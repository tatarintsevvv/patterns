package online.tatarintsev.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.weather.model.data.TownsListRepositoryImpl
import online.tatarintsev.weather.model.interactors.TownsListModelImpl
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownsListRepository
import online.tatarintsev.weather.viewmodel.WeatherListViewModel

class TownsListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val jsonPlaceHolderApi = JsonPlaceHolderApi()
        val townsListRepository: TownsListRepository = TownsListRepositoryImpl(jsonPlaceHolderApi)
        val townsModel = TownsListModelImpl(townsListRepository)

        if (modelClass == WeatherListViewModel::class.java) {
            return WeatherListViewModel(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                townsModel
            ) as T
        } else {
            throw IllegalArgumentException("model class: $modelClass")
        }
    }

}