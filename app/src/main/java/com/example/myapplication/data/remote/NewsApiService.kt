package com.example.myapplication.data.remote

import com.example.myapplication.core.common.Constants
import com.example.myapplication.data.remote.dto.NewsResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {

    @GET("v2/top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String = "us",
        @Query("category") category: String = "",
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponseDto>

    @GET("v2/everything")
    suspend fun searchNews(
        @Query("q") query: String,
        @Query("apiKey") apiKey: String = Constants.API_KEY
    ): Response<NewsResponseDto>
}