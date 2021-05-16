package online.tatarintsev.currencyrates.view

import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProvider

import online.tatarintsev.currencyrates.R
import online.tatarintsev.currencyrates.databinding.ActivityMainBinding
import online.tatarintsev.currencyrates.viewmodel.CurrencyViewModel

class CurrencyActivity: FragmentActivity() {
    var viewModel: CurrencyViewModel? = null

    private var viewBinding: ActivityMainBinding? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        // фабрика для создания ViewModel с конструктором с параметрами
        var currencyViewModelFactory: CurrencyViewModelFactory = CurrencyViewModelFactory()
        // механизм получения ViewModel
        // можно получить для активити или фрагмента
        viewModel = ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel::class.java)

        super.onCreate(savedInstanceState);

        // ViewModel не хранит данные при уничтожении процесса приложения
        viewModel!!.onCreate(savedInstanceState);
//        viewBinding = ActivityMainBinding.bind(this as View)
/*
        var binding: CurrencyActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setViewModel(viewModel)
        binding.setLifecycleOwner(this)

 */
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