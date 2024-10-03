package com.example.newsapp.data.repositories.news_repo

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source
import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSourceImpl
import com.example.newsapp.data.utilis.InternetConnectionChecker

class NewsRepoImpl(
    private var localDataSource: NewsLocalDataSource,
    private var remoteDataSource: NewsRemoteDataSource
) : NewsRepo {


    override suspend fun getSources(categoryId: String): List<Source> {
        if (InternetConnectionChecker.isOnline()) {
            val sourceResponse = remoteDataSource.getSources(categoryId)
            localDataSource.saveSources(sourceResponse.sources!!)
            return sourceResponse.sources

        } else {
            return localDataSource.getSources(categoryId)
        }

    }

    override suspend fun getArticles(sourceId: String): ArticlesResponse {
        try {
            if (InternetConnectionChecker.isOnline()) {
                val articleResponse = remoteDataSource.getArticles(sourceId)
                return articleResponse

            } else {
                return localDataSource.getArticles(sourceId)
            }
        } catch (e: Exception) {
            throw e
        }


    }
}