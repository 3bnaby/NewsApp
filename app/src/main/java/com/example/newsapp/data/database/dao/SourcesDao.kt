package com.example.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsapp.data.api.model.Source
import retrofit2.http.Query


@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(source: List<Source>)

    @androidx.room.Query("select * from Source where category = :categoryId")
    suspend fun getSources(categoryId : String): List<Source>
}