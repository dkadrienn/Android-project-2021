package com.example.bazaar.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.example.bazaar.R
import com.example.bazaar.model.PasswordReset
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.LogInViewModel
import com.example.bazaar.viewmodel.LogInViewModelFactory
import com.example.bazaar.viewmodel.PasswordResetViewModel
import com.example.bazaar.viewmodel.PasswordResetViewModelFactory
import kotlinx.coroutines.launch

class ForgetPasswordFragment : Fragment() {
    val TAG = Class::class.java.simpleName
    private lateinit var passwordResetViewModel: PasswordResetViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val factory = PasswordResetViewModelFactory(this.requireContext(), MarketRepository())
        passwordResetViewModel = ViewModelProvider(this, factory).get(PasswordResetViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_forget_password, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonReset = view.findViewById<Button>(R.id.buttonForgotPwd)
        val editTextUsernameForgetPwd = view.findViewById<EditText>(R.id.usernameForgetPwd)
        val editTextEmailForgetPwd = view.findViewById<EditText>(R.id.emailForgetPwd)
        buttonReset.setOnClickListener {
            Log.d(TAG, "Reset button clicked!")

            passwordResetViewModel.passwordReset.value.let {
                if (it != null) {
                    it.username = editTextUsernameForgetPwd.text.toString()
                }
                if (it != null) {
                    it.email = editTextEmailForgetPwd.text.toString()
                }
            }
            lifecycleScope.launch {
                passwordResetViewModel.passwordReset()

            }
            Navigation.findNavController(view)
                .navigate(R.id.action_forgetPasswordFragment_to_logInFragment)
        }
    }
}