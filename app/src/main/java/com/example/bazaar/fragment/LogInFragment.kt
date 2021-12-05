package com.example.bazaar.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.activity.MainActivity
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.LogInViewModel
import com.example.bazaar.viewmodel.LogInViewModelFactory
import kotlinx.coroutines.launch


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
        val view: View = inflater.inflate(R.layout.fragment_log_in, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val buttonSignUp = view.findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            Log.d(TAG, "button signed up clicked!")
            childFragmentManager.beginTransaction().replace(R.id.logFragment, RegisterFragment())
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
        buttonLogIn.setOnClickListener {
            logInViewModel.login.value.let {
                if (it != null) {
                    it.username = usernameLogIn.text.toString()
                }
                if (it != null) {
                    it.password = passwordLogIn.text.toString()
                }
            }
            lifecycleScope.launch {
                logInViewModel.login()

            }
            logInViewModel.token.observe(viewLifecycleOwner) {
                Log.d(TAG, "Navigate to the main activity and token is saved")
                val intent = Intent(activity, MainActivity::class.java)
                activity?.startActivity(intent)
            }
        }
    }
}