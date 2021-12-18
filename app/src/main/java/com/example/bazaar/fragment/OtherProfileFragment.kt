package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.bazaar.R

private const val ARG_USERNAME = "username"
private const val ARG_EMAIL = "email"
private const val ARG_PHONE_NUMBER = "phone_number"

class OtherProfileFragment : BaseFragment() {
    private val TAG = this.javaClass.simpleName
    private var username: String? = null
    private var email: String? = null
    private var phone_number: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            username = it.getString(ARG_USERNAME)
            email = it.getString(ARG_EMAIL)
            phone_number = it.getInt(ARG_PHONE_NUMBER)
            Log.d(TAG, it.toString())
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_other_profile, container, false)
        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarOtherProfile)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, username.toString())
        val name = view.findViewById<TextView>(R.id.otherUserName)
        val nameTV = view.findViewById<TextView>(R.id.setUsernameTextOther)
        val emailTV = view.findViewById<TextView>(R.id.setEmailTextOther)
        val phone_numberTV = view.findViewById<TextView>(R.id.setPhoneNrTextOther)
        name.apply { text = username }
        nameTV.apply { text = username }
        emailTV.apply { text = email }
        phone_numberTV.apply { text = phone_number.toString() }
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.visibility = View.VISIBLE
        title.apply { text = context.getString(R.string.profile) }
        profile.visibility = View.VISIBLE
    }

    override fun setOnElementsClickListeners(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.setOnClickListener {
            Log.d(TAG, "Clicked back")
            activity?.supportFragmentManager?.popBackStack()
        }
    }
}