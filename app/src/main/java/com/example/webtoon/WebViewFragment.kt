package com.example.webtoon

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import com.example.webtoon.databinding.FragmentWebviewBinding

class WebViewFragment(private val position: Int, private val webviewUrl: String):Fragment() {

    private lateinit var binding: FragmentWebviewBinding
    var listener: OnTabLayoutNameChanged? = null

    // static
    companion object {
        const val SHARED_PREFERENCE = "WEB_HISTORY"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentWebviewBinding.inflate(inflater)
        return binding.root
    }

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.saveButton.setOnClickListener {
            val sharedPreference = activity?.getSharedPreferences("WEB_HISTORY", Context.MODE_PRIVATE)
            val url = sharedPreference?.getString("tab$position", "")

            if(!url.isNullOrEmpty()) {
              binding.webView.loadUrl(url)
            } else {
                Toast.makeText(context, "마지막 저장 시점이 없습니다.",Toast.LENGTH_SHORT).show()
            }
            }


        binding.changeTabNameButton.setOnClickListener {

            val dialog = AlertDialog.Builder(context)
            val editText = EditText(context)

            dialog.setView(editText)
            dialog.setPositiveButton("저장") { _, _ ->
                activity
                    ?.getSharedPreferences(SHARED_PREFERENCE,Context.MODE_PRIVATE)
                    ?.edit {
                        putString("tab${position}_name", editText.text.toString())
                        listener?.nameChanged(position, editText.text.toString())
                    }
            }
            dialog.setNegativeButton("취소") { dialogInterface, _ ->
                dialogInterface.cancel()
            }
            dialog.show()
        }



        binding.webView.webViewClient = WebtoonWebviewClient(
            binding.progressCircular
        ) { url ->
            activity
                ?.getSharedPreferences(SHARED_PREFERENCE, Context.MODE_PRIVATE)
                ?.edit {
                putString("tab$position", url)

            }
        }
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.loadUrl(webviewUrl)

        super.onViewCreated(view, savedInstanceState)
    }

    fun canGoBack():Boolean {
        return binding.webView.canGoBack()
    }
    fun goBack() {
        binding.webView.goBack()
    }
}


interface OnTabLayoutNameChanged {
    fun nameChanged(position: Int, name: String)
}