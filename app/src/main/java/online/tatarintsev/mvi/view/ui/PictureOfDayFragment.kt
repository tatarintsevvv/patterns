package online.tatarintsev.mvi.view.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import coil.api.load
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import online.tatarintsev.mvi.R
import online.tatarintsev.mvi.databinding.FragmentPictureOfDayBinding

import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModel
import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModel.State.*
import online.tatarintsev.mvi.viewmodel.PictureOfDayViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [PictureOfDayFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalCoroutinesApi
class PictureOfDayFragment : Fragment() {

    private var viewModel: PictureOfDayViewModel? = null

    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>

    private var binding: FragmentPictureOfDayBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val pictureViewModelFactory = PictureOfDayViewModelFactory()
        viewModel = ViewModelProvider(this, pictureViewModelFactory).get(PictureOfDayViewModel::class.java)

        super.onCreate(savedInstanceState)
        viewModel?.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_picture_of_day, container, false)
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentPictureOfDayBinding.bind(view)

        (binding?.progressIndicator as CircularProgressIndicator).hide()

        (binding?.searchTextLayout as TextInputLayout).setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://en.wikipedia.org/wiki/${(binding?.searchText as TextInputEditText).text.toString()}")
            })
        }

/*
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
*/



        viewModel?.states
            ?.onEach { state -> handleState(state) }
            ?.launchIn(lifecycleScope)


    }

    private fun handleState(state: PictureOfDayViewModel.State) {
        when(state) {
            is Error -> {
                (binding?.progressIndicator as CircularProgressIndicator).hide()
                Snackbar.make(view as View, state.error.localizedMessage.toString(), Snackbar.LENGTH_SHORT).show()

            }
            is Loading -> {
                (binding?.progressIndicator as CircularProgressIndicator).show()
            }
            is Success -> {
                (binding?.progressIndicator as CircularProgressIndicator).hide()
                val serverResponseData = state.serverResponseData
                val url = serverResponseData.url
                if (url.isNullOrEmpty()) {
                    Snackbar.make(view as View, "Пустая ссылка", Snackbar.LENGTH_LONG).show()
                } else {
                    binding?.progressIndicator?.hide()
                    val imageView: ImageView?  = binding?.imageView
                    imageView?.load(url) {
                        lifecycle(this@PictureOfDayFragment)
                    }
                }

            }

        }
    }

    companion object {
         @JvmStatic
        fun newInstance() =
            PictureOfDayFragment()
    }

    private fun setBottomSheetBehavior(bottomSheet: ConstraintLayout) {
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.botom_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.app_bar_fav -> Snackbar.make(view as View, R.string.menu_faivourite, Snackbar.LENGTH_SHORT).show()
            R.id.app_bar_search -> Snackbar.make(view as View, R.string.menu_setings, Snackbar.LENGTH_SHORT).show()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun setBottomAppBar(view: View) {
        (activity as AppCompatActivity).setSupportActionBar(binding?.bottomAppBar)
        setHasOptionsMenu(true)
    }

}