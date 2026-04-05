package com.example.myapplication.domain.repository

import com.example.myapplication.domain.model.Resource
import com.example.myapplication.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getTopHeadlines(category: String): Flow<Resource<List<Article>>>
    fun searchNews(query: String): Flow<Resource<List<Article>>>
}
