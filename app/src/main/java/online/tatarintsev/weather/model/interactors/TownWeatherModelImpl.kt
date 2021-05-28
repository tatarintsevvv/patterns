package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.WeatherEntity
import online.tatarintsev.weather.model.repositories.TownWeatherRepository

class TownWeatherModelImpl(private val townWeatherRepository: TownWeatherRepository): TownWeatherModel {

    override fun getWeather(): Observable<WeatherEntity> {
        return townWeatherRepository.getWeather();
    }

    override fun getTownName(): String? {
        TODO("Not yet implemented")
    }


}