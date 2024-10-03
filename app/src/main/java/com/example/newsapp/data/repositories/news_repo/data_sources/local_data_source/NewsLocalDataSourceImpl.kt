package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source
import com.example.newsapp.data.database.dao.MyDataBase

class NewsLocalDataSourceImpl(var myDataBase: MyDataBase) : NewsLocalDataSource {

    override suspend fun getSources(categoryId: String): List<Source> {
        return myDataBase.getSourcesDao().getSources(categoryId)

    }

    override suspend fun saveSources(sourcesResponse: List<Source>) {
        myDataBase.getSourcesDao().insertSources(sourcesResponse)

    }

    override suspend fun getArticles(sourceId: String): ArticlesResponse {
        return ArticlesResponse(articles = emptyList())
    }
}