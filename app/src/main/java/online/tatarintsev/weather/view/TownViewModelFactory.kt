package online.tatarintsev.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.weather.model.data.models.TownWeatherRepositoryImpl
import online.tatarintsev.weather.model.interactors.TownWeatherModelImpl
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownWeatherRepository
import online.tatarintsev.weather.viewmodel.WeatherListViewModel
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

class TownViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var jsonPlaceHolderApi: JsonPlaceHolderApi = JsonPlaceHolderApi()
        var townWeatherRepository: TownWeatherRepository = TownWeatherRepositoryImpl(jsonPlaceHolderApi)
        var townListModel: TownWeatherModelImpl = TownWeatherModelImpl(townWeatherRepository)

        if (modelClass == WeatherTownViewModel::class.java) {
            var cvm: T = WeatherTownViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), townListModel) as T
            return cvm
        } else {
            throw IllegalArgumentException("model class: " + modelClass)
        }
    }

}