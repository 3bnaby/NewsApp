package com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source

import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.SourcesResponse

class NewsLocalDataSource {

     suspend fun getSources(categoryId :String) : SourcesResponse{
         throw Exception("not implemented yet")

    }
    suspend fun saveSources(categoryId: String , sourcesResponse: SourcesResponse){

    }

   suspend fun getArticles(sourceId : String) : ArticlesResponse{
       throw Exception("not implemented yet")

    }
}