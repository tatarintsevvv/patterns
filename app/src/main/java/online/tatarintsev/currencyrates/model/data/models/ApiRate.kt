package online.tatarintsev.currencyrates.model.data.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ApiRate(
    @SerializedName("tp") val tp: Int,
    @SerializedName("name") val name: String,
    @SerializedName("from") val from: Int,
    @SerializedName("currMnemFrom") val currMnemFrom: String,
    @SerializedName("to") val to: Int,
    @SerializedName("currMnemTo") val currMnemTo: String,
    @SerializedName("basic") val basic: String,
    @SerializedName("buy") val buy: String,
    @SerializedName("sale") val sale: String,
    @SerializedName("deltaBuy") val deltaBuy: String,
    @SerializedName("deltaSell") val deltaSell: String
) : Parcelable
