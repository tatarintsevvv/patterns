package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi

class CurrencyRemoteDataSource(val jsonPlaceHolderApi: JsonPlaceHolderApi): CurrencyDataSource {

    public override fun getUsers(): Observable<List<CurrencyEntity>> {
        return jsonPlaceHolderApi.getJsonplaceholderApiService().getCurrencies()
            .map(apiCurrencies ->

        //  throws Exception
        var currencies: MutableList<CurrencyEntity> = ArrayList(apiCurrencies.size)

        for (i in apiCurrencies.indices) {
            var apiCurrency: ApiCurrency = apiCurrencies[i]
                    currencies.add(CurrencyEntity(
                            i,
                        apiCurrency.getName(),
                        apiCurrency.getUsername(),
                        apiCurrency.getEmail(),
                        apiCurrency.getPhone()))
                }

                    return currencies
                }
            );
    }

}