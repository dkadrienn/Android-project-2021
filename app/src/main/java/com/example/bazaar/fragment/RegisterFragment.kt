package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.RegisterViewModel
import com.example.bazaar.viewmodel.RegisterViewModelFactory
import kotlinx.coroutines.launch
import java.lang.Exception

class RegisterFragment : Fragment() {
    val TAG = Class::class.java.simpleName
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = RegisterViewModelFactory(this.requireContext(), MarketRepository())
        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val imageLogo = requireActivity().findViewById<ImageView>(R.id.imageViewMiniLogo)
        imageLogo.visibility = View.VISIBLE

        val textViewLogInRegister = view.findViewById<TextView>(R.id.textViewLogInRegister)
        textViewLogInRegister.setOnClickListener {
            Log.d(TAG, "Log in text clicked!")
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.logFragment, LogInFragment())?.addToBackStack(null)
                ?.commit()
        }

        val buttonRegister = view.findViewById<Button>(R.id.buttonRegister)
        val usernameRegister = view.findViewById<EditText>(R.id.usernameRegister)
        val emailRegister = view.findViewById<EditText>(R.id.emailRegister)
        val passwordRegister = view.findViewById<EditText>(R.id.passwordRegister)
        val phoneNrRegister = view.findViewById<EditText>(R.id.phoneNrRegister)
        buttonRegister.setOnClickListener {
            registerViewModel.register.value.let {
                if (it != null) {
                    it.username = usernameRegister.text.toString()
                    it.email = emailRegister.text.toString()
                    it.password = passwordRegister.text.toString()
                    it.phone_number = phoneNrRegister.text.toString().toInt()
                }
            }
            lifecycleScope.launch {
                registerViewModel.register()

            }
            activity?.supportFragmentManager?.beginTransaction()
                ?.replace(R.id.logFragment, LogInFragment())?.addToBackStack(null)?.commit()
        }
    }
}