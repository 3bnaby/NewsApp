package com.example.newsapp.domain.repositories

import com.example.newsapp.data.api.model.ArticleDM
import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourceDM
import com.example.newsapp.data.api.model.SourcesResponse
import com.example.newsapp.domain.model.ApiResult
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source

interface NewsRepo {
    suspend fun getSources(categoryId: String): ApiResult<List<Source>>
    suspend fun getArticles(sourceId: String): ApiResult< List<Article>>
}