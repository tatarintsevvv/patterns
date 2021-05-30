package online.tatarintsev.weather.model.repositories

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface TownWeatherRepository {
    fun getWeather(lat: Float, lon:Float): Observable<WeatherEntity>
}