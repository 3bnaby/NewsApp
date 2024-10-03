package com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse

interface NewsRemoteDataSource {
    suspend fun getSources(categoryId :String) : SourcesResponse
    suspend fun getArticles(sourceId : String) : ArticlesResponse
}