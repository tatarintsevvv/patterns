package online.tatarintsev.weather.model.interactors

import io.reactivex.Observable
import online.tatarintsev.weather.model.entities.TownEntity

interface TownsListModel {
    fun getTowns(): Observable<ArrayList<TownEntity>>
}