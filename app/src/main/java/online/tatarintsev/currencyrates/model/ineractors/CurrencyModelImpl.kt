package online.tatarintsev.currencyrates.model.ineractors

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository
import kotlin.random.Random

class CurrencyModelImpl(private val currencyRepository: CurrencyRepository): CurrencyModel {
    override fun getCurrency(id: Int): Observable<CurrencyEntity> {
        return currencyRepository.getCurrency(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getCurrencyId(): Int {
        val random: Random = Random
        return random.nextInt(10)
    }

}