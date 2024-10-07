package com.example.newsapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.example.newsapp.data.api.model.SourceDM


@Dao
interface SourcesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSources(source: List<SourceDM>)

    @androidx.room.Query("select * from SourceDM where category = :categoryId")
    suspend fun getSources(categoryId : String): List<SourceDM>
}