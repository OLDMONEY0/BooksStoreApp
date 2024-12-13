package com.example.booksstoreapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.booksstoreapp.databinding.ActivityMainBinding
import com.example.booksstoreapp.adapter.TabPageAdapter
import com.example.booksstoreapp.util.UserData
import com.google.android.material.tabs.TabLayout

class   MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setup()
        when{
            UserData(this).isAuthorized() -> {
                setupTabBar()
            }
            !UserData(this).isAuthorized() -> {
                navigateToLogin()
            }
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setup() {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupTabBar() {
        val tabAdapter = TabPageAdapter(this, binding.tabBar.tabCount)
        binding.viewPager.adapter = tabAdapter

        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                binding.tabBar.selectTab(binding.tabBar.getTabAt(position))
            }
        })

        binding.tabBar.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.viewPager.currentItem = tab.position
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }
        })
    }
}