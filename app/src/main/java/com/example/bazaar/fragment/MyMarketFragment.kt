package com.example.bazaar.fragment

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SearchView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bazaar.R
import com.example.bazaar.adapter.RecycleViewAdapter
import com.example.bazaar.model.Product
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import com.example.bazaar.viewmodel.ProductListViewModel
import com.example.bazaar.viewmodel.ProductListViewModelFactory
import com.example.bazaar.viewmodel.RemoveProductViewModel
import com.example.bazaar.viewmodel.RemoveProductViewModelFactory
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class MyMarketFragment : BaseFragment(), RecycleViewAdapter.OnItemClickListener,
    RecycleViewAdapter.OnItemLongClickListener {
    private val TAG = this.javaClass.simpleName
    private var myName: String? = null

    lateinit var listViewModel: ProductListViewModel
    lateinit var removeViewModel: RemoveProductViewModel
    private lateinit var recycler_view: RecyclerView
    private lateinit var adapter: RecycleViewAdapter

    private lateinit var searchBar: SearchView

    private lateinit var myProducts: List<Product>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE
        )
        myName = sharedPreferences.getString(Constants.sharedPrefKeyUsername, "defaultname")
        Log.d(TAG, "My name is:$myName")
        val factory = ProductListViewModelFactory(requireContext(), MarketRepository())
        listViewModel = ViewModelProvider(this, factory).get(ProductListViewModel::class.java)

        val factoryRemove = RemoveProductViewModelFactory(requireContext(), MarketRepository())
        removeViewModel =
            ViewModelProvider(this, factoryRemove).get(RemoveProductViewModel::class.java)
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
            myProducts = listViewModel.products.value!!.reversed().filter { it.username == myName }
            adapter.setData(myProducts as ArrayList<Product>)
            adapter.notifyDataSetChanged()
        }
        searchBar = view.findViewById(R.id.searchViewMyMarket)
        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarMyMarket)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val search = view.findViewById<ImageView>(R.id.top_bar_search)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.visibility = View.VISIBLE
        search.visibility = View.VISIBLE
        title.apply { text = context.getString(R.string.mymarket) }
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val addButton = view.findViewById<ImageView>(R.id.addButton)
        val addProductFragment = AddProductFragment()
        addButton.setOnClickListener {
            Log.d(TAG, "Clicked add")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, addProductFragment)?.addToBackStack(null)
                ?.commit()
        }

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                Log.d(TAG, searchText)
                if (searchText.isNotEmpty()) {
                    adapter.setData(ArrayList(myProducts.filter {
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
                    adapter.setData(ArrayList(myProducts.filter {
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

    private fun setupRecyclerView() {
        adapter = RecycleViewAdapter(ArrayList<Product>(), this.requireContext(), this, this)
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
        Log.d(TAG, "Click simple")
        val myProductDetailFragment = MyProductDetailFragment()
        val bundle = bundleOf(
            "username" to product.username.replace("\"", ""),
            "creation_time" to product.creation_time,
            "title" to product.title.replace("\"", ""),
            "price" to product.price_per_unit.replace("\"", ""),
            "price_type" to product.price_type.replace("\"", ""),
            "is_active" to product.is_active,
            "unit" to product.units.replace("\"", ""),
            "amount_type" to product.amount_type.replace("\"", ""),
            "description" to product.description.replace("\"", ""),
            "rating" to product.rating,
            "product_id" to product.product_id.replace("\"", "")
        )
        myProductDetailFragment.arguments = bundle
        Log.d("OnProductClick", "Clicked" + product.product_id)
        activity?.supportFragmentManager?.beginTransaction()
            ?.replace(R.id.mainFragment, myProductDetailFragment)?.addToBackStack(null)
            ?.commit()
    }

    override fun onItemLongClick(product: Product) {
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Delete")
        builder.setMessage("Are you sure you want to delete this product?")
        builder.setPositiveButton(android.R.string.yes) { dialog, which ->
            removeViewModel.removed.value.let {
                if (it != null)
                    it.product_id = product.product_id
            }
            lifecycleScope.launch {
                removeViewModel.removeProduct()
                adapter.notifyDataSetChanged()
//            activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.mainFragment, TimelineFragment())?.addToBackStack(null)
//            ?.commit()
            }
        }
//        activity?.supportFragmentManager?.beginTransaction()
//            ?.replace(R.id.mainFragment, TimelineFragment())?.addToBackStack(null)
//            ?.commit()

        builder.setNegativeButton(android.R.string.no) { dialog, which ->
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, MyMarketFragment())?.addToBackStack(null)
                ?.commit()
        }
        builder.show()
        Log.d(TAG, "Click long")
    }
}