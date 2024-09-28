package com.example.newsapp.ui

import android.app.Application
import com.example.newsapp.data.database.dao.MyDataBase
import com.example.newsapp.data.utilis.InternetConnectionChecker

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        //todo : Remove this line with something better
        InternetConnectionChecker.context = this
        MyDataBase.init(this)
    }
}