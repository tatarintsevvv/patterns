package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.entities.RequestCurrency
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi

class CurrencyRemoteDataSource(private val jsonPlaceHolderApi: JsonPlaceHolderApi): CurrencyDataSource {

    override fun getCurrencies(): Observable<CurrencyEntity> {
        val requestCurrency: RequestCurrency = RequestCurrency(
                "563B4852-6D4B-49D6-A86E-B273DD520FD2",
                "ExchangeRates",
                "BEYkZbmV"
        )
        return jsonPlaceHolderApi.getJsonplaceholderApiService().getCurrencies(requestCurrency).
            map{ apiCurrency ->
                var currency = CurrencyEntity(
                        apiCurrency.code,
                        apiCurrency.messageTitle,
                        apiCurrency.message,
                        apiCurrency.rid,
                        apiCurrency.downloadDate,
                        apiCurrency.rates,
                        apiCurrency.productState,
                        apiCurrency.ratesDate
                )
                currency
            }
    }
}