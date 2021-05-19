package online.tatarintsev.currencyrates.model.repositories

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity

interface CurrencyRepository {
    fun getCurrency(): Observable<CurrencyEntity>
}