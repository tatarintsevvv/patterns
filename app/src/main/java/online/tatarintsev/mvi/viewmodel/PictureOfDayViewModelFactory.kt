package online.tatarintsev.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PictureOfDayViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return PictureOfDayViewModel(
            /*
            Schedulers.io(),
            AndroidSchedulers.mainThread(),
            currencyModel
             */
        ) as T

    }
}
