package com.example.myapplication.data.mappers

import com.example.myapplication.data.remote.dto.ArticleDto
import com.example.myapplication.domain.model.Article


fun ArticleDto.toDomainArticle(): Article {
    return Article(
        author = author ?: "Unknown Author",
        title = title ?: "No Title",
        description = description ?: "No Description available",
        url = url ?: "",
        urlToImage = urlToImage ?: "https://your-placeholder-image-url.com",
        publishedAt = publishedAt ?: "",
        sourceName = source?.name ?: "Unknown Source"
    )
}