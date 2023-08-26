package com.example.webtoon

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.webtoon.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button1.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, WebViewFragment())
                commit()
            }
        }

        binding.button2.setOnClickListener {
            supportFragmentManager.beginTransaction().apply {
                replace(R.id.fragmentContainer, BFragment())
                commit()
            }
        }
    }

    override fun onBackPressed() {

        val currentFragment = supportFragmentManager.fragments[0]


//     basic behavior:   super.onBackPressed()
    }
}