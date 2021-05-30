package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownsListRepository

class TownsListRepositoryImpl(jsonPlaceHolderApi: JsonPlaceHolderApi): TownsListRepository {
    private val remoteDataSource: TownsListDataSource = TownsListRemoteDataSource(jsonPlaceHolderApi)

    override fun getTowns(): Observable<ArrayList<TownEntity>> {
        return remoteDataSource.getTowns()
            .map { towns ->
                return@map towns
            }
    }

}

