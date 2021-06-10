package online.tatarintsev.weather.view.ui

import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.github.twocoffeesoneteam.glidetovectoryou.GlideToVectorYou
import online.tatarintsev.weather.R
import online.tatarintsev.weather.databinding.FragmentDetailBinding
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.view.TownViewModelFactory
import online.tatarintsev.weather.viewmodel.WeatherTownViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM_TOWN_ENTITY = "townEntity"

class WeatherTownFragment : Fragment() {

    private var townEntity: TownEntity? = null

    private var viewModel: WeatherTownViewModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val townViewModelFactory = TownViewModelFactory()
        viewModel = ViewModelProvider(this, townViewModelFactory).get(WeatherTownViewModel::class.java)
        super.onCreate(savedInstanceState)

        arguments?.let {
            townEntity = it.getParcelable(ARG_PARAM_TOWN_ENTITY)
        }

        viewModel?.onCreate(savedInstanceState)
        viewModel?.townChosen = townEntity
        viewModel?.onStart()


    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewBinding: FragmentDetailBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        viewBinding.tv = viewModel
        viewBinding.lifecycleOwner = this

        //подписываем фрагмент на изменения ошибки
        viewModel?.getError()?.observe(this.viewLifecycleOwner, {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

        viewModel?.getWeather()?.observe(this.viewLifecycleOwner) {
            it?.fact?.icon?.let {
                GlideToVectorYou.justLoadImage(
                    activity,
                    Uri.parse("https://yastatic.net/weather/i/icons/blueye/color/svg/${it}.svg"),
                    viewBinding.townWeatherIcon
                )

            }
        }

        return viewBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
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
        fun newInstance(townEntity: TownEntity) =
            WeatherTownFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(ARG_PARAM_TOWN_ENTITY, townEntity)
                }
            }
    }
}