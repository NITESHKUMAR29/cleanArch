package com.example.myapplication.domain.useCase

import com.example.myapplication.core.common.Resource
import com.example.myapplication.domain.model.Article
import com.example.myapplication.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTopHeadlinesUseCase @Inject constructor(private val newsRepository: NewsRepository) {
     operator fun invoke(category: String): Flow<Resource<List<Article>>> =
        newsRepository.getTopHeadlines(category)
}