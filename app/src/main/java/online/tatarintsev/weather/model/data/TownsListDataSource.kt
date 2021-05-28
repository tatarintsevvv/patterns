package online.tatarintsev.weather.model.data

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity

interface TownsListDataSource {
    fun getTowns(): Observable<ArrayList<TownEntity>>
}