package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity

interface WeatherDataSource {
    fun getCurrencies(): Observable<WeatherEntity>
}