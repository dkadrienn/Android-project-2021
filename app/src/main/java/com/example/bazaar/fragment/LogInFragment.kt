package com.example.bazaar.fragment

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.activity.MainActivity
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.utils.Constants
import com.example.bazaar.viewmodel.LogInViewModel
import com.example.bazaar.viewmodel.LogInViewModelFactory
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.lang.Exception


class LogInFragment : Fragment() {
    val TAG = Class::class.java.simpleName
    private lateinit var logInViewModel: LogInViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = LogInViewModelFactory(this.requireContext(), MarketRepository())
        logInViewModel = ViewModelProvider(this, factory).get(LogInViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_log_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageLogo = requireActivity().findViewById<ImageView>(R.id.imageViewMiniLogo)
        imageLogo.visibility = View.VISIBLE

        val buttonSignUp = view.findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            Log.d(TAG, "button signed up clicked!")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.logFragment, RegisterFragment())?.addToBackStack(null)
                ?.commit()
        }
        val textViewClickHere = view.findViewById<TextView>(R.id.textViewClickHere)
        textViewClickHere.setOnClickListener {
            Log.d(TAG, "Text Click Me clicked!")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.logFragment, ForgetPasswordFragment())?.addToBackStack(null)
                ?.commit()
        }

        val buttonLogIn = view.findViewById<Button>(R.id.buttonLogIn)
        val usernameLogIn = view.findViewById<EditText>(R.id.usernameLogIn)
        val passwordLogIn = view.findViewById<EditText>(R.id.passwordLogIn)
        val sharedPreferences: SharedPreferences = this.requireActivity().getSharedPreferences(
            Constants.SHARED_PREF_FILE,
            MODE_PRIVATE
        )
        buttonLogIn.setOnClickListener {
            logInViewModel.login.value.let {
                if (it != null) {
                    it.username = usernameLogIn.text.toString()
                    it.password = passwordLogIn.text.toString()
                }
            }
                lifecycleScope.launch {
                    logInViewModel.login()
                }
            logInViewModel.token.observe(viewLifecycleOwner) {
                Log.d(TAG, "Navigate to the main activity and token is saved")
                val edit = sharedPreferences.edit()
                edit.putString(Constants.sharedPrefKeyUsername, usernameLogIn.text.toString())
                edit.apply()
                edit.commit()
                val intent = Intent(activity, MainActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }
}