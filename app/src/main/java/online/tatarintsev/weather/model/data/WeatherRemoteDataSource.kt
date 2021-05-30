package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi

class WeatherRemoteDataSource(private val jsonPlaceHolderApi: JsonPlaceHolderApi): WeatherDataSource {

    override fun getWeather(lat: Float, lon: Float): Observable<WeatherEntity> {
        return jsonPlaceHolderApi.getJsonplaceholderApiService().getWeather(lat, lon).
        map{ apiWeather ->
            val weather = WeatherEntity(
                apiWeather.now,
                apiWeather.now_dt,
                apiWeather.info,
                apiWeather.fact,
                apiWeather.forecasts
            )
            weather
        }
    }


}