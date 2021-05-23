package online.tatarintsev.currencyrates.model.entities

import com.google.gson.annotations.SerializedName

data class RequestCurrency (
        @SerializedName("uid") val uid: String,
        @SerializedName("type") val type: String,
        @SerializedName("rid") val rid: String
        )