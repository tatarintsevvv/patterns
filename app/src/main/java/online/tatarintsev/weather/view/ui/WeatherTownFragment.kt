package online.tatarintsev.weather.view.ui

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import online.tatarintsev.weather.R
import online.tatarintsev.weather.databinding.FragmentDetailBinding
import online.tatarintsev.weather.databinding.TownsListFragmentBinding
import online.tatarintsev.weather.viewmodel.WeatherListViewModel
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

class WeatherTownFragment : Fragment() {


    private var viewModel: WeatherTownViewModel? = null
    private var viewBinding: FragmentDetailBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var viewBinding: FragmentDetailBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewBinding.tv = viewModel
        viewBinding.setLifecycleOwner(this)

        //подписываем фрагмент на изменения ошибки
        viewModel?.getError()?.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment WeatherListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            WeatheTownFragment().apply {
                /*
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }

                 */
            }
    }
}