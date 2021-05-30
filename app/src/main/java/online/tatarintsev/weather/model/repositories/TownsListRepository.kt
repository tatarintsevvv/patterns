package online.tatarintsev.weather.model.repositories

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity

interface TownsListRepository {
    fun getTowns(): Observable<ArrayList<TownEntity>>
}