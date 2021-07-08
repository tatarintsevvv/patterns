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

    private var disposable: Disposable? = null

    // для корутинов
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)




    sealed class Intent {
        object Search : Intent()
    }

    sealed class State {
        class Success(val serverResponseData: PODServerResponseData) : State()
        class Error(val error: Throwable) : State()
        class Loading(val progress: Int?) : State()
    }

    override suspend fun handleIntent(intent: Intent) = when (intent) {
        is Intent.Search -> TODO("Not implemented yet")

    }

    fun onStart() {
        viewModelScope.launch(Dispatchers.IO) {
        setState { State.Loading(1)}
            sendServerRequest()
        }

    }

    private suspend fun sendServerRequest() {
//        liveDataForViewToObserve.value = PictureOfTheDayData.Loading(null)
        val apiKey: String = BuildConfig.NASA_API_KEY
        if (apiKey.isBlank()) {

            setState { State.Error(Throwable("You need API key")) }
//            PictureOfTheDayData.Error(Throwable("You need API key"))
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
//                        liveDataForViewToObserve.value =
//                            PictureOfTheDayData.Success(response.body()!!)
                    } else {
                        val message = response.message()
                        viewModelScope.launch(Dispatchers.IO) {
                        if (message.isNullOrEmpty()) {
                                 setState { State.Error(Throwable("Unidentified error")) }

//                            liveDataForViewToObserve.value =
//                                PictureOfTheDayData.Error(Throwable("Unidentified error"))
                        } else {
                            State.Error(Throwable(message))
//                            liveDataForViewToObserve.value =
//                                PictureOfTheDayData.Error(Throwable(message))
                        }
                        }
                    }
                }

                override fun onFailure(call: Call<PODServerResponseData>, t: Throwable) {
                    viewModelScope.launch(Dispatchers.IO) {
                        setState { State.Error(t) }
                    }
//                    liveDataForViewToObserve.value = PictureOfTheDayData.Error(t)
                }
            })
        }


    }


    /**
     * Единственный метод жизненного цикла ViewModel
     * Вызывается, когда пользователь явно покидает активити и ViewModel больше не будет хранится
     * явно покидание активити - кнопка "назад"
     * Если пользователь свернул приложение или перевернул экран - активити будет пересоздано,
     * ViewModel сохранится, а этот метод не вызовется
     */
    override fun onCleared() {
        if (disposable != null) {
            disposable!!.dispose()
        }
        viewModelJob.cancel()
        super.onCleared()
    }
}