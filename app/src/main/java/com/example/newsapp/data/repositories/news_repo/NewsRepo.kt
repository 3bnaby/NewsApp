package com.example.newsapp.data.repositories.news_repo

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source

interface NewsRepo {
    suspend fun getSources(categoryId: String): List<Source>
    suspend fun getArticles(sourceId: String): ArticlesResponse
}