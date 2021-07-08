package online.tatarintsev.mvi.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import online.tatarintsev.mvi.R
import online.tatarintsev.mvi.databinding.FragmentPictureOfDayBinding
import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModel
import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModel.State.*
import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModelFactory

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [PictureOfDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalCoroutinesApi
class PictureOfDayFragment : Fragment() {
    // TODO: Rename and change types of parameters
//    private var param1: String? = null
//    private var param2: String? = null

    private var viewModel: PictureOfDayViewModel? = null

    private lateinit var binding: FragmentPictureOfDayBinding

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun onCreate(savedInstanceState: Bundle?) {
        val pictureViewModelFactory = PictureOfDayViewModelFactory()
        viewModel = ViewModelProvider(this, pictureViewModelFactory).get(PictureOfDayViewModel::class.java)

        super.onCreate(savedInstanceState)
        /*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
         */
//        viewModel?.onCreate(savedInstanceState)
        viewModel?.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
//        DataBindingUtil.inflate(inflater, R.layout.fragment_picture_of_day, container, false)
//        val view: View = binding.root
        return inflater.inflate(R.layout.fragment_picture_of_day, container, false)
    }

    // пока не понля, почему не в onCreateView
    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPictureOfDayBinding.bind(view)
        viewModel?.states
            ?.onEach { state -> handleState(state) }
            ?.launchIn(lifecycleScope)

        binding.searchTextLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${binding.searchText.text.toString()}")
            })
        }

        setBottomSheetBehavior(view.findViewById(R.id.bottom_sheet_container))

        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_DRAGGING -> TODO("not implemented")
                    BottomSheetBehavior.STATE_COLLAPSED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> TODO("not implemented")
                    BottomSheetBehavior.STATE_HIDDEN -> TODO("not implemented")
                    BottomSheetBehavior.STATE_SETTLING -> TODO("not implemented")
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                TODO("not implemented")
            }
        })

    }

    private fun handleState(state: PictureOfDayViewModel.State) {
        when(state) {
            is Error -> {            }
            is Loading -> {}
            is Success -> {
                val serverResponseData = state.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    //Отобразите ошибку
                    //showError("Сообщение, что ссылка пустая")
                } else {
                    //Отобразите фото
                    //showSuccess()
                    //Coil в работе: достаточно вызвать у нашего ImageView
                    //нужную extension-функцию и передать ссылку и заглушки для placeholder
                        val imageView: ImageView?  = this.view?.findViewById(R.id.image_view)
                    imageView?.load(url) {
                        lifecycle(this@PictureOfDayFragment)
                //                        error(R.drawable.ic_load_error_vector)
                //                        placeholder(R.drawable.ic_no_photo_vector)
                    }
                }

            }

        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment PictureOfDayFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            PictureOfDayFragment().apply {
                /*
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
                 */
            }
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

}