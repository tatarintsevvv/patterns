package online.tatarintsev.currencyrates.model.interactors

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository

class CurrencyModelImpl(private val currencyRepository: CurrencyRepository): CurrencyModel {

    override fun getCurrency(): Observable<CurrencyEntity> {
        return currencyRepository.getCurrency();
    }

    override fun getCurrencyName(): String? {
        TODO("Not yet implemented")
    }


}