package com.flavorfusion.core_ui.mvi

/**
 * Marker interface for one-time UI effects.
 * Examples: navigation, snackbar, dialog triggers.
 */
interface UiEffect

/**
 * Marker interface for UI events (user intents).
 */
interface UiEvent

/**
 * Marker interface for UI state.
 * Represents the entire screen state.
 */
interface UiState

/**
 * Marker interface for reducer actions.
 * Actions describe state transitions.
 */
interface UiAction

/**
 * Configuration provider for an MVI ViewModel.
 *
 * Supplies initial state and reducer implementation.
 */
interface MviConfig<State : UiState> {
    fun initialState(): State
    fun reducer(): Reducer<State>
}

/**
 * Functional reducer interface.
 *
 * Defines how an action transforms a state into a new state.
 */
fun interface Reducer<State> {

    /**
     * Applies an action to the current state and returns a new state.
     */
    fun State.reduce(action: UiAction): State
}



