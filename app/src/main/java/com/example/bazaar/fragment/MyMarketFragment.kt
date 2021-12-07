package com.example.bazaar.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.adapter.RecycleViewAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.ProductListViewModel
import com.example.bazaar.viewmodel.ProductListViewModelFactory

class MyMarketFragment : Fragment(), RecycleViewAdapter.OnItemClickListener {
    private val TAG = this.javaClass.simpleName
    private val sharedPrefFile = "MYSHAREDPREF"
    private val sharedPrefKey = "username"
    private var username : String? = null

    lateinit var listViewModel: ProductListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences(
            "MySharedPref",
            Context.MODE_PRIVATE
        )
        username = sharedPreferences.getString("username", "defaultname")
        Log.d(TAG, "My name is:$username")
        val factory = ProductListViewModelFactory(MarketRepository())
        listViewModel = ViewModelProvider(this, factory).get(ProductListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_market, container, false)
        recycler_view = view.findViewById(R.id.timeLineRecycleView)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner) {
            val myProducts = listViewModel.products.value!!.filter { it.username == "manyi" }
            adapter.setData( myProducts as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        return view
    }

    private fun setupRecyclerView() {
        adapter = RecycleViewAdapter(ArrayList<Product>(), this.requireContext(), this)
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

    override fun onItemClick(product: Product) {
        val myProductDetailFragment = MyProductDetailFragment()
        val bundle = bundleOf(
            "username" to product.username,
            "creation_time" to product.creation_time,
            "title" to product.title,
            "price" to product.price_per_unit,
            "price_type" to product.price_type,
            "is_active" to product.is_active,
            "unit" to product.units,
            "description" to product.description
        )
        myProductDetailFragment.arguments = bundle
        Log.d("OnProductClick", "Clicked" + product.price_type)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragment, myProductDetailFragment)?.addToBackStack(null)
            ?.commit()
    }
}