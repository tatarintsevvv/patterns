package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface TownWeatherModel {
    fun getWeather(): Observable<WeatherEntity>
    fun getTownName(): String?
}