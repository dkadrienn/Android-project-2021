package com.example.bazaar.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import com.example.bazaar.viewmodel.PasswordResetTokenViewModel
import com.example.bazaar.viewmodel.PasswordResetTokenViewModelFactory
import com.example.bazaar.viewmodel.UpdateUserDataViewModel
import com.example.bazaar.viewmodel.UpdateUserDataViewModelFactory
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch

class ProfileFragment : BaseFragment() {
    private val TAG = this.javaClass.simpleName
    private var myName: String? = null
    private var myEmail: String? = null
    private var myPhoneNr: String? = null

    private lateinit var viewModel: UpdateUserDataViewModel
    private lateinit var pwdViewModel: PasswordResetTokenViewModel

    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val factory = UpdateUserDataViewModelFactory(this.requireContext(), MarketRepository())
        viewModel = ViewModelProvider(this, factory).get(UpdateUserDataViewModel::class.java)

        val factoryPwd =
            PasswordResetTokenViewModelFactory(this.requireContext(), MarketRepository())
        pwdViewModel =
            ViewModelProvider(this, factoryPwd).get(PasswordResetTokenViewModel::class.java)

        sharedPreferences = this.requireActivity().getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            Context.MODE_PRIVATE
        )

        myName = sharedPreferences.getString(Constants.sharedPrefKeyUsername, "default")
        myEmail = sharedPreferences.getString(Constants.sharedPrefKeyEmail, "default")
        myPhoneNr = sharedPreferences.getString(Constants.sharedPrefKeyPhoneNr, "default")
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        val topBar = view.findViewById<ConstraintLayout>(R.id.topBarProfile)
        setTopBarElements(topBar)
        setOnElementsClickListeners(topBar)

        val emailEditText: TextInputEditText = view.findViewById(R.id.changeEmailProfile)
        val usernameEditText: TextInputEditText = view.findViewById(R.id.changeUserNameProfile)
        val phoneNrEditText: TextInputEditText = view.findViewById(R.id.changePhoneNrProfile)
        val pwdEditText: TextInputEditText = view.findViewById(R.id.changePasswordProfile)

        emailEditText.text = myEmail!!.toEditable()
        usernameEditText.text = myName!!.toEditable()
        phoneNrEditText.text = myPhoneNr!!.toEditable()

        val publishButton: Button = view.findViewById(R.id.publishButtonProfile)
        publishButton.setOnClickListener {
            if (!pwdEditText.text.isNullOrEmpty()) {
                pwdViewModel.newPwd.value.let {
                    if (it != null) it.new_password = pwdEditText.text.toString().replace("\"", "")
                }
                lifecycleScope.launch {
                    pwdViewModel.resetPasswordWithToken()
                }
            }

            viewModel.updated.value.let {
                if (it != null) {
                    if (usernameEditText.text.toString().replace("\"", "") != myName)
                        it.username = usernameEditText.text.toString().replace("\"", "")
//                    if (emailEditText.text.toString().replace("\"", "").contains(myEmail!!))
//                        it.email = emailEditText.text.toString().replace("\"", "")
                    it.phone_number = phoneNrEditText.text.toString().replace("\"", "").toInt()
                }
            }
            lifecycleScope.launch {
                viewModel.updateUserData()
            }

            val edit = sharedPreferences.edit()
            edit.putString(Constants.sharedPrefKeyUsername, usernameEditText.text.toString())
            edit.putString(Constants.sharedPrefKeyEmail, emailEditText.text.toString())
            edit.putString(Constants.sharedPrefKeyPhoneNr, phoneNrEditText.text.toString())
            edit.apply()
            edit.commit()

            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.mainFragment, TimelineFragment())?.addToBackStack(null)?.commit()
        }
        return view
    }

    override fun setTopBarElements(view: View) {
        val backArrow = view.findViewById<ImageView>(R.id.top_bar_left_icon)
        val title = view.findViewById<TextView>(R.id.top_bar_title)
        val profile = view.findViewById<ImageView>(R.id.top_bar_profile)
        backArrow.visibility = View.VISIBLE
        title.apply { text = context.getString(R.string.settings) }
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

    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
}