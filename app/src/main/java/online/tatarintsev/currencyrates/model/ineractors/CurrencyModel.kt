package online.tatarintsev.currencyrates.model.ineractors

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity

interface CurrencyModel {
    fun getCurrency(id: Int) : Observable<CurrencyEntity>
    fun getCurrencyId() : Int
}