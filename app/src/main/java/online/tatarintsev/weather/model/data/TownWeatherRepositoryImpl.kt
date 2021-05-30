package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownWeatherRepository

class TownWeatherRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi): TownWeatherRepository {
    private val remoteDataSource: WeatherDataSource = WeatherRemoteDataSource(jsonPlaceHolderApi)

    override fun getWeather(lat: Float, lon: Float): Observable<WeatherEntity> {
        return remoteDataSource.getWeather(lat, lon)
            .map { weather ->
                weather
            }
    }

}