package online.tatarintsev.weather.model.repositories

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface WeatherRepository {
    fun getCurrency(): Observable<WeatherEntity>
}