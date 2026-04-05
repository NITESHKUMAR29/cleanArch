package com.example.myapplication.data.repository

import android.util.Log

import com.example.myapplication.data.mappers.toDomainArticle
import com.example.myapplication.data.remote.NewsApiService
import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(val apiService: NewsApiService) : NewsRepository {
    override fun getTopHeadlines(category: String): Flow<Resource<List<Article>>> {
        return flow {
            emit(Resource.Loading)

            try {
                val response = apiService.getTopHeadlines(category=category)

                Log.d("NewsRepositoryImpl", "Response: $response")


                if (response.isSuccessful && response.body() != null) {
                    val newsResponseDto = response.body()!!
                    val articles = newsResponseDto.articles.map { it.toDomainArticle() }
                    emit(Resource.Success(articles))
                } else {
                        emit(Resource.Error("Failed to fetch news"))
                }
            } catch (e: Exception) {
                emit(Resource.Error(e.message ?: "An error occurred"))
            }
        }.flowOn(Dispatchers.IO)

    }

    override fun searchNews(query: String): Flow<Resource<List<Article>>> {
        TODO("Not yet implemented")
    }
}