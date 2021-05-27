package online.tatarintsev.weather.model.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiWeather(
    @SerializedName("now") val now: Long,
    @SerializedName("now_dt") val now_dt: String,
    @SerializedName("info") val info: ApiInfo
): Parcelable