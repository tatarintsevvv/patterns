package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi

class WeatherRemoteDataSource(private val jsonPlaceHolderApi: JsonPlaceHolderApi): WeatherDataSource {

    override fun getWeather(): Observable<WeatherEntity> {

        return jsonPlaceHolderApi.getJsonplaceholderApiService().getWeather().
            map{ apiWeather ->
                var weather = WeatherEntity(
                        apiWeather.now,
                        apiWeather.now_dt,
                        apiWeather.info,
                        apiWeather.fact,
                        apiWeather.forecast
                )
                weather
            }
    }

}