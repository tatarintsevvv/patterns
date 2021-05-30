package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi

class TownsListRemoteDataSource(private val jsonPlaceHolderApi: JsonPlaceHolderApi): TownsListDataSource {

    override fun getTowns(): Observable<ArrayList<TownEntity>> {

        return jsonPlaceHolderApi.getJsonplaceholderApiService().getTowns().
        map{ towns ->
            return@map towns
        }
    }

}