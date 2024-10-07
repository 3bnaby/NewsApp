package com.example.newsapp.data.repositories.news_repo

import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSource
import com.example.newsapp.data.utilis.InternetConnectionChecker
import com.example.newsapp.domain.mappers.ArticleMapper
import com.example.newsapp.domain.mappers.SourceMapper
import com.example.newsapp.domain.model.ApiResult
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.domain.repositories.NewsRepo
import javax.inject.Inject

class NewsRepoImpl @Inject constructor(
    private var localDataSource: NewsLocalDataSource,
    private var remoteDataSource: NewsRemoteDataSource,
    private var sourceMapper: SourceMapper,
    private var articleMapper: ArticleMapper
) : NewsRepo {


    override suspend fun getSources(categoryId: String): ApiResult<List<Source>> {
        return if (InternetConnectionChecker.isOnline()) {
            when (val result = remoteDataSource.getSources(categoryId)) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> {
                    localDataSource.saveSources(result.data.sources!!)
                    ApiResult.Success(sourceMapper.mapSourcesDMToSources(result.data.sources))
                }
            }

        } else {
            when (val result = localDataSource.getSources(categoryId)) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> ApiResult.Success(sourceMapper.mapSourcesDMToSources(result.data))
            }
        }
    }

    override suspend fun getArticles(sourceId: String): ApiResult<List<Article>> {
        return if (InternetConnectionChecker.isOnline()) {
            when (val result = remoteDataSource.getArticles(sourceId)) {
                is ApiResult.Error -> return result
                is ApiResult.Success -> {
                    ApiResult.Success(articleMapper.mapArticlesDMToArticles(result.data.articles!!))
                }
            }

        } else{
            when(val result = localDataSource.getArticles("")){
                is ApiResult.Error -> return result
                is ApiResult.Success -> ApiResult.Success(articleMapper.mapArticlesDMToArticles(result.data.articles!!))
            }

        }

        }
}