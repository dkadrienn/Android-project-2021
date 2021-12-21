package com.example.bazaar.fragment

import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.SwitchCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.UpdateProductViewModel
import com.example.bazaar.viewmodel.UpdateProductViewModelFactory
import kotlinx.android.synthetic.main.fragment_add_product.*
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat

private const val ARG_USERNAME = "username"
private const val ARG_CREATION_TIME = "creation_time"
private const val ARG_TITLE = "title"
private const val ARG_PRICE = "price"
private const val ARG_PRICE_TYPE = "price_type"
private const val ARG_IS_ACTIVE = "is_active"
private const val ARG_UNIT = "unit"
private const val ARG_AMOUNT_TYPE = "amount_type"
private const val ARG_DESCRIPTION = "description"
private const val ARG_RATING = "rating"
private const val ARG_PRODUCT_ID = "product_id"

class EditProductFragment : Fragment() {
    private val TAG = this.javaClass.simpleName
    private lateinit var editProductViewModel: UpdateProductViewModel

    private var username: String? = null
    private var creation_time: Long? = null
    private var title: String? = null
    private var price: String? = null
    private var price_type: String? = null
    private var is_active: Boolean = false
    private var unit: String? = null
    private var amount_type: String? = null
    private var description: String? = null
    private var rating: Double? = null
    private var product_id: String? = null

    lateinit var titleEditText : EditText
    lateinit var priceEditText: EditText
    lateinit var priceTypeEditText : EditText
    lateinit var unitEditText : EditText
    lateinit var unitTypeEditText : EditText
    lateinit var descriptionEditText : EditText
    lateinit var ratingEditText : EditText
    lateinit var dateTextView : TextView
    lateinit var toggleButton: SwitchCompat
    lateinit var stateImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            creation_time = it.getLong(ARG_CREATION_TIME)
            title = it.getString(ARG_TITLE)
            price = it.getString(ARG_PRICE)
            price_type = it.getString(ARG_PRICE_TYPE)
            is_active = it.getBoolean(ARG_IS_ACTIVE)
            unit = it.getString(ARG_UNIT)
            amount_type = it.getString(ARG_AMOUNT_TYPE)
            description = it.getString(ARG_DESCRIPTION)
            rating = it.getDouble(ARG_RATING)
            product_id = it.getString(ARG_PRODUCT_ID)
        }

        val factory = UpdateProductViewModelFactory(this.requireContext(), MarketRepository())
        editProductViewModel =
            ViewModelProvider(this, factory).get(UpdateProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)

        toggleButton = view.findViewById<SwitchCompat>(R.id.sliderAddP)
        stateImage = view.findViewById<ImageView>(R.id.stateImageView)

        titleEditText = view.findViewById<EditText>(R.id.titleAddP)
        priceEditText = view.findViewById<EditText>(R.id.priceAddP)
        priceTypeEditText = view.findViewById<EditText>(R.id.priceTypeAddP)
        unitEditText = view.findViewById<EditText>(R.id.amountAddP)
        unitTypeEditText = view.findViewById<EditText>(R.id.amountTypeAddP)
        descriptionEditText = view.findViewById<EditText>(R.id.descriptionAddP)
        ratingEditText = view.findViewById<EditText>(R.id.ratingAddP)
        dateTextView = view.findViewById<TextView>(R.id.dateAddP)

        if (is_active) {
            toggleButton.toggle()
            stateImage.setImageResource(R.drawable.ic_active)
        }

        toggleButton.setOnCheckedChangeListener { _, b ->
            if (!b) {
                stateImage.setImageResource(R.drawable.ic_inactive)
            } else {
                stateImage.setImageResource(R.drawable.ic_active)
            }
        }

        titleEditText.text = title!!.toEditable()
        priceEditText.text = price!!.toEditable()
        priceTypeEditText.text = price_type!!.toEditable()
        unitEditText.text = unit!!.toEditable()
        unitTypeEditText.text = amount_type!!.toEditable()
        descriptionEditText.text = description!!.toEditable()
        ratingEditText.text = rating.toString().toEditable()
        dateTextView.append(" " + SimpleDateFormat("yyyy.MM.dd").format(creation_time))

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonLaunch = view.findViewById<Button>(R.id.buttonLaunchAddP)

        buttonLaunch.setOnClickListener {
            Log.d(TAG, editProductViewModel.updated.value.toString())
            editProductViewModel.updated.value.let {
                if (it != null) {
                    it.product_id = product_id!!.replace("\"","")
                    it.price_per_unit = priceEditText.text.toString().toDouble()
                    it.is_active = toggleButton.isChecked
                    it.title = titleEditText.text.toString()
                    it.rating = ratingEditText.text.toString()
                    it.amount_type = unitTypeEditText.text.toString()
                    it.price_type = priceTypeEditText.text.toString()
                }
            }
            lifecycleScope.launch {
                editProductViewModel.updateProduct()
            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, MyMarketFragment())?.addToBackStack(null)
                ?.commit()
        }
    }

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}