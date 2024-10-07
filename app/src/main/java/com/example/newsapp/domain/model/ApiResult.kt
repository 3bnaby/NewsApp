package com.example.newsapp.domain.model

sealed class ApiResult<out T> {
    class Success<out T>(val data : T) :ApiResult<T>()
    class Error(val errorMessage: String) : ApiResult<Nothing>()

}