package com.example.newsapp.domain.mappers

import com.example.newsapp.data.api.model.ArticleDM
import com.example.newsapp.domain.model.Article
import javax.inject.Inject

class ArticleMapper @Inject constructor() {
    fun mapArticleDMToArticle(articleDM: ArticleDM): Article {
        return Article(
            imageUrl = articleDM.urlToImage ?: "",
            author = articleDM.author ?: "",
            title = articleDM.title ?: "",
            date = articleDM.publishedAt ?: "",
        )
    }

    fun mapArticlesDMToArticles(articles: List<ArticleDM>): List<Article> {
        return articles.map {
            mapArticleDMToArticle(it)

        }
    }
}