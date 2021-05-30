package online.tatarintsev.weather.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import online.tatarintsev.weather.model.data.models.ApiFact
import online.tatarintsev.weather.model.data.models.ApiForecast
import online.tatarintsev.weather.model.data.models.ApiInfo

@Parcelize
data class WeatherEntity(
        var now: Long,
        var now_dt: String,
        val info: ApiInfo,
        val fact: ApiFact,
        val forecast: ApiForecast
        ) : Parcelable
