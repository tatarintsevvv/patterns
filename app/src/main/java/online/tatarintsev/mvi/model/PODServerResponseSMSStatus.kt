package online.tatarintsev.mvi.model

import com.google.gson.annotations.SerializedName

data class PODServerResponseSMSStatus(
    @field:SerializedName("status") val status: String?,
)