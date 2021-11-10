package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.bazaar.R

class LogInFragment : Fragment() {
    val TAG = Class::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_log_in, container, false)
        val buttonSignUp = view.findViewById<Button>(R.id.buttonSignUp)
        buttonSignUp.setOnClickListener {
            Log.d(TAG, "button signed up clicked!")
            Navigation.findNavController(view)
                .navigate(R.id.action_logInFragment_to_registerFragment)
        }
        val textViewClickHere = view.findViewById<TextView>(R.id.textViewClickHere)
        textViewClickHere.setOnClickListener {
            Log.d(TAG, "Text Click Me clicked!")
            Navigation.findNavController(view)
                .navigate(R.id.action_logInFragment_to_forgetPasswordFragment)
        }
        return view
    }
}