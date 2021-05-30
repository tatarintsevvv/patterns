package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface TownWeatherModel {
    fun getWeather(lat: Float, lon: Float): Observable<WeatherEntity>
    fun getTownName(): String?
}