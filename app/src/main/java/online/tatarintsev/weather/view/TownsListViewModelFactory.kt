package online.tatarintsev.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.weather.model.data.TownsListRemoteDataSource
import online.tatarintsev.weather.model.data.TownsListRepositoryImpl
import online.tatarintsev.weather.model.interactors.TownWeatherModelImpl
import online.tatarintsev.weather.model.interactors.TownsListModelImpl
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownWeatherRepository
import online.tatarintsev.weather.model.repositories.TownsListRepository
import online.tatarintsev.weather.viewmodel.WeatherListViewModel
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

class TownsListViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var jsonPlaceHolderApi: JsonPlaceHolderApi = JsonPlaceHolderApi()
        var townsListRepository: TownsListRepository = TownsListRepositoryImpl(jsonPlaceHolderApi)
        var townsModel: TownsListModelImpl = TownsListModelImpl(townsListRepository)

        if (modelClass == WeatherListViewModel::class.java) {
                var cvm: T = WeatherListViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), townsModel) as T
                return cvm
            } else {
            throw IllegalArgumentException("model class: " + modelClass)
        }
    }

}