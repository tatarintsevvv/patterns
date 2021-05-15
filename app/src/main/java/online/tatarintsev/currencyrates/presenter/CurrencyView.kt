package online.tatarintsev.currencyrates.presenter

import online.tatarintsev.currencyrates.model.entities.CurrencyEntity

interface CurrencyView {
//    fun showProgress()
//    fun hideProgress()
    fun showCurrency(currency: CurrencyEntity)
    fun showError()
    fun showResult()
}