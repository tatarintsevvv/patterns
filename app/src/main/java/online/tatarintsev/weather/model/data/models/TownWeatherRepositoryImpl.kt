package online.tatarintsev.weather.model.data.models

import io.reactivex.Observable
import online.tatarintsev.weather.model.data.WeatherDataSource
import online.tatarintsev.weather.model.data.WeatherRemoteDataSource
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownWeatherRepository

class TownWeatherRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi): TownWeatherRepository {
    private val remoteDataSource: WeatherDataSource = WeatherRemoteDataSource(jsonPlaceHolderApi)

    override fun getWeather(): Observable<WeatherEntity> {
        return remoteDataSource.getWeather()
            .map { weather ->
                val get = weather
                get
            }
    }

}