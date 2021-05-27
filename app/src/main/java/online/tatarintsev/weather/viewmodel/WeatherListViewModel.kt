package online.tatarintsev.weather.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import online.tatarintsev.weather.model.data.models.ApiRate
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.interactors.WeatherModel

public class WeatherListViewModel(private val subscribeOn: Scheduler, private val observeOn: Scheduler, private val model: WeatherModel): ViewModel() {
    val SAVE_WEATHER_DATA: String = "weather_data"
    val SAVE_WEATHER_CHOSEN: String = "weather_chosen"

    private var disposable: Disposable? = null

    // поля данных, на которые подписывается View
    private var weatherLiveData: MutableLiveData<WeatherEntity> = MutableLiveData<WeatherEntity>()
    private var errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private var resultLiveData: MutableLiveData<String> = MutableLiveData<String>()

    // идентификатор валюты на случай пересоздания процесса
    // сохраняется с помощью стандартного механизма Bundle - savedInstanceState
    private var rateChosen: String? = null
    private var weatherAll: WeatherEntity? = null

    /**
     * Используется для восстановления идентификатора из savedInstanceState
     */
    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            weatherAll = savedInstanceState.getParcelable(SAVE_WEATHER_DATA)
            rateChosen = savedInstanceState.getString(SAVE_WEATHER_CHOSEN)
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putParcelable(SAVE_WEATHER_DATA, weatherLiveData.getValue())
        outState.putString(SAVE_WEATHER_CHOSEN, rateChosen)
    }

    /**
     * Запрашиваем данные, только если они не были получены ранее
     * ViewModel сохранится при пересоздании активити и данные не нужно будет запрашивать вновь
     */
    fun onStart() {
        if (weatherLiveData.getValue() == null) {
            // получаем данные из модели аналогично MVP
            model.getCurrency()
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(CurrencyObserver());
        }
    }

    /**
     * перегружам данные
     */
    fun reload() {
        model.getCurrency()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(CurrencyObserver());
    }

    /**
     * генерация идентификатора пользователя для наглядной демонстрации сохрания данных
     */
    private fun getCurrencyName(): String? {
        if (weatherAll != null) {
            rateChosen = model.getCurrencyName()
        }
        return rateChosen
    }

    /**
     * Единственный метод жизненного цикла ViewModel
     * Вызывается, когда пользователь явно покидает активити и ViewModel больше не будет хранится
     * явно покидание активити - кнопка "назад"
     * Если пользователь свернул приложение или перевернул экран - активити будет пересоздано,
     * ViewModel сохранится, а этот метод не вызовется
     */
    protected override fun onCleared() {
        if (disposable != null) {
            disposable!!.dispose()
        }

        super.onCleared()
    }

    fun onUserAction() {
        resultLiveData.setValue("Result")
    }

    fun getCurrencies(): MutableLiveData<WeatherEntity>  {
        return weatherLiveData
    }

    fun getRates(): ArrayList<ApiRate>?  {
        return weatherLiveData?.value?.rates
    }

    fun getError(): LiveData<String> {
        return errorLiveData
    }

    fun getResult(): LiveData<String> {
        return resultLiveData
    }

    private inner class CurrencyObserver: Observer<WeatherEntity> {

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(weather: WeatherEntity) {
            // полученные данные передаем в обозреваемое поле, которое уведомит подписчиков
            weatherLiveData.setValue(weather)
        }

        override fun onError(e: Throwable) {
            // ошибку тоже передаем в обозреваемое поле
            errorLiveData.setValue("Error!:" + e.message)
        }

        override fun onComplete() {

        }
    }

}