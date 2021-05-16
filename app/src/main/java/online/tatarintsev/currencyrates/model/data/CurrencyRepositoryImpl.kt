package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository

class CurrencyRepositoryImpl: CurrencyRepository {
    private lateinit var remoteDataSource: CurrencyDataSource

    public fun CurrencyRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi) {
        remoteDataSource = CurrencyRemoteDataSource(jsonPlaceHolderApi)
    }

    public override fun getCurrency(id: Int): Observable<CurrencyEntity> {
        return remoteDataSource.getCurrencies()
                .map(
                        it.get(id)
                )
                /*
            .map(Function<List<CurrencyEntity>, CurrencyEntity>() {
                @Throws(Exception::class)
                fun apply(currencies: List<CurrencyEntity>) :  CurrencyEntity {
                    return currencies.get(id);
                }
            })

                 */
    }

}