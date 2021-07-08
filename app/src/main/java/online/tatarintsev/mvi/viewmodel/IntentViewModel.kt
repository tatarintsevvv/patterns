package online.tatarintsev.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.launch

abstract class IntentViewModel<Intent> : ViewModel()  {
    private val intents = Channel<Intent>()
    fun send(intent: Intent) = viewModelScope.launch { intents.send(intent) }
    protected abstract suspend fun handleIntent(intent: Intent)
    init {
        viewModelScope.launch {
            intents.consumeEach { intent ->
                handleIntent(intent)
            }
        }
    }
}