package online.tatarintsev.currencyrates.view.ui

import android.content.Context
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
import online.tatarintsev.currencyrates.R
import online.tatarintsev.currencyrates.databinding.FragmentRatesBinding
import online.tatarintsev.currencyrates.model.data.models.ApiRate
import online.tatarintsev.currencyrates.model.entities.CurrencyEntity
import online.tatarintsev.currencyrates.view.CurrencyViewModelFactory
import online.tatarintsev.currencyrates.viewmodel.CurrencyViewModel

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RatesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class RatesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null
    var viewModel: CurrencyViewModel? = null
    private var viewBinding: FragmentRatesBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        var currencyViewModelFactory: CurrencyViewModelFactory = CurrencyViewModelFactory()
        viewModel = ViewModelProvider(this, currencyViewModelFactory).get(CurrencyViewModel::class.java)
        super.onCreate(savedInstanceState)
        /*
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

         */
        viewModel!!.onCreate(savedInstanceState)
        viewModel!!.onStart()


    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        var viewBinding: FragmentRatesBinding =  DataBindingUtil.inflate(inflater, R.layout.fragment_rates, container, false)
        viewBinding.ce = viewModel
        viewBinding.setLifecycleOwner(this)

        viewBinding.ratesView.layoutManager = LinearLayoutManager(this.context)
        val adapter = RecyclerViewAdapter(viewModel?.getCurrencies()?.value?.rates)
        viewBinding.ratesView.adapter = adapter

        viewBinding.ratesView.apply {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }

        //подписываем адаптер на изменения списка
        viewModel?.getCurrencies()?.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                adapter.refreshRates(it)
            }
        })

        //подписываем адаптер на изменения ошибки
        viewModel?.getError()?.observe(this.viewLifecycleOwner, Observer {
            it?.let {
                Toast.makeText(this.context, it, Toast.LENGTH_LONG)
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
         * @return A new instance of fragment RatesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                RatesFragment().apply {
                    /*
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }

                     */
                }
    }
}
class RecyclerViewAdapter(var items : ArrayList<ApiRate>?) : RecyclerView.Adapter<ViewHolder>() {

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
        holder?.buy_value.setText(items?.get(position)?.buy)
        holder?.sell_value.setText(items?.get(position)?.sell)
    }

    fun refreshRates(currency: CurrencyEntity) {
        this.items = currency.rates
        notifyDataSetChanged()
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each country to
    val rate_name: TextView = view.findViewById(R.id.rate_name) as TextView
    val buy_value: TextView = view.findViewById(R.id.buy_value) as TextView
    val sell_value: TextView = view.findViewById(R.id.sell_value) as TextView
}