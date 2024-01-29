package com.example.newsapi.data.reporistory

import android.app.Application
import androidx.lifecycle.liveData
import com.example.newsapi.R
import com.example.newsapi.data.network.ApiService
import com.example.newsapi.utils.ApiError
import com.example.newsapi.utils.Result
import retrofit2.HttpException
import java.io.IOException

class NewsRepository private constructor(
    private val apiService: ApiService,
    private val application: Application
) {

    fun getHeadlines() = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getHeadlines()
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(ApiError.handleHttpException(e))
        } catch (exception: IOException) {
            emit(Result.Error(application.resources.getString(R.string.network_error_message)))
        } catch (exception: Exception) {
            emit(Result.Error(exception.message ?: application.resources.getString(R.string.unknown_error)))
        }
    }

    fun getEverything() = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getEverything()
            emit(Result.Success(response))
        }catch (e: HttpException) {
            emit(ApiError.handleHttpException(e))
        } catch (exception: IOException) {
            emit(Result.Error(application.resources.getString(R.string.network_error_message)))
        } catch (exception: Exception) {
            emit(Result.Error(exception.message ?: application.resources.getString(R.string.unknown_error)))
        }
    }

    companion object {
        @Volatile
        private var instance: NewsRepository? = null
        fun getInstance(
            apiService: ApiService,
            application: Application
        ): NewsRepository =
            instance ?: synchronized(this) {
                instance ?: NewsRepository(apiService, application)
            }.also { instance = it }
    }
}