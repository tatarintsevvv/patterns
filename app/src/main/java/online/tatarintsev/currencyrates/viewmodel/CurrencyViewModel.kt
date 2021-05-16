package online.tatarintsev.currencyrates.viewmodel

import android.os.Bundle
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.Observer
import io.reactivex.Scheduler
import io.reactivex.disposables.Disposable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.interactors.CurrencyModel

public class CurrencyViewModel(private val subscribeOn: Scheduler, private val observeOn: Scheduler, private val model: CurrencyModel): ViewModel() {
    val SAVE_CURRENCY_ID: String = "user_id"

    private var disposable: Disposable? = null

    // поля данных, на которые подписывается View
    private var currencyLiveData: MutableLiveData<CurrencyEntity> = MutableLiveData<CurrencyEntity>()
    private var errorLiveData: MutableLiveData<String> = MutableLiveData<String>()
    private var resultLiveData: MutableLiveData<String> = MutableLiveData<String>()

    // идентификатор пользователя на случай пересоздания процесса
    // сохраняется с помощью стандартного механизма Bundle - savedInstanceState
    private var usedId: Int = -1


    /**
     * Используется для восстановления идентификатора из savedInstanceState
     */
    fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            usedId = savedInstanceState.getInt(SAVE_CURRENCY_ID, -1);
        }
    }

    fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(SAVE_CURRENCY_ID, usedId);
    }

    /**
     * Запрашиваем данные, только если они не были получены ранее
     * ViewModel сохранится при пересоздании активити и данные не нужно будет запрашивать вновь
     */
    fun onStart() {
        if (currencyLiveData.getValue() == null) {
            // получаем данные из модели аналогично MVP
            model.getCurrency(getCurrencyId())
                    .subscribeOn(subscribeOn)
                    .observeOn(observeOn)
                    .subscribe(CurrencyObserver());
        }
    }

    /**
     * генерация идентификатора пользователя для наглядной демонстрации сохрания данных
     */
    private fun getCurrencyId(): Int {
        if (usedId == -1) {
            usedId = model.getCurrencyId()
        }
        return usedId
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

    fun getUser(): LiveData<CurrencyEntity> {
        return currencyLiveData
    }

    fun getError(): LiveData<String> {
        return errorLiveData;
    }

    fun getResult(): LiveData<String> {
        return resultLiveData;
    }

    private inner class CurrencyObserver: Observer<CurrencyEntity> {

        override fun onSubscribe(d: Disposable) {
            disposable = d
        }

        override fun onNext(currency: CurrencyEntity) {
            // полученные данные передаем в обозреваемое поле, которое уведомит подписчиков
            currencyLiveData.setValue(currency)
        }

        override fun onError(e: Throwable) {
            // ошибку тоже передаем в обозреваемое поле
            errorLiveData.setValue("Error!")
        }

        override fun onComplete() {

        }
    }

}