package online.tatarintsev.weather.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.interactors.TownsListModel

class WeatherListViewModel(private val subscribeOn: Scheduler, private val observeOn: Scheduler, private val modelTown: TownsListModel): ViewModel() {
    private val SAVE_WEATHER_DATA: String = "weather_data"
    private val SAVE_LIST_CHOSEN: String = "list_chosen"

    private val RUSSIAN_LIST_CHOSEN: Int = 0
    private val FOREIGN_LIST_CHOSEN: Int = 1

    private var disposable: Disposable? = null

    // поля данных, на которые подписывается View
    private var townsLiveData: MutableLiveData<ArrayList<TownEntity>> = MutableLiveData<ArrayList<TownEntity>>()
    private var errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private var resultLiveData: MutableLiveData<String> = MutableLiveData<String>()

    // идентификатор выбранного города на случай пересоздания процесса
    // сохраняется с помощью стандартного механизма Bundle - savedInstanceState
    private var listChosen: Int = RUSSIAN_LIST_CHOSEN

    private val ourTowns: ArrayList<TownEntity> = arrayListOf(
        TownEntity("Москва", 55.7522f, 37.6156f),
        TownEntity("Санкт-Петербург", 59.93428f, 30.3351f),
        TownEntity("Екатеринбург", 56.8575f, 60.6125f),
        TownEntity("Казань", 55.83043f, 49.06608f),
        TownEntity("Новосибирск", 55.00835f, 82.93573f),
        TownEntity("Красноярск", 56.01258f, 92.89325f),
        TownEntity("Нижний Новгород", 56.2965f, 43.93606f),
        TownEntity("Сочи", 43.60281f, 39.73415f)
    )

    private val notOurTowns: ArrayList<TownEntity> = arrayListOf(
        TownEntity("New-York", 40.7143f, -74.006f),
        TownEntity("Los-Angelos", 34.0396f, -118.2661f),
        TownEntity("Pekin", 39.9289f, 116.3883f),
        TownEntity("Berlin", 55.755786f, 37.6176f),
        TownEntity("Dehli", 28.65915f, 77.23149f),
        TownEntity("Paris", 48.85341f, 2.3488f),
        TownEntity("Rio-de-Janeiro", -22.9064f, -43.2075f),
        TownEntity("London", 51.50853f, -0.12574f)
    )


    /**
     * Используется для восстановления идентификатора из savedInstanceState
     */
    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            listChosen = savedInstanceState.getInt(SAVE_LIST_CHOSEN, RUSSIAN_LIST_CHOSEN)
        }
        if(listChosen == RUSSIAN_LIST_CHOSEN) {
            townsLiveData.setValue(ourTowns)
        } else {
            townsLiveData.setValue(notOurTowns)
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_LIST_CHOSEN, listChosen)
    }

    /**
     * Оставлено на всякий случай, если пожелаем брать список городов из сети, но пока
     * список городов всегда не null
     * Запрашиваем данные, только если они не были получены ранее
     * ViewModel сохранится при пересоздании активити и данные не нужно будет запрашивать вновь
     */
    fun onStart() {
        if (townsLiveData.value == null) {
            // получаем данные из модели аналогично MVP
            modelTown.getTowns()
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(ListTownObserver())
        }
    }

    /**
     * переключаем список
     */
    fun revertList() {
        if(listChosen == RUSSIAN_LIST_CHOSEN) {
            listChosen = FOREIGN_LIST_CHOSEN
            townsLiveData.setValue(notOurTowns)
        } else {
            listChosen = RUSSIAN_LIST_CHOSEN
            townsLiveData.setValue(ourTowns)
        }
    }

    /**
     * Оставлено на всякий случай, если пожелаем брать список городов из сети, но пока не используется
     * перегружам данные
     */
    fun reload() {
        modelTown.getTowns()
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(ListTownObserver())
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

        super.onCleared()
    }

    fun onUserAction() {
        resultLiveData.value = "Result"
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
            townsLiveData.value = towns
        }

        override fun onError(e: Throwable) {
            // ошибку тоже передаем в обозреваемое поле
            errorLiveData.value = "Error!:" + e.message
        }

        override fun onComplete() {

        }
    }

}