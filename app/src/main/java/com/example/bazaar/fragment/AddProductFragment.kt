package com.example.bazaar.fragment

import android.os.Bundle
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
import com.example.bazaar.viewmodel.AddProductViewModel
import com.example.bazaar.viewmodel.AddProductViewModelFactory
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*

class AddProductFragment : Fragment() {
    val TAG = Class::class.java.simpleName
    private lateinit var addProductViewModel: AddProductViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = AddProductViewModelFactory(this.requireContext(), MarketRepository())
        addProductViewModel = ViewModelProvider(this, factory).get(AddProductViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_product, container, false)
        val format = SimpleDateFormat("yyyy.MM.dd")
        val dateTV = view.findViewById<TextView>(R.id.dateAddP)
        dateTV.append(" " + format.format(Date()))
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toggleButton = view.findViewById<SwitchCompat>(R.id.sliderAddP)
        val stateImage = view.findViewById<ImageView>(R.id.stateImageView)
        val buttonLaunch = view.findViewById<Button>(R.id.buttonLaunchAddP)

        val title = view.findViewById<EditText>(R.id.titleAddP)
        val price = view.findViewById<EditText>(R.id.priceAddP)
        val unit = view.findViewById<EditText>(R.id.amountAddP)
        val description = view.findViewById<EditText>(R.id.descriptionAddP)
        val rating = view.findViewById<EditText>(R.id.ratingAddP)

        toggleButton.setOnCheckedChangeListener { _, b ->
            if (!b) {
                stateImage.setImageResource(R.drawable.ic_inactive)
            } else {
                stateImage.setImageResource(R.drawable.ic_active)
            }
        }
        buttonLaunch.setOnClickListener {
            addProductViewModel.newProduct.value.let {
                if (it != null) {
                    it.title = title.text.toString()
                    it.price_per_unit = price.text.toString()
                    it.price_type = "RON"
                    it.unit = unit.text.toString()
                    it.amount_type = "KG"
                    it.description = description.text.toString()
                    it.rating = rating.text.toString().toDouble()
                    it.is_active = toggleButton.isChecked
                }
            }
            lifecycleScope.launch {
                addProductViewModel.addProduct()
            }
        }
    }
}