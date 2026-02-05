package com.flavorfusion.core_ui.mvi

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

/**
 * Base ViewModel implementation for an MVI architecture in Compose.
 *
 * This class provides a standardized state container, event pipeline, and side-effect channel.
 * It enforces unidirectional data flow:
 *
 * UI -> Event -> handleEvent -> Action -> Reducer -> State -> UI
 *
 * Responsibilities:
 * - Holds and exposes immutable UI state as a StateFlow
 * - Accepts UI events through a SharedFlow
 * - Processes events via [handleEvent]
 * - Reduces actions into new state using a [Reducer]
 * - Emits one-off side effects (navigation, toasts, etc.) via a Channel
 *
 * @param State The UI state type representing the full screen state
 * @param Event The UI event type representing user or system intents
 * @param config Configuration provider that supplies initial state and reducer
 */
abstract class MviViewModel<State : UiState, Event : UiEvent>(
    private val config: MviConfig<State>
) : ViewModel(), MviConfig<State> by config {

    private val initialState by lazy { initialState() }

    private val _state = MutableStateFlow(initialState)

    /**
     * State container exposed to the UI
     * UI collects this state to render screen
     */
    val state = _state.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()

    /**
     * event stream used to process UI intents.
     */
    private val event = _event.asSharedFlow()

    private val _effect: Channel<UiEffect> = Channel(Channel.BUFFERED, BufferOverflow.DROP_OLDEST)
    /**
     * Channel used for one-time effects such as navigation,
     * snackbars, dialogs, etc.
     */
    val effect = _effect.receiveAsFlow()

    /**
     * Lazily created reducer responsible for state transitions.
     */
    private val reducer by lazy { reducer() }

    /**
     * Handles incoming UI events.
     *
     * Subclasses must implement this function and translate events
     * into side effects.
     *
     * @param event UI intent to process
     */
    abstract fun handleEvent(event: Event)

    /**
     * Returns the current state snapshot.
     */
    val currentState: State
        get() = _state.value

    init {
        subscribeEvents()
    }

    /**
     * Emits an event into the event pipeline.
     *
     * This is the entry point for UI intents.
     * Events are collected and forwarded to [handleEvent].
     *
     * @param event Event coming from UI
     */
    fun setEvent(event: Event) {
        viewModelScope.launch { _event.emit(event) }
    }

    /**
     * Applies a state transition by dispatching an action to the reducer.
     *
     * The reducer produces a new state from the current state and action.
     * This function should be used inside [handleEvent].
     *
     * @param action Domain action that transforms state
     */
    protected fun dispatch(action: UiAction) {
        val newState = reducer.run { currentState.reduce(action) }
        _state.value = newState
    }

    /**
     * Publishes a one-time UI effect.
     *
     * Effects are not part of state and should represent transient actions
     * like navigation or showing a toast.
     *
     * @param builder Lambda used to construct the effect lazily
     */
    protected fun publish(builder: () -> UiEffect) {
        val effect = builder()
        viewModelScope.launch { _effect.send(effect) }
    }

    /**
     * Subscribes to the internal event flow and routes events
     * to [handleEvent].
     *
     * Automatically started when the ViewModel is created.
     */
    private fun subscribeEvents() {
        viewModelScope.launch {
            event.collect {
                handleEvent(it)
            }
        }
    }
}