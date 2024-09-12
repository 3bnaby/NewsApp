package com.example.newsapp.ui.screens.home

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.core.view.children
import androidx.core.view.setPadding
import com.example.newsapp.api.ApiManager
import com.example.newsapp.api.model.ArticlesResponse
import com.example.newsapp.api.model.Source
import com.example.newsapp.api.model.SourcesResponse
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.screens.home.adapters.NewsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.Exception

class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    lateinit var adapter : NewsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        initRecyclerView()
        iniTabClickListener()
        getSources()

            }

    private fun initRecyclerView(){
        adapter = NewsAdapter(emptyList())
        binding!!.newsRecyclerView.adapter = adapter
    }

    private fun iniTabClickListener() {
        binding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }

    private fun getArticles(tabId: String) {
        showLoading()
        hideError()
        ApiManager.webServices().getArticles(ApiManager.API_KEY , tabId)
            .enqueue(object : Callback<ArticlesResponse>{
                override fun onResponse(
                    p0: Call<ArticlesResponse>,
                    responce: Response<ArticlesResponse>
                ) {
                    hideLoading()
                    if (responce.isSuccessful){
                        adapter.submitArticles(responce.body()!!.articles!!)

                    }else {
                        var articlesResponce : ArticlesResponse = Gson().fromJson(responce.errorBody() ?.string() ,
                            ArticlesResponse::class.java)
                        showError(articlesResponce.message ?: " "){
                            getArticles(tabId)
                        }

                    }
                }

                override fun onFailure(p0: Call<ArticlesResponse>, exception: Throwable) {
                    showError(exception.localizedMessage ?: " "){
                        getArticles(tabId)
                    }
                }

            })

    }

    private fun getSources() {
        showLoading()
        hideError()
        ApiManager.webServices().getSources(ApiManager.API_KEY)
            .enqueue(object : Callback<SourcesResponse>{
                override fun onResponse(p0: Call<SourcesResponse>, responce: Response<SourcesResponse>) {
                    hideLoading()
                    if (responce.isSuccessful){
                        try {
                            val sources = responce.body()!!.sources
                            showTabs(sources!!)

                        }catch (e:Throwable){
                            showError(e.message ?: "--"){
                                getSources()
                            }

                        }

                    }else{
                        val errorBodyString = responce.errorBody()!!.string()
                        val errorResponse : SourcesResponse = Gson().fromJson(
                            errorBodyString,
                            SourcesResponse::class.java
                        )
                        showError(errorResponse.message ?: ".."){
                            getSources()
                        }
                    }
                }

                override fun onFailure(p0: Call<SourcesResponse>, exception: Throwable) {
                    hideLoading()
                    showError(exception.localizedMessage ?: "Something went wrong.."){
                        getSources()
                    }
                }

            })
    }

    private fun showTabs(sources : List<Source?>) {
        for (source in sources){
            val tab = binding!!.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source?.id
            binding!!.tabLayout.addTab(tab)
            getMargins()
        }

    }
    private fun getMargins(){
        for (tab in 0 until binding!!.tabLayout.tabCount) {
            val tab = (binding!!.tabLayout.getChildAt(0) as ViewGroup).getChildAt(tab)
            val layoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(10, 0, 10, 0)  // Example margins
            tab.layoutParams = layoutParams
        }

    }

    private fun showError(errorMessage : String , onRetryClick: () -> Unit) {
        binding!!.errorView.root.visibility = View.VISIBLE
        binding!!.errorView.errorText.text = errorMessage
        binding!!.errorView.retryButton.setOnClickListener{
            onRetryClick()
        }

    }

    private fun hideError(){
        binding!!.errorView.root.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding!!.loaderView.visibility = View.VISIBLE

    }

    private fun hideLoading(){
        binding!!.loaderView.visibility = View.INVISIBLE
    }
}