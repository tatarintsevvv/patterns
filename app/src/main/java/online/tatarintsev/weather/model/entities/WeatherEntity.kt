package online.tatarintsev.weather.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class WeatherEntity(
        var now: Long,
        var now_dt: String,
        var message: String,
        var rid: String,
        var downloadDate: String,
        var rates: ArrayList<ApiRate>,
        var productState: Int,
        var ratesDate: String
        ) : Parcelable
