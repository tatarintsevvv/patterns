package online.tatarintsev.currencyrates.presenter;

import android.os.Bundle;
import android.view.View;

import io.reactivex.disposables.Disposable;
import online.tatarintsev.currencyrates.model.CurrencyData;
import online.tatarintsev.currencyrates.model.ineractors.CurrencyModel;

public class CurrencyPresenterImpl implements CurrencyPresenter {

    private static final String SAVE_CURRENCY = "SAVE_CURRENCY";

    private CurrencyView view;
    private final CurrencyModel currencyModel;

    private CurrencyData currenciesData;

    private Disposable disposable;

    public CurrencyPresenterImpl(CurrencyModel currencyModel) {
        this.currencyModel = currencyModel;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        if(savedInstanceState != null) {
            //TODO восстанавливаем данные
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if(currenciesData != null) {
            //TODO записываю данные
        }
    }

    @Override
    public void onAttachView(CurrencyView view) {
        this.view = view;
        updateCurrencyView(view);
    }

    private void updateCurrencyView(CurrencyView view) {
        if(view == null) {
//            view.showProgress();
//            userModel.getCurrencyData().subscribe(new CurrencyObserver);
        } else {
//            view.showCurrencyData();
        }
    }

    @Override
    public void onDetachView() {
        if(disposable != null) {
            disposable.dispose();
        }

        view = null;
    }

    @Override
    public void onUserAction(View view) {
        //TODO
    }
}
