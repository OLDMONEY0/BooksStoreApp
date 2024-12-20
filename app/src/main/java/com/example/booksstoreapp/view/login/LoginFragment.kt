package com.example.booksstoreapp.view.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksstoreapp.base.BaseFragment
import com.example.booksstoreapp.databinding.FragmentLoginBinding
import com.example.booksstoreapp.util.UserData
import com.example.booksstoreapp.view.home.MainFragment
import com.google.android.material.snackbar.Snackbar

class LoginFragment : BaseFragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.submitButton.setOnClickListener {
            val login = binding.loginEt.text.toString()
            val password = binding.passwordEt.text.toString()
            
            if (validateInput(login, password)) {
                UserData(requireContext()).setAuthorized()
                replaceFragment(MainFragment(), false)
            } else {
                Snackbar.make(view, "Please fill all fields", Snackbar.LENGTH_SHORT).show()
            }
        }
    }

    private fun validateInput(login: String, password: String): Boolean {
        return login.isNotEmpty() && password.isNotEmpty()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 