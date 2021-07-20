package online.tatarintsev.mvi.model

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FirstScreenAPI {
    @GET("api/requestSMSCodeClient")
    fun getStatusSMS(@Query("phone_number") phone_number: String): Call<PODServerResponseSMSStatus>
}