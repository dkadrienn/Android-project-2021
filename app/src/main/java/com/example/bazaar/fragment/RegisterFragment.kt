package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.bazaar.R

class RegisterFragment : Fragment() {
    val TAG = Class::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_register, container, false)
        val textViewLogInRegister = view.findViewById<TextView>(R.id.textViewLogInRegister)
        textViewLogInRegister.setOnClickListener {
            Log.d(TAG, "Log in text clicked!")
            Navigation.findNavController(view)
                .navigate(R.id.action_registerFragment_to_logInFragment)
        }
        return view
    }
}