package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository

class CurrencyRepositoryImpl: CurrencyRepository {
    private var remoteDataSource: CurrencyDataSource

    public CurrencyRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi) {
        remoteDataSource = CurrencyRemoteDataSource(jsonPlaceHolderApi)
    }

    public override fun getCurrency(val id: Int): Observable<CurrencyEntity> {
        return remoteDataSource.getCurrencies()
            .map(Function<List<CurrencyEntity>, CurrencyEntity>() {
                @Throws(Exception::class)
                fun apply(currencies: List<CurrencyEntity>) :  CurrencyEntity {
                    return currencies.get(id);
                }
            })
    }

}