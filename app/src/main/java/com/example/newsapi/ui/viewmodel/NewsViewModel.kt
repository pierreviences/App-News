package com.example.newsapi.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.example.newsapi.data.reporistory.NewsRepository

class NewsViewModel (private val newsRepository: NewsRepository) : ViewModel()  {
    fun getHeadlines() = newsRepository.getHeadlines()
    fun getEverything() = newsRepository.getEverything()
}