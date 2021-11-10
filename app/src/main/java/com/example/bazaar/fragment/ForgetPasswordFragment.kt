package com.example.bazaar.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.bazaar.R
import com.example.bazaar.model.PasswordReset
import com.example.bazaar.repository.MarketRepository
import com.example.bazaar.viewmodel.PasswordResetViewModel
import com.example.bazaar.viewmodel.PasswordResetViewModelFactory
import kotlinx.coroutines.launch

class ForgetPasswordFragment : Fragment() {
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
        val editTextEmailLogIn = view.findViewById<EditText>(R.id.editTextEmailLogIn)
//        val editTextEmailLogIn = view.findViewById<EditText>(R.id.editTextEmailLogIn)
        buttonReset.setOnClickListener {
            passwordResetViewModel.passwordReset.value.let {
//                if (it != null) {
//                    it.username = editText1.text.toString()
//                }
                if (it != null) {
                    it.email = editTextEmailLogIn.text.toString()
                }
            }
            lifecycleScope.launch {
                passwordResetViewModel.passwordReset()

            }
//            loginViewModel.token.observe(viewLifecycleOwner){
//                findNavController().navigate(R.id.action_loginFragment_to_listFragment)
//            }
        }
    }
}