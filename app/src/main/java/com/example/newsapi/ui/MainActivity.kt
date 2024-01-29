package com.example.newsapi.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.example.newsapi.R
import com.example.newsapi.utils.Result
import com.example.newsapi.databinding.ActivityMainBinding
import com.example.newsapi.ui.viewmodel.NewsViewModel
import com.example.newsapi.ui.viewmodel.NewsViewModelFactory
import com.example.newsapi.utils.SnackbarUtils
import com.example.newsapi.utils.convertDateFormat

class MainActivity : AppCompatActivity() {
    private val viewModel: NewsViewModel by viewModels{
        NewsViewModelFactory.getInstance(application)
    }

    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.getHeadlines().observe(this) { result ->
            when (result) {
                is Result.Loading ->  return@observe
                is Result.Success -> {
                    val data = result.data.articles?.get(0)
                    if (data != null) {
                        binding.tvTitle.text = data.title
                        Glide.with(this)
                            .load(data.urlToImage)
                            .placeholder(R.drawable.ic_launcher_foreground)
                            .error(R.drawable.ic_launcher_foreground)
                            .fallback(R.drawable.ic_launcher_foreground)
                            .into(binding.ivGambar)
                        binding.tvDate.text = data.publishedAt?.let { convertDateFormat(it) }
                    }


                }
                is Result.Error -> {
                    showSnackBar(result.error)
                }
            }
        }
    }

    private fun showSnackBar(messageResId: Any) {
        SnackbarUtils.showWithDismissAction(binding.root, messageResId.toString())
    }
}