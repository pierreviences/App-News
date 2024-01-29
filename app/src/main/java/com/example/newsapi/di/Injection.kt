package com.example.newsapi.di

import android.app.Application
import com.example.newsapi.data.network.ApiConfig
import com.example.newsapi.data.reporistory.NewsRepository

object Injection {
    private fun provideApiService() = ApiConfig.getApiService()
    fun provideNewsRepository(application: Application) =
        NewsRepository.getInstance(provideApiService(), application)
}