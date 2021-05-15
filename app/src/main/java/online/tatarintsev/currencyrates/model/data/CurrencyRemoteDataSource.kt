package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi

class CurrencyRemoteDataSource(val jsonPlaceHolderApi: JsonPlaceHolderApi): CurrencyDataSource {

    public override fun getUsers(): Observable<List<CurrencyEntity>> {
        return jsonPlaceHolderApi.getJsonplaceholderApiService().getCurrencies()
            .map(Function<List<ApiCurrency>, List<CurrencyEntity>>() {
                public override fun apply(apiCurrencies: List<ApiCurrency>) throws Exception: List<CurrencyEntity> {
                    var currencies: List<CurrencyEntity> = ArrayList<>(apiCurrencies.size())

                    for (int i = 0; i < apiCurrencies.size(); i++) {
                    var apiCurrency: ApiCurrency = apiCurrencies.get(i)
                    currencies.add(CurrencyEntity(
                            i,
                        apiCurrency.getName(),
                        apiCurrency.getUsername(),
                        apiCurrency.getEmail(),
                        apiCurrency.getPhone()))
                }

                    return currencies
                }
            });
    }

}