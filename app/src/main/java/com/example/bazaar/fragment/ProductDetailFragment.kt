package com.example.bazaar.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import com.example.bazaar.R
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import com.example.bazaar.viewmodel.OtherUserViewModel
import com.example.bazaar.viewmodel.OtherUserViewModelFactory
import java.text.SimpleDateFormat
import java.util.*

private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_IS_ACTIVE = "is_active"
private const val ARG_UNIT = "unit"
private const val ARG_AMOUNT_TYPE = "amount_type"
private const val ARG_DESCRIPTION = "description"

class ProductDetailFragment : BaseFragment() {
    private val TAG = this.javaClass.simpleName

    private lateinit var otherUserViewModel: OtherUserViewModel

    //other user
    var otherUserEmail: String? = null
    var otherUserPhoneNr: Int? = null

    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var is_active: Boolean = false
    private var unit: String? = null
    private var amount_type: String? = null
    private var description: String? = null

    private lateinit var usernameTextView: TextView
    private lateinit var dateTextView: TextView
    private lateinit var titleTextView: TextView
    private lateinit var priceTextView: TextView
    private lateinit var priceTypeTextView: TextView
    private lateinit var isActiveImageView: ImageView
    private lateinit var unitTextView: TextView
    private lateinit var descriptionTextView: TextView
    private lateinit var orderImageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = OtherUserViewModelFactory(this.requireContext(), MarketRepository())
        otherUserViewModel = ViewModelProvider(this, factory).get(OtherUserViewModel::class.java)

        arguments?.let {
            Log.d("bundle", it.toString())
            username = it.getString(ARG_USERNAME)
            creation_time = it.getLong(ARG_CREATION_TIME)
            title = it.getString(ARG_TITLE)
            price = it.getString(ARG_PRICE)
            price_type = it.getString(ARG_PRICE_TYPE)
            is_active = it.getBoolean(ARG_IS_ACTIVE)
            unit = it.getString(ARG_UNIT)
            amount_type = it.getString(ARG_AMOUNT_TYPE)
            description = it.getString(ARG_DESCRIPTION)
//            images = it.getStringArrayList(ARG_IMAGES)
        }
        val sharedPreferences: SharedPreferences = requireContext().getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE
        )
        val edit = sharedPreferences.edit()
        edit.putString(Constants.sharedPrefKeyUser, username)
        edit.apply()
        edit.commit()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_product_detail, container, false)
        usernameTextView = view.findViewById(R.id.sellerNameDetailPage)
        dateTextView = view.findViewById(R.id.creationDateDetailPage)
        titleTextView = view.findViewById(R.id.productNameDetailPage)
        priceTextView = view.findViewById(R.id.amountPriceDetailPage)
        priceTypeTextView = view.findViewById(R.id.priceTypeDetailPage)
        isActiveImageView = view.findViewById(R.id.productStateDetailPage)
        unitTextView = view.findViewById(R.id.amountDetailPage)
        descriptionTextView = view.findViewById(R.id.detailDetailPage)

        otherUserViewModel.user.observe(viewLifecycleOwner) {
            otherUserEmail = otherUserViewModel.user.value!!.email
            otherUserPhoneNr = otherUserViewModel.user.value!!.phone_number
        }

        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarProductDetail)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        title.apply { text = context.getString(R.string.product_detail) }
        backArrow.visibility = View.VISIBLE
        profile.visibility = View.VISIBLE
    }

    override fun setOnElementsClickListeners(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
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
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usernameTextView.apply { text = username }
        val date = Date(creation_time!!)
        val format = SimpleDateFormat("yyyy.MM.dd")
        dateTextView.apply { text = format.format(date).toString() }
        titleTextView.apply { text = title!!.replace("\"", "") }
        priceTextView.apply { text = price!!.replace("\"", "") }
        priceTypeTextView.apply { text = price_type!!.replace("\"", "") }
        if (is_active) {
            isActiveImageView.setImageResource(R.drawable.ic_active)
        } else {
            isActiveImageView.setImageResource(R.drawable.ic_inactive)
        }
        unitTextView.apply { text = unit!!.replace("\"", "") + amount_type!!.replace("\"", "") }
        descriptionTextView.apply { text = description!!.replace("\"", "") }

        //seller profile
        val sellerProfile = view.findViewById<ImageView>(R.id.sellerProfileDetailPage)
        val otherProfileFragment = OtherProfileFragment()
        sellerProfile.setOnClickListener {
            val bundle = bundleOf(
                "username" to username!!.replace("\"", ""),
                "email" to otherUserEmail!!.replace("\"", ""),
                "phone_number" to otherUserPhoneNr
            )
            otherProfileFragment.arguments = bundle
            Log.d("onProfileClick", "Clicked" + username)
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, otherProfileFragment)?.addToBackStack(null)
                ?.commit()
        }

        //order
        orderImageView = view.findViewById(R.id.buyProductButtonDetailPage)
        val addOrderFragment = AddOrderFragment()
        val bundle = bundleOf(
            "username" to username!!.replace("\"", ""),
            "creation_time" to creation_time,
            "title" to title!!.replace("\"", ""),
            "price" to price!!.replace("\"", ""),
            "price_type" to price_type!!.replace("\"", ""),
            "unit" to unit!!.replace("\"", "")
        )
        addOrderFragment.arguments = bundle
        orderImageView.setOnClickListener {
            if (is_active) {
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.mainFragment, addOrderFragment)?.addToBackStack(null)?.commit()
            } else {
                Toast.makeText(
                    context,
                    "This product is unavailable at the moment!",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}