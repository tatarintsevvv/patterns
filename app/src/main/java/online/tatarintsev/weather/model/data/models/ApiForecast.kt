package online.tatarintsev.weather.model.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ApiForecast(
    @SerializedName("date") val date: String
): Parcelable
