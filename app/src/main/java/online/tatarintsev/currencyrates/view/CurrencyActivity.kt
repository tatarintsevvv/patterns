package online.tatarintsev.currencyrates.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


import online.tatarintsev.currencyrates.R
import online.tatarintsev.currencyrates.viewmodel.CurrencyViewModel


class CurrencyActivity: AppCompatActivity() {
    var viewModel: CurrencyViewModel? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        // фабрика для создания ViewModel с конструктором с параметрами
        var currencyViewModelFactory: CurrencyViewModelFactory = CurrencyViewModelFactory()
        // механизм получения ViewModel
        // можно получить для активити или фрагмента
        viewModel = ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel::class.java)

        super.onCreate(savedInstanceState)

        // ViewModel не хранит данные при уничтожении процесса приложения
        viewModel!!.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    protected override fun onSaveInstanceState(outState: Bundle) {
        viewModel!!.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    public override fun onStart() {
        super.onStart()

        // у ViewModel нет событий жизненного цикла на создание
        viewModel!!.onStart()
    }

}



