package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source
import com.example.newsapp.data.api.model.SourcesResponse
import com.example.newsapp.data.database.dao.MyDataBase

class NewsLocalDataSource {

     suspend fun getSources(categoryId :String) :List<Source?>{
         return MyDataBase.getInstance().getSourcesDao().getSources(categoryId)

    }
    suspend fun saveSources(sourcesResponse: List<Source>){
        MyDataBase.getInstance().getSourcesDao().insertSources(sourcesResponse)

    }

   suspend fun getArticles(sourceId : String) : ArticlesResponse{
       throw Exception("not implemented yet")

    }
}