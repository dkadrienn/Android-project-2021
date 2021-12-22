package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
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

class OrderDetailFragment : BaseFragment() {
    private val TAG = this.javaClass.simpleName

    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var status: String? = null
    private var unit: String? = null
    private var amount_type: String? = null
    private var description: String? = null
    private var owner: String? = null

    private lateinit var usernameTextView: TextView
    private lateinit var ownerTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var priceTypeTextView: TextView
    private lateinit var statusTextView: TextView
    private lateinit var unitTextView: TextView
    private lateinit var priceAmountTextView: TextView
    private lateinit var descriptionTextView: TextView

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
        val view = inflater.inflate(R.layout.fragment_order_detail, container, false)
        usernameTextView = view.findViewById(R.id.nameDetailPageOrder)
        ownerTextView = view.findViewById(R.id.sellerNameDetailPageOrder)
        dateTextView = view.findViewById(R.id.creationDateDetailPageOrder)
        titleTextView = view.findViewById(R.id.productNameDetailPageOrder)
        priceTextView = view.findViewById(R.id.priceDetailPageOrder)
        statusTextView = view.findViewById(R.id.statusDetailOrder)
        unitTextView = view.findViewById(R.id.amountDetailPageOrder)
        descriptionTextView = view.findViewById(R.id.detailDetailPageOrder)

        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarProductDetailOrder)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        title.apply { text = context.getString(R.string.order_detail) }
        backArrow.visibility = View.VISIBLE
    }

    override fun setOnElementsClickListeners(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        backArrow.setOnClickListener {
            Log.d(TAG, "Clicked back")
            activity?.supportFragmentManager?.popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTextView.text = username
        ownerTextView.text = owner
        val format = SimpleDateFormat("yyyy.MM.dd")
        dateTextView.apply { text = format.format(creation_time).toString() }
        titleTextView.text = title
        priceTextView.text = price + price_type
        statusTextView.text = status
        unitTextView.text = unit + amount_type
        descriptionTextView.text = description
    }
}