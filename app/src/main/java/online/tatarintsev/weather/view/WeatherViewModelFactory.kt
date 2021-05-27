package online.tatarintsev.weather.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.weather.model.data.WeatherRepositoryImpl
import online.tatarintsev.weather.model.interactors.WeatherModelImpl
import online.tatarintsev.weather.model.network.JsonPlaceHolderApi
import online.tatarintsev.weather.model.repositories.WeatherRepository
import online.tatarintsev.weather.viewmodel.WeatherListViewModel

class WeatherViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var jsonPlaceHolderApi: JsonPlaceHolderApi = JsonPlaceHolderApi()
        var weatherRepository: WeatherRepository = WeatherRepositoryImpl(jsonPlaceHolderApi)
        var currencyModel: WeatherModelImpl = WeatherModelImpl(weatherRepository)

        if (modelClass == WeatherListViewModel::class.java) {
                var cvm: T = WeatherListViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), currencyModel) as T
                return cvm
            } else {
            throw IllegalArgumentException("model class: " + modelClass)
        }
    }

}