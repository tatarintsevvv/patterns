package online.tatarintsev.mvi.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import online.tatarintsev.mvi.R
import online.tatarintsev.mvi.databinding.MenuBottomLayoutBinding

class BottomNavigationDrawerFragment : BottomSheetDialogFragment() {

    private var binding: MenuBottomLayoutBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_bottom_layout, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = MenuBottomLayoutBinding.bind(view)

        binding?.navigationView?.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.navigation_one -> Snackbar.make(view, "1", Snackbar.LENGTH_SHORT).show()
                R.id.navigation_two -> Snackbar.make(view, "2", Snackbar.LENGTH_SHORT).show()
            }
            true
        }
    }
}
