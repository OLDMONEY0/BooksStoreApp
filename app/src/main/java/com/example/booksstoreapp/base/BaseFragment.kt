package com.example.booksstoreapp.base

import androidx.fragment.app.Fragment
import com.example.booksstoreapp.MainActivity

abstract class BaseFragment : Fragment() {
    
    protected fun replaceFragment(fragment: Fragment, addToBackStack: Boolean = true) {
        (activity as? MainActivity)?.replaceFragment(fragment, addToBackStack)
    }
} 