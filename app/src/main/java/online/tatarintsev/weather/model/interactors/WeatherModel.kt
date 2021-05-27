package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface WeatherModel {
    fun getCurrency(): Observable<WeatherEntity>
    fun getCurrencyName(): String?
}