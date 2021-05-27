package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.WeatherRepository

class WeatherRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi): WeatherRepository {
    private val remoteDataSource: WeatherDataSource = WeatherRemoteDataSource(jsonPlaceHolderApi)

    override fun getCurrency(): Observable<WeatherEntity> {
        return remoteDataSource.getCurrencies()
                .map { currency ->
            val get = currency
            get
        }
    }

}

