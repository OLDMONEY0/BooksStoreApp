package com.example.booksstoreapp.view.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.booksstoreapp.base.BaseFragment
import com.example.booksstoreapp.databinding.FragmentProfileBinding
import com.example.booksstoreapp.util.UserData
import com.example.booksstoreapp.view.login.LoginFragment

class ProfileFragment : BaseFragment() {
    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        binding.logoutButton.setOnClickListener {
            UserData(requireContext()).clearAuth()
            replaceFragment(LoginFragment(), false)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
} 