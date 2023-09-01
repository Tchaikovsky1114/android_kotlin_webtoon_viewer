package com.example.webtoon

import android.graphics.Bitmap
import android.view.View
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible

class WebtoonWebviewClient(
    private val progressCircular:ProgressBar,
    private val saveData: (String) -> Unit
):WebViewClient() {


    override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

        if (request != null && request.url.toString().contains("webtoon/detail")) {
            saveData(request.url.toString())
        }
        // return true = 로드 멈춤
        // return false = 계속 url 로드 시도
        return if(!(request != null && request.url.toString().contains("comic.naver.com"))) {

            if (view != null) {
                Toast.makeText(
                    view.context,"웹툰 바깥으로 나가지마세요!!!", Toast.LENGTH_LONG).show()
            }
            true
        } else {
            false
        }

    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)

        progressCircular.isVisible = false
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        progressCircular.isVisible = true
    }

    override fun onReceivedError(
        view: WebView?,
        request: WebResourceRequest?,
        error: WebResourceError?
    ) {
        super.onReceivedError(view, request, error)
        // do render errorpage
    }
}