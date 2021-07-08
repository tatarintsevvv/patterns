package online.tatarintsev.mvi.viewmodel

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock


@ExperimentalCoroutinesApi
abstract class StatefulIntentViewModel<Intent, State>(
    initialState: State
) : IntentViewModel<Intent>() {

    private var state = initialState

    private val statesBroadcast = BroadcastChannel<State>(1)

    private val stateMutex = Mutex()

    @FlowPreview
    val states = statesBroadcast.asFlow()

    protected suspend fun setState(reducer: State.() -> State) =
        stateMutex.withLock {
            state = state.reducer()
            statesBroadcast.send(state)
        }
    protected suspend fun withState(action: (State).() -> Unit) =
        setState {
            this.apply(action)
        }
}