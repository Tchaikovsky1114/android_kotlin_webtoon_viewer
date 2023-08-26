package com.example.webtoon

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.webtoon.databinding.FragmentWebviewBinding

class WebViewFragment:Fragment() {

    private lateinit var binding: FragmentWebviewBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.saveButton.setOnClickListener {
        }
        binding.changeTabNameButton.setOnClickListener {
        }
        binding.webView.webViewClient = WebtoonWebviewClient(binding.progressCircular)
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl("https://comic.naver.com/webtoon")

        super.onViewCreated(view, savedInstanceState)

        // https://comic.naver.com/webtoon/detail?titleId=650305&no=399
    }

    fun canGoBack():Boolean {
        return binding.webView.canGoBack()
    }
    fun goBack() {
        binding.webView.goBack()
    }
}