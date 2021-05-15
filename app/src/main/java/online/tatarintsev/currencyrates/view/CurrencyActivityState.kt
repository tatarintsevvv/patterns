package online.tatarintsev.currencyrates.view

import online.tatarintsev.currencyrates.model.data.CurrencyRepositoryImpl
import online.tatarintsev.currencyrates.model.ineractors.CurrencyModel
import online.tatarintsev.currencyrates.model.ineractors.CurrencyModelImpl
import online.tatarintsev.currencyrates.model.network.JsonPlaceHolderApi
import online.tatarintsev.currencyrates.model.repositories.CurrencyRepository
import online.tatarintsev.currencyrates.presenter.CurrencyPresenter
import online.tatarintsev.currencyrates.presenter.CurrencyPresenterImpl
import online.tatarintsev.currencyrates.presenter.CurrencyView

object CurrencyActivityState {

    private var presenter: CurrencyPresenter? = null


    fun getPresenter(): CurrencyPresenter {
        if(presenter == null) {
            presenter = createPresenter()


        }
        return presenter as CurrencyPresenter
    }

    private fun createPresenter(): CurrencyPresenter {
        val jsonPlaceHolderApi: JsonPlaceHolderApi = JsonPlaceHolderApi()
        val userRepository: CurrencyRepository = CurrencyRepositoryImpl(jsonPlaceHolderApi)
        val userModel: CurrencyModel = CurrencyModelImpl(userRepository)
        return CurrencyPresenterImpl(userModel)
    }

    fun onStart(view: CurrencyView) {
        presenter!!.onAttachView(view)
    }

    fun onStop() {
        presenter!!.onDetachView()
    }

    fun onDestroy() {
        presenter = null
    }
}