package com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources

import com.example.newsapp.data.api.ApiManager
import com.example.newsapp.data.api.WebServices
import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse
import com.example.newsapp.domain.model.ApiResult
import javax.inject.Inject

class NewsRemoteDataSourceImpl@Inject constructor(var webServices: WebServices) : NewsRemoteDataSource {

    override suspend fun getSources(categoryId :String) : ApiResult<SourcesResponse> {
        return try {
            ApiResult.Success(data = webServices.getSources(category = categoryId))
        } catch (exception: Throwable) {
            ApiResult.Error(exception.localizedMessage ?: "")
        }
    }


    override suspend fun getArticles(sourceId : String) : ApiResult<ArticlesResponse>{
        return try {
            ApiResult.Success(webServices.getArticles(tabId = sourceId))
        }catch (exception :Throwable){
            ApiResult.Error(exception.localizedMessage ?: "")
        }
    }

    }
