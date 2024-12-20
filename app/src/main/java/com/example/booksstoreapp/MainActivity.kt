package com.example.booksstoreapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.booksstoreapp.databinding.ActivityMainBinding
import com.example.booksstoreapp.view.home.MainFragment
import com.example.booksstoreapp.view.login.LoginFragment
import com.example.booksstoreapp.view.favorites.FavoritesFragment
import com.example.booksstoreapp.view.wishlist.WishlistFragment
import com.example.booksstoreapp.view.profile.ProfileFragment
import com.example.booksstoreapp.util.UserData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        if (savedInstanceState == null) {
            when {
                UserData(this).isAuthorized() -> showMainFragment()
                else -> showLoginFragment()
            }
        }

        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.navigation_books -> {
                    replaceFragment(MainFragment(), false)
                    true
                }
                R.id.navigation_favorites -> {
                    replaceFragment(FavoritesFragment(), false)
                    true
                }
                R.id.navigation_wishlist -> {
                    replaceFragment(WishlistFragment(), false)
                    true
                }
                R.id.navigation_profile -> {
                    replaceFragment(ProfileFragment(), false)
                    true
                }
                else -> false
            }
        }
    }

    private fun showLoginFragment() {
        replaceFragment(LoginFragment(), false)
        binding.bottomNavigation.visibility = View.GONE
    }

    private fun showMainFragment() {
        replaceFragment(MainFragment(), false)
        binding.bottomNavigation.visibility = View.VISIBLE
    }

    fun replaceFragment(fragment: Fragment, addToBackStack: Boolean) {
        supportFragmentManager.beginTransaction().apply {
            setCustomAnimations(
                R.anim.slide_in_right,
                R.anim.slide_out_left,
                R.anim.slide_in_left,
                R.anim.slide_out_right
            )
            replace(R.id.fragmentContainer, fragment)
            if (addToBackStack) {
                addToBackStack(null)
            }
        }.commit()
    }
}