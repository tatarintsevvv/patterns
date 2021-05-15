package online.tatarintsev.currencyrates.model

import io.reactivex.Observable
import online.tatarintsev.currencyrates.model.data.models.ApiCurrency
import retrofit2.http.GET

interface JsonPlaceHolderApiService {
    @GET("users")
    fun getCurrencies(): Observable<List<ApiCurrency>>
}