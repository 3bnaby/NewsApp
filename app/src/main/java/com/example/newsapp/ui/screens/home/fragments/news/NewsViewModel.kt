package com.example.newsapp.ui.screens.home.fragments.news

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.data.api.model.Article
import com.example.newsapp.data.api.model.Source
import com.example.newsapp.data.repositories.news_repo.NewsRepo
import kotlinx.coroutines.launch

class NewsViewModel : ViewModel() {
    val newsRepo = NewsRepo()
    val isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val sourcesLiveData: MutableLiveData<List<Source?>?> = MutableLiveData()
    val articlesLiveData: MutableLiveData<List<Article?>> = MutableLiveData()

    fun getSources(categoryId: String) {
        isLoadingLiveData.value = true // showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            try {
                val sourcesResponse =
                    newsRepo.getSources(categoryId)
                isLoadingLiveData.value = false //hideLoading()
                sourcesLiveData.value = sourcesResponse.sources!! // showTabs(sources!!)
            } catch (exception: Throwable) {
                errorLiveData.value = exception.localizedMessage ?: "Something went wrong.."
            }
        }
    }

    fun getArticles(tabId: String) {
        isLoadingLiveData.value = true // showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            try {
                var articleResponse =
                    newsRepo.getArticles(tabId)
                isLoadingLiveData.value = false //hideLoading()
                articlesLiveData.value = articleResponse.articles!!
            } catch (exception: Throwable) {
                errorLiveData.value = exception.localizedMessage ?: " "
            }
        }
    }

}