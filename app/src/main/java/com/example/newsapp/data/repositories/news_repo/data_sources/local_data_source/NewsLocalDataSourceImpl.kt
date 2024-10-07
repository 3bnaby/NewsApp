package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourceDM
import com.example.newsapp.data.database.dao.MyDataBase
import com.example.newsapp.domain.model.ApiResult
import javax.inject.Inject

class NewsLocalDataSourceImpl@Inject constructor(var myDataBase: MyDataBase) : NewsLocalDataSource {

    override suspend fun getSources(categoryId: String): ApiResult<List<SourceDM>> {
        return try {
            ApiResult.Success(myDataBase.getSourcesDao().getSources(categoryId))
        } catch (exception : Throwable){
            ApiResult.Error(exception.localizedMessage ?: "")
        }

    }

    override suspend fun saveSources(sourcesResponse: List<SourceDM>) {
        myDataBase.getSourcesDao().insertSources(sourcesResponse)

    }

    override suspend fun getArticles(sourceId: String): ApiResult<ArticlesResponse> {
        return ApiResult.Success(ArticlesResponse(articles = emptyList()))


    }
}