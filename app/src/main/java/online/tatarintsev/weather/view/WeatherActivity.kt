package online.tatarintsev.weather.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider


import online.tatarintsev.weather.R
import online.tatarintsev.weather.viewmodel.WeatherListViewModel


class WeatherActivity: AppCompatActivity() {
    var listViewModel: WeatherListViewModel? = null

    protected override fun onCreate(savedInstanceState: Bundle?) {
        // фабрика для создания ViewModel с конструктором с параметрами
        var townsListViewModelFactory: TownsListViewModelFactory = TownsListViewModelFactory()
        // механизм получения ViewModel
        // можно получить для активити или фрагмента
        listViewModel = ViewModelProvider(this, townsListViewModelFactory).get(WeatherListViewModel::class.java)

        super.onCreate(savedInstanceState)

        // ViewModel не хранит данные при уничтожении процесса приложения
        listViewModel!!.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
    }

    protected override fun onSaveInstanceState(outState: Bundle) {
        listViewModel!!.onSaveInstanceState(outState)
        super.onSaveInstanceState(outState)
    }

    public override fun onStart() {
        super.onStart()

        // у ViewModel нет событий жизненного цикла на создание
        listViewModel!!.onStart()
    }

}



