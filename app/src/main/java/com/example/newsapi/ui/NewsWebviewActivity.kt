package com.example.newsapi.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebViewClient
import com.example.newsapi.R
import com.example.newsapi.databinding.ActivityNewsWebviewBinding

class NewsWebviewActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewsWebviewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsWebviewBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val url = intent.getStringExtra(EXTRA_URL)
        if (url != null) {
            setupWebView(url)
        }
    }

    private fun setupWebView(url: String) {
        binding.webView.settings.javaScriptEnabled = true
        binding.webView.webViewClient = WebViewClient()
        binding.webView.loadUrl(url)
    }

    companion object {
        const val EXTRA_URL = "extra_url"
    }

}