package online.tatarintsev.mvi.viewmodel.first_screen

import androidx.lifecycle.viewModelScope
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.*
import online.tatarintsev.mvi.model.PODRetrofitImpl
import online.tatarintsev.mvi.model.PODServerResponseSMSStatus
import online.tatarintsev.mvi.viewmodel.StatefulIntentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@ExperimentalCoroutinesApi
class FirstScreenViewModel (
    initialState: State = State.Loading(0)
    ) : StatefulIntentViewModel<FirstScreenViewModel.Intent, FirstScreenViewModel.State>(initialState) {

    private val retrofitImpl: PODRetrofitImpl = PODRetrofitImpl()

    // для корутинов
    private var disposable: Disposable? = null

    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    sealed class Intent {
        class RequestSMS(val phone_number: String) : Intent()
    }

    sealed class State {
        class Success(val serverResponseSMSStatus: PODServerResponseSMSStatus) : State()
        class Loading(val progress: Int?) : State()
        class Error(val error: Throwable) : State()
    }

    override suspend fun handleIntent(intent: Intent) = when (intent) {
        is Intent.RequestSMS -> {
            viewModelScope.launch(Dispatchers.IO) {
                sendServerRequest(intent.phone_number)
            }
        }

    }

    fun onStart() {

    }

    private suspend fun sendServerRequest(phoneNumber: String) {
        setState { State.Loading(1) }
        if (phoneNumber.isBlank()) {
            setState { State.Error(Throwable("Не указан номер телефона")) }
        } else {
            retrofitImpl.getRetrofitImpl().getStatusSMS(phoneNumber).enqueue(object :
                Callback<PODServerResponseSMSStatus> {
                override fun onResponse(
                    call: Call<PODServerResponseSMSStatus>,
                    response: Response<PODServerResponseSMSStatus>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        viewModelScope.launch(Dispatchers.IO) {
                            setState { State.Success(response.body() as PODServerResponseSMSStatus) }
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

                override fun onFailure(call: Call<PODServerResponseSMSStatus>, t: Throwable) {
                    viewModelScope.launch(Dispatchers.IO) {
                        setState { State.Error(t) }
                    }
                }
            })
        }

    }

    override fun onCleared() {
        disposable?.dispose()
        viewModelJob.cancel()
        super.onCleared()
    }

}