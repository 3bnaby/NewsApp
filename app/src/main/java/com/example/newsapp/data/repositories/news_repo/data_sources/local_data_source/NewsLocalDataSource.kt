package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourceDM
import com.example.newsapp.domain.model.ApiResult

interface NewsLocalDataSource {

    suspend fun getSources(categoryId :String) : ApiResult<List<SourceDM>>
    suspend fun saveSources(sourcesResponse: List<SourceDM>)
    suspend fun getArticles(sourceId : String) : ApiResult<ArticlesResponse>
}