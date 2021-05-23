package online.tatarintsev.currencyrates.model.entities

import android.os.Parcelable
import kotlinx.android.parcel.RawValue
import kotlinx.parcelize.Parcelize
import online.tatarintsev.currencyrates.model.data.models.ApiRate

@Parcelize
data class CurrencyEntity(
        var code: Int,
        var messageTitle: String,
        var message: String,
        var rid: String,
        var downloadDate: String,
        var rates: ArrayList<ApiRate>,
        var productState: Int,
        var ratesDate: String
        ) : Parcelable
