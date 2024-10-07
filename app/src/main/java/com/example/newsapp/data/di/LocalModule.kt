package com.example.newsapp.data.di

import android.content.Context
import androidx.room.Room
import com.example.newsapp.data.database.dao.MyDataBase
import com.example.newsapp.data.database.dao.MyDataBase.Companion.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    @Provides
    @Singleton
    fun provideDataBase(context: Context):MyDataBase{
        return Room.databaseBuilder(context, MyDataBase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration()
            //.allowMainThreadQueries() ///no need since we used coroutines
            .build()
    }
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext context: Context) :Context{
        return context
    }

}