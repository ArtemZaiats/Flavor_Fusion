package com.flavorfusion.common_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.flavorfusion.common_domain.model.Result
import com.flavorfusion.common_domain.model.onError
import com.flavorfusion.common_domain.model.onSuccess
import com.flavorfusion.common_ui.error.ErrorMessage
import com.flavorfusion.common_ui.error.ErrorMessageExtractor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

interface Executor {
    fun <T : ViewModel, D> T.launch(
        context: CoroutineContext = Dispatchers.IO,
        onSuccess: suspend (D) -> Unit = {},
        onError: (ErrorMessage) -> Unit = {},
        action: suspend CoroutineScope.() -> Result<D>
    ): Job
}

class DefaultExecutor @Inject constructor(
    private val errorMessageExtractor: ErrorMessageExtractor
) : Executor {
    override fun <T : ViewModel, D> T.launch(
        context: CoroutineContext,
        onSuccess: suspend (D) -> Unit,
        onError: (ErrorMessage) -> Unit,
        action: suspend CoroutineScope.() -> Result<D>
    ): Job {
        return viewModelScope.launch(context) {
            action.invoke(this)
                .onSuccess { onSuccess.invoke(it) }
                .onError { rootError ->
                    onError.invoke(errorMessageExtractor.extract(rootError))
                }
        }
    }

}