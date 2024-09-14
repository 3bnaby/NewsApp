package com.example.newsapp.api

import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiManager {
    companion object {
        const val API_KEY = "12698f6da0d446bf9016c1107f76ac47"
        const val BASE_URL = "https://newsapi.org"
        private var retrofit: Retrofit? = null

        private fun getInstance(): Retrofit {
            if (retrofit == null) {

                val logging = HttpLoggingInterceptor { Log.e("API_CALL", it) }
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)

                var okHttpClient = OkHttpClient.Builder().addInterceptor(logging).build()
                retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
            }
            return retrofit!!
        }

        fun webServices(): WebServices {
            return getInstance().create(WebServices::class.java)

        }
    }
}