package online.tatarintsev.mvi.view.ui

import android.os.Bundle
import android.telephony.PhoneNumberFormattingTextWatcher
import android.view.*
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.google.android.material.progressindicator.CircularProgressIndicator
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import online.tatarintsev.mvi.R
import online.tatarintsev.mvi.databinding.FragmentFirstScreenBinding

import online.tatarintsev.mvi.viewmodel.first_screen.FirstScreenViewModel
import online.tatarintsev.mvi.viewmodel.first_screen.FirstScreenViewModel.State.*
import online.tatarintsev.mvi.viewmodel.first_screen.FirstScreenViewModelFactory


/**
 * A simple [Fragment] subclass.
 * Use the [FirstScreenFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
@ExperimentalCoroutinesApi
class FirstScreenFragment : Fragment() {

    private var viewModel: FirstScreenViewModel? = null

    private var binding: FragmentFirstScreenBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        val pictureViewModelFactory = FirstScreenViewModelFactory()
        viewModel = ViewModelProvider(this, pictureViewModelFactory).get(FirstScreenViewModel::class.java)

        super.onCreate(savedInstanceState)
        viewModel?.onStart()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first_screen, container, false)
    }

    @FlowPreview
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentFirstScreenBinding.bind(view)

        (binding?.progressIndicator as CircularProgressIndicator).hide()

        val phoneEdit: TextInputEditText = binding?.phoneNumber as TextInputEditText
        phoneEdit.addTextChangedListener(PhoneNumberFormattingTextWatcher("RU"))

        binding?.repeatButton?.setOnClickListener{
            binding?.phoneNumber?.text?.let {
                val phone = it.toString()
                if(phone.isNotBlank() && phone.trim().startsWith("+7")) {
                    val phoneOnlyDigits = phone.filter { symbol -> symbol.isDigit() }
                    if(phoneOnlyDigits.length == 11) {
                        // запрос кода CМС
                        viewModel?.send(FirstScreenViewModel.Intent.RequestSMS(phoneOnlyDigits))
                    } else {
                        Snackbar.make(view, "Номер должен содержать 11 цифр", Snackbar.LENGTH_LONG).show()
                    }
                } else {
                    Snackbar.make(view, "Номер должен начинаться с +7", Snackbar.LENGTH_LONG).show()
                }
            }
        }



        viewModel?.states
            ?.onEach { state -> handleState(state) }
            ?.launchIn(lifecycleScope)

    }

    private fun handleState(state: FirstScreenViewModel.State) {
        when(state) {
            is Error -> {
                (binding?.progressIndicator as CircularProgressIndicator).hide()
                Snackbar.make(view as View, state.error.localizedMessage as String, Snackbar.LENGTH_SHORT).show()
            }
            is Loading -> {
                (binding?.progressIndicator as CircularProgressIndicator).show()
            }
            is Success -> {
                (binding?.progressIndicator as CircularProgressIndicator).hide()
                val serverResponseData = state.serverResponseSMSStatus
                if(serverResponseData.status.equals("success")) {
                    this.view?.let {
                        Navigation.findNavController(it).navigate(
                            R.id.action_first_screen_to_secondScreenFragment,
                            bundleOf()
                        )
                    }
               } else {
                    Snackbar.make(view as View, "Ошибка отправки СМС", Snackbar.LENGTH_SHORT).show()
                }
            }

        }
    }

    companion object {
         @JvmStatic
        fun newInstance() =
            FirstScreenFragment()
    }


    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }


}