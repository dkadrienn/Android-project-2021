package com.example.bazaar.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.adapter.OrderRecyclerViewAdapter
import com.example.bazaar.model.Order
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import com.example.bazaar.viewmodel.OrderViewModel
import com.example.bazaar.viewmodel.OrderViewModelFactory
import java.util.*
import kotlin.collections.ArrayList


class MyFaresFragment : BaseFragment(), OrderRecyclerViewAdapter.OnItemClickListener {
    private val TAG = this.javaClass.simpleName
    private var myName: String? = null

    lateinit var listViewModel: OrderViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: OrderRecyclerViewAdapter

    private lateinit var searchBar: SearchView

    private lateinit var mySales: List<Order>
    private lateinit var myOrders: List<Order>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = OrderViewModelFactory(requireContext(), MarketRepository())
        listViewModel = ViewModelProvider(this, factory).get(OrderViewModel::class.java)
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE
        )
        myName = sharedPreferences.getString(Constants.sharedPrefKeyUsername, "defaultname")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_fares, container, false)

        recycler_view = view.findViewById(R.id.myFaresRecycleView)
        setupRecyclerView()
        listViewModel.orders.observe(viewLifecycleOwner) {
            mySales = listViewModel.orders.value!!.reversed().filter { it.owner_username == myName }
            adapter.setData(mySales as ArrayList<Order>)
            adapter.notifyDataSetChanged()
        }
        searchBar = view.findViewById(R.id.searchViewMyFares)
        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarMyFares)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                Log.d(TAG, searchText)
                if (searchText.isNotEmpty()) {
                    adapter.setData(ArrayList(listViewModel.orders.value?.filter {
                        it.title.toLowerCase(
                            Locale.getDefault()
                        ).contains(searchText)
                    }))
                    adapter.notifyDataSetChanged()
                }
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                Log.d(TAG, searchText)
                if (searchText.isNotEmpty()) {
                    adapter.setData(ArrayList(listViewModel.orders.value?.filter {
                        it.title.toLowerCase(
                            Locale.getDefault()
                        ).contains(searchText)
                    }))
                    adapter.notifyDataSetChanged()
                }
                return false
            }
        })


        //set buttons click listener
        val ongoingSalesButton = view.findViewById<Button>(R.id.ongoingSalesButtonMyFares)
        val ongoingOrdersButton = view.findViewById<Button>(R.id.ongoingOrdersButtonMyFares)

        ongoingSalesButton.setOnClickListener {
            listViewModel.orders.observe(viewLifecycleOwner) {
                mySales =
                    listViewModel.orders.value!!.reversed().filter { it.owner_username == myName }
                adapter.setData(mySales as ArrayList<Order>)
                adapter.notifyDataSetChanged()
            }
        }

        ongoingOrdersButton.setOnClickListener {
            listViewModel.orders.observe(viewLifecycleOwner) {
                myOrders = listViewModel.orders.value!!.reversed().filter { it.username == myName }
                adapter.setData(myOrders as ArrayList<Order>)
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val search = view.findViewById<ImageView>(R.id.top_bar_search)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.visibility = View.VISIBLE
        search.visibility = View.VISIBLE
        title.apply { text = context.getString(R.string.myfares) }
        profile.visibility = View.VISIBLE
    }

    override fun setOnElementsClickListeners(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val search = view.findViewById<ImageView>(R.id.top_bar_search)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.setOnClickListener {
            Log.d(TAG, "Clicked back")
            activity?.supportFragmentManager?.popBackStack()
        }
        profile.setOnClickListener {
            Log.d(TAG, "Clicked profile")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, ProfileFragment())?.addToBackStack(null)
                ?.commit()
        }
        search.setOnClickListener {
            Log.d(TAG, "Clicked search")
            searchBar.visibility = View.VISIBLE
        }
    }

    private fun setupRecyclerView() {
        adapter = OrderRecyclerViewAdapter(ArrayList<Order>(), this.requireContext(), this)
        recycler_view.adapter = adapter
        recycler_view.layoutManager = LinearLayoutManager(this.context)
        recycler_view.addItemDecoration(
            DividerItemDecoration(
                activity,
                DividerItemDecoration.VERTICAL
            )
        )
        recycler_view.setHasFixedSize(true)
    }

    override fun onItemClick(order: Order) {
        val orderDetailFragment = ProductDetailFragment()
        val bundle = bundleOf(
            "username" to order.username.replace("\"", ""),
            "creation_time" to order.creation_time,
            "title" to order.title.replace("\"", ""),
            "price" to order.price_per_unit.replace("\"", ""),
            "price_type" to order.price_type.replace("\"", ""),
            "status" to order.status.replace("\"", ""),
            "unit" to order.units.replace("\"", ""),
            "amount_type" to order.amount_type.replace("\"", ""),
            "description" to order.description.replace("\"", "")
        )
        orderDetailFragment.arguments = bundle
        Log.d("OnProductClick", "Clicked" + order.price_type)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragment, orderDetailFragment)?.addToBackStack(null)
            ?.commit()
    }
}