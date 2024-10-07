package com.example.newsapp.domain.usecases

import com.example.newsapp.data.api.model.ArticleDM
import com.example.newsapp.domain.model.ApiResult
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.repositories.NewsRepo
import javax.inject.Inject

class GetArticlesUseCase @Inject constructor(private val newsRepo: NewsRepo) {
    suspend fun execute(tabId:String): ApiResult<List<Article>>{
        return newsRepo.getArticles(tabId)
    }
}