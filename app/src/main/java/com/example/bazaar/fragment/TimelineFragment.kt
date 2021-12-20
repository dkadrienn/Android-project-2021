package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
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
import java.util.*
import kotlin.collections.ArrayList

class TimelineFragment : BaseFragment(), RecycleViewAdapter.OnItemClickListener {
    private val TAG = this.javaClass.simpleName

    lateinit var listViewModel: ProductListViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: RecycleViewAdapter

    private lateinit var searchBar: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = ProductListViewModelFactory(requireContext(), MarketRepository())
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
        searchBar = view.findViewById(R.id.searchViewTimeline)
        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarTimeline)
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
                    adapter.setData(ArrayList(listViewModel.products.value?.filter {
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
                    adapter.setData(ArrayList(listViewModel.products.value?.filter {
                        it.title.toLowerCase(
                            Locale.getDefault()
                        ).contains(searchText)
                    }))
                    adapter.notifyDataSetChanged()
                }
                return false
            }
        })
    }

    override fun setTopBarElements(view: View) {
        val logo = view.findViewById<ImageView>(R.id.top_bar_logo)
        val search = view.findViewById<ImageView>(R.id.top_bar_search)
        val filter = view.findViewById<ImageView>(R.id.top_bar_filter)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        logo.visibility = View.VISIBLE
        search.visibility = View.VISIBLE
        filter.visibility = View.VISIBLE
        profile.visibility = View.VISIBLE
    }

    override fun setOnElementsClickListeners(view: View) {
        val search = view.findViewById<ImageView>(R.id.top_bar_search)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        profile.setOnClickListener {
            Log.d("OnProfileClick", "Clicked profile")
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
            "amount_type" to product.amount_type,
            "description" to product.description
        )
        productDetailFragment.arguments = bundle
        Log.d("OnProductClick", "Clicked" + product.price_type)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragment, productDetailFragment)?.addToBackStack(null)
            ?.commit()
    }
}