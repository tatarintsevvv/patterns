package online.tatarintsev.currencyrates.view

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import online.tatarintsev.currencyrates.model.data.CurrencyRepositoryImpl
import online.tatarintsev.currencyrates.model.interactors.CurrencyModelImpl
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository
import online.tatarintsev.currencyrates.viewmodel.CurrencyViewModel

class CurrencyViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var jsonPlaceHolderApi: JsonPlaceHolderApi = JsonPlaceHolderApi()
        var currencyRepository: CurrencyRepository = CurrencyRepositoryImpl(jsonPlaceHolderApi)
        var currencyModel: CurrencyModelImpl = CurrencyModelImpl(currencyRepository)

        if (modelClass == CurrencyViewModel::class.java) {
                var cvm: T = CurrencyViewModel(Schedulers.io(), AndroidSchedulers.mainThread(), currencyModel) as T
                return cvm
            } else {
            throw IllegalArgumentException("model class: " + modelClass)
        }
    }

}