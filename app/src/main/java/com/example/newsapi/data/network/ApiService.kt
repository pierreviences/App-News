package com.example.newsapi.data.network

import com.example.newsapi.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("top-headlines")
    suspend fun getHeadlines(
        @Query("country") country: String,
        @Query("apiKey") apiKey: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 1,
    ): NewsResponse

    @GET("everything")
    suspend fun getEverything(
        @Query("q") q: String,
        @Query("apiKey") latitude: String,
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 1,
    ): NewsResponse
}