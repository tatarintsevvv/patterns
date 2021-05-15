package online.tatarintsev.currencyrates.presenter

import android.os.Bundle
import android.view.View
import io.reactivex.disposables.Disposable
import online.tatarintsev.currencyrates.model.CurrencyData
import online.tatarintsev.currencyrates.model.ineractors.CurrencyModel

class CurrencyPresenterImpl(val currencyModel: CurrencyModel): CurrencyPresenter {
    private val SAVE_CURRENCY: String = "SAVE_CURRENCY"

    private var view: CurrencyView? = null

    private lateinit var currenciesData: CurrencyData

    private lateinit var disposable: Disposable

    public override fun onCreate(savedInstanceState: Bundle?) {
        if(savedInstanceState != null) {
            //TODO восстанавливаем данные
        }
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        if(currenciesData != null) {
            //TODO записываю данные
        }
    }

    public override fun onAttachView(view: CurrencyView) {
        this.view = view
        updateCurrencyView(view)
    }

    private fun updateCurrencyView(view: CurrencyView) {
        if(view == null) {
//            view.showProgress()
//            userModel.getCurrencyData().subscribe(new CurrencyObserver)
        } else {
//            view.showCurrencyData()
        }
    }

    public override fun onDetachView() {
        if(disposable != null) {
            disposable.dispose()
        }

        view = null;
    }

    public override fun onUserAction(view: View) {
        //TODO
    }

}