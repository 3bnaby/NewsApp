package com.example.newsapp.data.repositories.news_repo

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse
import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSource
import com.example.newsapp.data.utilis.InternetConnectionChecker

class NewsRepo {
    private var localDataSource = NewsLocalDataSource()
    private var remoteDataSource = NewsRemoteDataSource()

    suspend fun getSources(categoryId: String): SourcesResponse {
        if (InternetConnectionChecker.isOnline()) {
            val sourceResponse = remoteDataSource.getSources(categoryId)
            localDataSource.saveSources(categoryId, sourceResponse)
            return sourceResponse

        } else {
            return localDataSource.getSources(categoryId)
        }

    }

    suspend fun getArticles(sourceId: String): ArticlesResponse {
        if (InternetConnectionChecker.isOnline()) {
            val articleResponse = remoteDataSource.getArticles(sourceId)
            return articleResponse

        } else {
            return localDataSource.getArticles(sourceId)
        }

    }
}