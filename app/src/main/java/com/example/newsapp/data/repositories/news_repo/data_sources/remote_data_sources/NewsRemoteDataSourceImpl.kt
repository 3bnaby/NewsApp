package com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources

import com.example.newsapp.data.api.ApiManager
import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse

class NewsRemoteDataSourceImpl(var webServices: WebServices) : NewsRemoteDataSource {

    override suspend fun getSources(categoryId :String) : SourcesResponse{
        return webServices.getSources(category = categoryId)

    }

    override suspend fun getArticles(sourceId : String) : ArticlesResponse{
        return webServices.getArticles(tabId = sourceId)

    }
}