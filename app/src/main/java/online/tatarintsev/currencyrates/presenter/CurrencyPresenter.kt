package online.tatarintsev.currencyrates.presenter

import android.os.Bundle
import android.view.View

interface CurrencyPresenter {
    fun onCreate(savedInstanceState: Bundle?)
    fun onSaveInstanceState(outState: Bundle)

    fun onAttachView(view: CurrencyView)
    fun onDetachView()

    fun onUserAction(view: View)
}