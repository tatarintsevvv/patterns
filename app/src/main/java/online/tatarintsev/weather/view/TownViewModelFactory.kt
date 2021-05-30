package online.tatarintsev.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.weather.model.data.TownWeatherRepositoryImpl
import online.tatarintsev.weather.model.interactors.TownWeatherModelImpl
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.TownWeatherRepository
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

class TownViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val jsonPlaceHolderApi = JsonPlaceHolderApi()
        val townWeatherRepository: TownWeatherRepository = TownWeatherRepositoryImpl(jsonPlaceHolderApi)
        val townListModel = TownWeatherModelImpl(townWeatherRepository)

        if (modelClass == WeatherTownViewModel::class.java) {
            return WeatherTownViewModel(
                Schedulers.io(),
                AndroidSchedulers.mainThread(),
                townListModel
            ) as T
        } else {
            throw IllegalArgumentException("model class: $modelClass")
        }
    }

}