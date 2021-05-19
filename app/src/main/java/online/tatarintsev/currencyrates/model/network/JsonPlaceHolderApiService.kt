package online.tatarintsev.currencyrates.model.network

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency
import online.tatarintsev.currencyrates.model.entities.RequestCurrency
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Body

interface JsonPlaceHolderApiService {
    @Headers("User-Agent:Test GeekBrains iOS 3.0.0.182 (iPhone 11; iOS 14.4.1; Scale/2.00; Private);Content-Type: application/json;Accept: application/json")
    @POST("/moby-pre-44/core?r=BEYkZbmV&d=563B4852-6D4B-49D6-A86E-B273DD520FD2&t=ExchangeRates&v=44")
    fun getCurrencies(@Body jsonBody: RequestCurrency): Observable<ApiCurrency>
}