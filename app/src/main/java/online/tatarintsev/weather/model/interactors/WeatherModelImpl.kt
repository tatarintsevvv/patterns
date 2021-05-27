package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.repositories.WeatherRepository

class WeatherModelImpl(private val weatherRepository: WeatherRepository): WeatherModel {

    override fun getCurrency(): Observable<WeatherEntity> {
        return weatherRepository.getCurrency();
    }

    override fun getCurrencyName(): String? {
        TODO("Not yet implemented")
    }


}