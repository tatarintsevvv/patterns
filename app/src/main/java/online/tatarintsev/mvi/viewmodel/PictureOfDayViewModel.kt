package online.tatarintsev.mvi.viewmodel

import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import online.tatarintsev.mvi.BuildConfig
import online.tatarintsev.mvi.model.PODRetrofitImpl
import online.tatarintsev.mvi.model.PODServerResponseData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
class PictureOfDayViewModel (
    initialState: State = State.Loading(0)
    ) : StatefulIntentViewModel<PictureOfDayViewModel.Intent, PictureOfDayViewModel.State>(initialState) {

    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()

    // для корутинов
    private var disposable: Disposable? = null

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    sealed class Intent {
        object Search : Intent()
    }

    sealed class State {
        class Success(val serverResponseData: PODServerResponseData) : State()
        class Loading(val progress: Int?) : State()
        class Error(val error: Throwable) : State()
    }

    override suspend fun handleIntent(intent: Intent) = when (intent) {
        is Intent.Search -> TODO("Not implemented yet")

    }

    fun onStart() {
        viewModelScope.launch(Dispatchers.IO) {
            sendServerRequest()
        }

    }

    private suspend fun sendServerRequest() {
        val apiKey: String = BuildConfig.NASA_API_KEY
        setState { State.Loading(1)}
        if (apiKey.isBlank()) {
            setState { State.Error(Throwable("You need API key")) }
        } else {
            retrofitImpl.getRetrofitImpl().getPictureOfTheDay(apiKey).enqueue(object :
                Callback<PODServerResponseData> {
                override fun onResponse(
                    call: Call<PODServerResponseData>,
                    response: Response<PODServerResponseData>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        viewModelScope.launch(Dispatchers.IO) {
                            setState { State.Success(response.body() as PODServerResponseData) }
                        }
                    } else {
                        val message = response.message()
                        viewModelScope.launch(Dispatchers.IO) {
                            if (message.isNullOrEmpty()) {
                                setState { State.Error(Throwable("Unidentified error")) }
                            } else {
                                setState { State.Error(Throwable(message)) }
                            }
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    viewModelScope.launch(Dispatchers.IO) {
                        setState { State.Error(t) }
                    }
                }
            })
        }
    }

    override fun onCleared() {
        if (disposable != null) {
            disposable!!.dispose()
        }
        viewModelJob.cancel()
        super.onCleared()
    }

}