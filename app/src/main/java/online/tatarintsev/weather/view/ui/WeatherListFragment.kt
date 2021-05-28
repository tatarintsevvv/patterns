package online.tatarintsev.weather.view.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import online.tatarintsev.weather.R
import online.tatarintsev.weather.databinding.TownsListFragmentBinding
import online.tatarintsev.weather.model.entities.TownEntity
import online.tatarintsev.weather.view.TownsListViewModelFactory
import online.tatarintsev.weather.viewmodel.WeatherListViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [WeatherListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WeatherListFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null
    var listViewModel: WeatherListViewModel? = null
    private var viewBinding: TownsListFragmentBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        var townsListViewModelFactory: TownsListViewModelFactory = TownsListViewModelFactory()
        listViewModel = ViewModelProvider(this, townsListViewModelFactory).get(WeatherListViewModel::class.java)
        super.onCreate(savedInstanceState)
        /*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

         */
        listViewModel!!.onCreate(savedInstanceState)
        listViewModel!!.onStart()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var viewBinding: TownsListFragmentBinding =  DataBindingUtil.inflate(inflater, R.layout.towns_list_fragment, container, false)
        viewBinding.ce = listViewModel
        viewBinding.setLifecycleOwner(this)

        viewBinding.listTowns.layoutManager = LinearLayoutManager(this.context)
        val adapter = RecyclerViewAdapter(listViewModel?.getTowns()?.value)
        viewBinding.listTowns.adapter = adapter

        viewBinding.listTowns.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        //подписываем адаптер на изменения списка
        listViewModel?.getTowns()?.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                adapter.refreshTowns(it)
            }
        })

        //подписываем фрагмент на изменения ошибки
        listViewModel?.getError()?.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG).show()
            }
        })

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
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment WeatherListFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                WeatherListFragment().apply {
                    /*
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }

                     */
                }
    }
}
class RecyclerViewAdapter(var items : ArrayList<TownEntity>?) : RecyclerView.Adapter<ViewHolder>() {

    // Gets the number of countries in the list
    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    // Inflates the item views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false))
    }

    // Binds each country in the ArrayList to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.rate_name.setText(items?.get(position)?.name)
    }

    fun refreshTowns(towns: ArrayList<TownEntity>) {
        this.items = towns
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each country to
    val rate_name: TextView = view.findViewById(R.id.town_name) as TextView
}