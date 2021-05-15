package online.tatarintsev.currencyrates.presenter;

import android.os.Bundle;
import android.view.View;

public interface CurrencyPresenter {
    void onCreate(Bundle savedInstanceState);
    void onSaveInstanceState(Bundle outState);

    void onAttachView(CurrencyView view);
    void onDetachView();

    void onUserAction(View view);
}
