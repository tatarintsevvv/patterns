package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity

interface CurrencyDataSource {
    fun getCurrencies(): Observable<CurrencyEntity>
}