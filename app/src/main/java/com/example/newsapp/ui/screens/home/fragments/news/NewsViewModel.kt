package com.example.newsapp.ui.screens.home.fragments.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapp.domain.model.ApiResult
import com.example.newsapp.domain.model.Article
import com.example.newsapp.domain.model.Source
import com.example.newsapp.domain.usecases.GetArticlesUseCase
import com.example.newsapp.domain.usecases.GetSourcesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(
    val getArticlesUseCase: GetArticlesUseCase, val getSourcesUseCase: GetSourcesUseCase
) : ViewModel() {

    private val _isLoadingLiveData: MutableLiveData<Boolean> = MutableLiveData()
    val isLoadingLiveData: LiveData<Boolean> = _isLoadingLiveData
    val errorLiveData: MutableLiveData<String?> = MutableLiveData()
    val sourcesLiveData: MutableLiveData<List<Source>> = MutableLiveData()
    val articlesLiveData: MutableLiveData<List<Article>> = MutableLiveData()

    fun getSources(categoryId: String) {
        _isLoadingLiveData.value = true // showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            val result = getSourcesUseCase.execute(categoryId)
            when (result) {
                is ApiResult.Error -> {
                    errorLiveData.value = result.errorMessage

                }

                is ApiResult.Success -> {
                    _isLoadingLiveData.value = false //hideLoading()
                    sourcesLiveData.value = result.data // showTabs(sources!!)
                }

            }
//            try {
//                val sources = getSourcesUseCase.execute(categoryId)
//                _isLoadingLiveData.value = false //hideLoading()
//                sourcesLiveData.value = sources // showTabs(sources!!)
//            } catch (exception: Throwable) {
//                errorLiveData.value = exception.localizedMessage ?: "Something went wrong.."
//            }
        }
    }

    fun getArticles(tabId: String) {
        _isLoadingLiveData.value = true // showLoading()
        errorLiveData.value = null //hideError()
        viewModelScope.launch {
            val result = getArticlesUseCase.execute(tabId)
            when (result) {
                is ApiResult.Error -> {
                    errorLiveData.value = result.errorMessage
                }

                is ApiResult.Success -> {
                    _isLoadingLiveData.value = false //hideLoading()
                    articlesLiveData.value = result.data
                }
            }

//            try {
//                var articles = getArticlesUseCase.execute(tabId)
//                _isLoadingLiveData.value = false //hideLoading()
//                articlesLiveData.value = articles!!
//            } catch (exception: Throwable) {
//                errorLiveData.value = exception.localizedMessage ?: " "
//            }
        }
    }

}