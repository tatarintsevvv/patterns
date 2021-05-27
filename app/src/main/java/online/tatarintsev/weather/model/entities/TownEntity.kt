package online.tatarintsev.weather.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TownEntity (
    val name: String,
    val lat: Double,
    val lon: Double
        ) : Parcelable