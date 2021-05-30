package online.tatarintsev.weather.model.network

import io.reactivex.Observable
import online.tatarintsev.weather.model.data.models.ApiWeather
import online.tatarintsev.weather.model.entities.TownEntity
import retrofit2.http.Headers
import retrofit2.http.GET

interface JsonPlaceHolderApiService {
    @Headers("X-Yandex-API-Key:da158da9-6678-45cd-a530-4e83c951d638")
    @GET("/v2/forecast?lat=43.438155&lon=39.911147")
    fun getWeather(): Observable<ApiWeather>
    fun getTowns(): Observable<ArrayList<TownEntity>>
}