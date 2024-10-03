package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source

interface NewsLocalDataSource {

    suspend fun getSources(categoryId :String) :List<Source>
    suspend fun saveSources(sourcesResponse: List<Source>)
    suspend fun getArticles(sourceId : String) : ArticlesResponse
}