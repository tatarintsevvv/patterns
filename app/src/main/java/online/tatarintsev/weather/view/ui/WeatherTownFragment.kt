package online.tatarintsev.weather.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import online.tatarintsev.weather.R
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

class WeatherTownFragment : Fragment() {

    companion object {
        fun newInstance() = WeatherTownFragment()
    }

    private lateinit var viewModel: WeatherTownViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.towns_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WeatherTownViewModel::class.java)
        // TODO: Use the ViewModel
    }

}