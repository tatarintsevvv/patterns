package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.model.repositories.TownsListRepository

class TownsListModelImpl(private val townsListRepository: TownsListRepository): TownsListModel {
    override fun getTowns(): Observable<ArrayList<TownEntity>> {
        TODO("Not yet implemented")
    }
}