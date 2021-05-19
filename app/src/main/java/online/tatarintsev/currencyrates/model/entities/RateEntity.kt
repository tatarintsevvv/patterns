package online.tatarintsev.currencyrates.model.entities

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RateEntity (
    var tp: Int,
    var name: String,
    var from: Int,
    var currMnemFrom: String,
    var to: Int,
    var currMnemTo: String,
    var basic: String,
    var buy: String,
    var sell: String,
    var deltaBuy: String,
    var deltaSell: String
) : Parcelable