package com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources

import com.example.newsapp.data.api.ApiManager
import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse

class NewsRemoteDataSource {

    suspend fun getSources(categoryId :String) : SourcesResponse{
        return ApiManager.webServices().getSources(category = categoryId)

    }

    suspend fun getArticles(sourceId : String) : ArticlesResponse{
        return ApiManager.webServices().getArticles(tabId = sourceId)

    }
}