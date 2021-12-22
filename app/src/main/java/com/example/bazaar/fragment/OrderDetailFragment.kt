package com.example.bazaar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.bazaar.R
import java.text.SimpleDateFormat


private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_STATUS = "status"
private const val ARG_UNIT = "unit"
private const val ARG_AMOUNT_TYPE = "amount_type"
private const val ARG_DESCRIPTION = "description"
private const val ARG_OWNER = "ownername"

class OrderDetailFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            creation_time = it.getLong(ARG_CREATION_TIME)
            title = it.getString(ARG_TITLE)
            price = it.getString(ARG_PRICE)
            price_type = it.getString(ARG_PRICE_TYPE)
            status = it.getString(ARG_STATUS)
            unit = it.getString(ARG_UNIT)
            amount_type = it.getString(ARG_AMOUNT_TYPE)
            description = it.getString(ARG_DESCRIPTION)
            owner = it.getString(ARG_OWNER)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_order_detail, container, false)
    }
}