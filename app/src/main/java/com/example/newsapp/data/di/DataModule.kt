package com.example.newsapp.data.di

import com.example.newsapp.data.repositories.news_repo.NewsRepo
import com.example.newsapp.data.repositories.news_repo.NewsRepoImpl
import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.local_data_source.NewsLocalDataSourceImpl
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSource
import com.example.newsapp.data.repositories.news_repo.data_sources.remote_data_sources.NewsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DataModule {
    @Binds
    abstract fun bindNewsRepo(newsRepoImpl: NewsRepoImpl): NewsRepo

    @Binds
    abstract fun bindNewsLocalDataSource(newsLocalDataSource:NewsLocalDataSourceImpl):NewsLocalDataSource

    @Binds
    abstract fun bindNewsRemoteDataSource (newsRemoteDataSource: NewsRemoteDataSourceImpl):NewsRemoteDataSource


}