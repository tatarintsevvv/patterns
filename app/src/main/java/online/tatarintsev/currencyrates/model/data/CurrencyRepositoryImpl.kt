package online.tatarintsev.currencyrates.model.data

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository

class CurrencyRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi): CurrencyRepository {
    private val remoteDataSource: CurrencyDataSource = CurrencyRemoteDataSource(jsonPlaceHolderApi)

    override fun getCurrency(id: Int) : Observable<CurrencyEntity?>? {
        return remoteDataSource.getCurrencies()?.
        map { currencies ->
            val get = currencies.get(id)
            get
        }
    }
}