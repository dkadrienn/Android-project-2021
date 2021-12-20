package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import com.example.bazaar.R
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_IS_ACTIVE = "is_active"
private const val ARG_UNIT = "unit"
private const val ARG_DESCRIPTION = "description"
private const val ARG_RATING = "rating"

class MyProductDetailFragment : BaseFragment() {
    private val TAG = this.javaClass.simpleName

    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var is_active: Boolean = false
    private var unit: String? = null
    private var description: String? = null
    private var rating: Double? = null

    private lateinit var usernameTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var priceTypeTextView: TextView
    private lateinit var isActiveImageView: ImageView
    private lateinit var unitTextView: TextView
    private lateinit var priceAmountTextView: TextView
    private lateinit var descriptionTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            Log.d("bundle", it.toString())
            username = it.getString(ARG_USERNAME)
            creation_time = it.getLong(ARG_CREATION_TIME)
            title = it.getString(ARG_TITLE)
            price = it.getString(ARG_PRICE)
            price_type = it.getString(ARG_PRICE_TYPE)
            is_active = it.getBoolean(ARG_IS_ACTIVE)
            unit = it.getString(ARG_UNIT)
            description = it.getString(ARG_DESCRIPTION)
            rating = it.getDouble(ARG_RATING)
//            images = it.getStringArrayList(ARG_IMAGES)
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_my_product_detail, container, false)
        usernameTextView = view.findViewById(R.id.sellerNameMyDetailPage)
        dateTextView = view.findViewById(R.id.creationDateMyDetailPage)
        titleTextView = view.findViewById(R.id.productNameMyDetailPage)
        priceTextView = view.findViewById(R.id.amountPriceMyDetailPage)
        priceTypeTextView = view.findViewById(R.id.priceTypeMyDetailPage)
        isActiveImageView = view.findViewById(R.id.productStateMyDetailPage)
        unitTextView = view.findViewById(R.id.amountMyDetailPage)
        priceAmountTextView = view.findViewById(R.id.priceAmountMyDetailPage)
        descriptionTextView = view.findViewById(R.id.detailMyDetailPage)

        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarMyMarketDetail)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        title.apply { text = context.getString(R.string.product_detail) }
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
        usernameTextView.apply { text = username }
        val date = Date(creation_time!!)
        val format = SimpleDateFormat("yyyy.MM.dd")
        dateTextView.apply { text = format.format(date).toString() }
        titleTextView.apply { text = title }
        priceTextView.apply { text = price }
        priceTypeTextView.apply { text = price_type }
        priceAmountTextView.apply { text = price + price_type }
        if (is_active) {
            isActiveImageView.setImageResource(R.drawable.ic_active)
        } else {
            isActiveImageView.setImageResource(R.drawable.ic_inactive)
        }
        unitTextView.apply { text = unit }
        descriptionTextView.apply { text = description }

        val editProductFragment = EditProductFragment()
        val bundle = bundleOf(
            "username" to username,
            "creation_time" to creation_time,
            "title" to title,
            "price" to price,
            "price_type" to price_type,
            "is_active" to is_active,
            "unit" to unit,
            "description" to description,
            "rating" to rating
        )
        editProductFragment.arguments = bundle

        val editButton: ImageView = view.findViewById(R.id.editButtonMyProduct)
        editButton.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, editProductFragment)?.addToBackStack(null)
                ?.commit()
        }
    }
}