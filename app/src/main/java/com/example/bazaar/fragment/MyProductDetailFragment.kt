package com.example.bazaar.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bazaar.R

private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_IS_ACTIVE = "is_active"
private const val ARG_UNIT = "unit"
private const val ARG_DESCRIPTION = "description"

class MyProductDetailFragment : Fragment() {
    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var is_active: Boolean = false
    private var unit: String? = null
    private var description: String? = null

    private lateinit var usernameTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var priceTypeTextView: TextView
    private lateinit var isActiveImageView: ImageView
    private lateinit var unitTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_my_product_detail, container, false)
    }

}