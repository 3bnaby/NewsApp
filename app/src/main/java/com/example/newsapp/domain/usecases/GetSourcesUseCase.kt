package com.example.newsapp.domain.usecases

import com.example.newsapp.data.api.model.SourceDM
import com.example.newsapp.domain.model.ApiResult
import com.example.newsapp.domain.model.Source
import com.example.newsapp.domain.repositories.NewsRepo
import javax.inject.Inject

class GetSourcesUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun execute(categoryId:String): ApiResult<List<Source>> {
        return newsRepo.getSources(categoryId)
    }
}