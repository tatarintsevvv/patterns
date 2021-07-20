package online.tatarintsev.mvi.viewmodel.first_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class FirstScreenViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        return FirstScreenViewModel(

        ) as T

    }
}
