package com.example.bazaar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.AddOrderViewModel
import com.example.bazaar.viewmodel.AddOrderViewModelFactory
import com.example.bazaar.viewmodel.LogInViewModel
import com.example.bazaar.viewmodel.LogInViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat


private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_UNIT = "unit"

class AddOrderFragment : Fragment() {
    val TAG = Class::class.java.simpleName
    private lateinit var addOrderViewModel: AddOrderViewModel

    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var unit: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            creation_time = it.getLong(ARG_CREATION_TIME)
            title = it.getString(ARG_TITLE)
            price = it.getString(ARG_PRICE)
            price_type = it.getString(ARG_PRICE_TYPE)
            unit = it.getString(ARG_UNIT)
        }

        val factory = AddOrderViewModelFactory(this.requireContext(), MarketRepository())
        addOrderViewModel = ViewModelProvider(this, factory).get(AddOrderViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_order, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userNameTextView = view.findViewById<TextView>(R.id.ownerNameAddO)
        val dateTextView = view.findViewById<TextView>(R.id.creationDateAddO)
        val titleTextView = view.findViewById<TextView>(R.id.itemTitleAddO)
        val priceTextView = view.findViewById<TextView>(R.id.priceAddO)

        val closeImageView = view.findViewById<ImageView>(R.id.closeAddO)

        val sendOrderButton = view.findViewById<Button>(R.id.sendMyOrderAddO)

        val amountEditText: TextInputEditText = view.findViewById(R.id.amountTextAddO)
        val detailEditText: TextInputEditText = view.findViewById(R.id.detailTextAddO)

        userNameTextView.text = username
        titleTextView.text = title
        priceTextView.text = price + price_type
        dateTextView.apply {
            text = SimpleDateFormat("yyyy.MM.dd").format(creation_time).toString()
        }

        closeImageView.setOnClickListener {
            activity?.supportFragmentManager?.popBackStack()
        }

        sendOrderButton.setOnClickListener {
            if(amountEditText.text.toString().toInt() > unit!!.toInt()){
                Toast.makeText(context, "Wow, way too much amount!", Toast.LENGTH_LONG).show()
            }
            else{
                addOrderViewModel.newOrder.value.let {
                    if (it != null) {
                        it.title = title!!
                        it.description = detailEditText.text.toString()
                        it.price_per_unit = price!!
                        it.units = unit!!
                        it.owner_username = username!!
                    }
                }
                lifecycleScope.launch {
                    addOrderViewModel.addOrder()
                }
                activity?.supportFragmentManager?.popBackStack()
            }
        }
    }
}