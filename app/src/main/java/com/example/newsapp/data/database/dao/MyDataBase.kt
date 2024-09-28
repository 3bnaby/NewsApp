package com.example.newsapp.data.database.dao

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapp.data.api.model.Source

@Database(entities = [Source::class], version = 1)
abstract class MyDataBase() : RoomDatabase() {

    abstract fun getSourcesDao(): SourcesDao

    companion object{
        private const val DATABASE_NAME = "database_name"
        private var  myDataBase : MyDataBase? = null

        fun init(context: Context){
            if (myDataBase == null){
                myDataBase = Room.databaseBuilder(context, MyDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    //.allowMainThreadQueries() ///no need since we used coroutines
                    .build()
            }
        }
        fun getInstance() : MyDataBase = myDataBase!!
    }
}