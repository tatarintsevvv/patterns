package online.tatarintsev.currencyrates.model.interactors

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository

class CurrencyModelImpl(private val currencyRepository: CurrencyRepository): CurrencyModel {

    override fun getCurrency(id: Int): Observable<CurrencyEntity> {
        return currencyRepository.getCurrency(id);
    }

    override fun getCurrencyId(): Int {
        return (0 until 10).random()
    }

}