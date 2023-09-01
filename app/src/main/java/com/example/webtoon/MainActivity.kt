package com.example.webtoon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.webtoon.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity(), OnTabLayoutNameChanged {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val sharedPreference = getSharedPreferences(WebViewFragment.SHARED_PREFERENCE, MODE_PRIVATE)
        val tab0 = sharedPreference.getString("tab0_name", "밸로그")
        val tab1 = sharedPreference.getString("tab1_name", "구글")
        val tab2 = sharedPreference.getString("tab2_name", "네이버웹툰")
        binding.viewPager.adapter = ViewPagerAdapter(this)

        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            run {
//                val textView = TextView(this@MainActivity)
//                textView.text = when(position) {
//                    0 -> tab0
//                    1 -> tab1
//                    else -> tab2
//                }
//                textView.textAlignment = View.TEXT_ALIGNMENT_CENTER
                    tab.text = when(position) {
                        0 -> tab0
                        1 -> tab1
                        else -> tab2
                    }
            }
        }.attach()
    }

    override fun nameChanged(position: Int, name: String) {
        val tab = binding.tabLayout.getTabAt(position)
        tab?.text = name
    }

//    override fun onBackPressed() {
//
//        val currentFragment = supportFragmentManager.fragments[binding.viewPager.currentItem]
//        if (currentFragment is WebViewFragment) {
//            currentFragment.goBack()
//        } else {
//            super.onBackPressed()
//        }
//        return super.onBackPressed()
//    }
}