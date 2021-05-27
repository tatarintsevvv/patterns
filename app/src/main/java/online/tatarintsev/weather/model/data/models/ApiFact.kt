package online.tatarintsev.weather.model.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiFact(
    @SerializedName("temp") val temp: Int,
    @SerializedName("feels_like") val feels_like: Int,
    @SerializedName("temp_water") val temp_water: Int,
    @SerializedName("condition") val condition: String,
    @SerializedName("icon") val icon:String
): Parcelable
