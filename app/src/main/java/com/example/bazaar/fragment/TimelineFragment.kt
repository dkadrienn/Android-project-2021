package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class TimelineFragment : Fragment(), RecycleViewAdapter.OnItemClickListener {
    lateinit var listViewModel: ProductListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: RecycleViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProductListViewModelFactory(MarketRepository())
        listViewModel = ViewModelProvider(this, factory).get(ProductListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_timeline, container, false)

        recycler_view = view.findViewById(R.id.timeLineRecycleView)
        setupRecyclerView()
        listViewModel.products.observe(viewLifecycleOwner) {
            adapter.setData(listViewModel.products.value as ArrayList<Product>)
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
        val productDetailFragment = ProductDetailFragment()
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
        productDetailFragment.arguments = bundle
        Log.d("OnProductClick", "Clicked" + product.price_type)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragment, productDetailFragment)?.addToBackStack(null)
            ?.commit()
    }
}