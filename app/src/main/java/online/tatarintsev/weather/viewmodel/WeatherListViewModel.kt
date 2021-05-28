package online.tatarintsev.weather.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.interactors.TownWeatherModel
import online.tatarintsev.weather.model.interactors.TownsListModel

public class WeatherListViewModel(private val subscribeOn: Scheduler, private val observeOn: Scheduler, private val modelTown: TownsListModel): ViewModel() {
    val SAVE_WEATHER_DATA: String = "weather_data"
    val SAVE_WEATHER_CHOSEN: String = "weather_chosen"

    private var disposable: Disposable? = null

    // поля данных, на которые подписывается View
    private var townsLiveData: MutableLiveData<ArrayList<TownEntity>> = MutableLiveData<ArrayList<TownEntity>>()
    private var errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private var resultLiveData: MutableLiveData<String> = MutableLiveData<String>()

    // идентификатор выбранного города на случай пересоздания процесса
    // сохраняется с помощью стандартного механизма Bundle - savedInstanceState
    private var townChosen: String? = null

    private val ourTowns: ArrayList<TownEntity> = arrayListOf(
        TownEntity("Москва", 49.0, 49.0),
        TownEntity("Санкт-Петербург", 49.0, 49.0),
        TownEntity("Екатеринбург", 49.0, 49.0),
        TownEntity("Казань", 49.0, 49.0),
        TownEntity("Новосибирск", 49.0, 49.0),
        TownEntity("Красноярск", 49.0, 49.0),
        TownEntity("Нижний Новгород", 49.0, 49.0),
        TownEntity("Сочи", 49.0, 49.0),
    )

    private val notOurTowns: ArrayList<TownEntity> = arrayListOf(
        TownEntity("New-York", 49.0, 49.0),
        TownEntity("Los-Angelos", 49.0, 49.0),
        TownEntity("Pekin", 49.0, 49.0),
        TownEntity("Berlin", 49.0, 49.0),
        TownEntity("Dehli", 49.0, 49.0),
        TownEntity("Paris", 49.0, 49.0),
        TownEntity("Rio-de-Janeiro", 49.0, 49.0),
        TownEntity("London", 49.0, 49.0),
    )


    /**
     * Используется для восстановления идентификатора из savedInstanceState
     */
    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            townChosen = savedInstanceState.getString(SAVE_WEATHER_CHOSEN)
        }
        townsLiveData.setValue(ourTowns)
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putString(SAVE_WEATHER_CHOSEN, townChosen)
    }

    /**
     * Запрашиваем данные, только если они не были получены ранее
     * ViewModel сохранится при пересоздании активити и данные не нужно будет запрашивать вновь
     */
    fun onStart() {
        if (townsLiveData.getValue() == null) {
            // получаем данные из модели аналогично MVP
            modelTown.getTowns()
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(ListTownObserver());
        }
    }

    /**
     * перегружам данные
     */
    fun reload() {
        modelTown.getTowns()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(ListTownObserver());
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

    fun getTowns(): MutableLiveData<ArrayList<TownEntity>>  {
        return townsLiveData
    }

    fun getError(): LiveData<String> {
        return errorLiveData
    }

    fun getResult(): LiveData<String> {
        return resultLiveData
    }

    private inner class ListTownObserver: Observer<ArrayList<TownEntity>> {

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(towns: ArrayList<TownEntity>) {
            // полученные данные передаем в обозреваемое поле, которое уведомит подписчиков
            townsLiveData.setValue(towns)
        }

        override fun onError(e: Throwable) {
            // ошибку тоже передаем в обозреваемое поле
            errorLiveData.setValue("Error!:" + e.message)
        }

        override fun onComplete() {

        }
    }

}