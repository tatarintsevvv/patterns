package online.tatarintsev.weather.model.network

import io.reactivex.Observable
import online.tatarintsev.weather.model.data.models.ApiWeather
import online.tatarintsev.weather.model.entities.TownEntity
import retrofit2.http.Headers
import retrofit2.http.GET
import retrofit2.http.Query

interface JsonPlaceHolderApiService {
    @Headers("X-Yandex-API-Key:da158da9-6678-45cd-a530-4e83c951d638")
    @GET("/v2/forecast")
    fun getWeather(@Query("lat") lat: Float, @Query("lon") lon: Float): Observable<ApiWeather>

    fun getTowns(): Observable<ArrayList<TownEntity>>
}
