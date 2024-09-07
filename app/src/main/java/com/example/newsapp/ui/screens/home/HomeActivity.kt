package com.example.newsapp.ui.screens.home

import android.os.Bundle
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesResponse
import com.example.newsapp.api.model.Source
import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.base.BaseActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : BaseActivity<ActivityHomeBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        getSources()
        iniTabClickListener()

            }

    private fun iniTabClickListener() {
        binding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                TODO("Not yet implemented")
            }

        })
    }

    private fun getArticles(tabId: String) {
        ApiManager.webServices().getArticles(ApiManager.API_KEY , tabId)
            .enqueue(object : Callback<ArticlesResponse>{
                override fun onResponse(
                    p0: Call<ArticlesResponse>,
                    p1: Response<ArticlesResponse>
                ) {
                    TODO("Not yet implemented")
                }

                override fun onFailure(p0: Call<ArticlesResponse>, p1: Throwable) {
                    TODO("Not yet implemented")
                }

            })

    }

    private fun getSources() {
        ApiManager.webServices().getSources(ApiManager.API_KEY)
            .enqueue(object : Callback<SourcesResponse>{
                override fun onResponse(p0: Call<SourcesResponse>, responce: Response<SourcesResponse>) {
                    if (responce.isSuccessful){
                        try {
                            val sources = responce.body()!!.sources
                            showTabs(sources!!)

                        }catch (e:Throwable){

                        }

                    }else{

                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, exception: Throwable) {
                    TODO("Not yet implemented")
                }

            })
    }

    private fun showTabs(sources : List<Source?>) {
        for (source in sources){
            val tab = binding!!.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source?.id
            binding!!.tabLayout.addTab(tab)

        }



    }
}