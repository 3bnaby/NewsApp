package com.example.newsapp.ui.screens.home.fragments.news

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapp.data.api.ApiManager
import com.example.newsapp.data.api.model.Article
import com.example.newsapp.data.api.model.ArticlesResponse
import com.example.newsapp.data.api.model.Source
import com.example.newsapp.data.api.model.SourcesResponse
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.model.Category
import com.example.newsapp.ui.screens.home.adapters.NewsAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsFragment(private var category: Category) : BaseFragment<FragmentNewsBinding>() {

    var newsAdapter = NewsAdapter(emptyList())
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this)[NewsViewModel::class.java]

        binding = FragmentNewsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        iniTabClickListener()
        viewModel.getSources(category.id)
        SetupObservers()

    }

    private fun initRecyclerView() {
        binding!!.newsRecyclerView.adapter = newsAdapter
    }

    private fun iniTabClickListener() {
        binding!!.tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                viewModel.getArticles(tab!!.tag as String)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }

        })
    }


    private fun showTabs(sources: List<Source?>) {
        for (source in sources) {
            val tab = binding!!.tabLayout.newTab()
            tab.text = source?.name
            tab.tag = source?.id
            binding!!.tabLayout.addTab(tab)
        }
        setMargins()

    }

    private fun setMargins() {
        for (i in 0 until binding!!.tabLayout.tabCount) {
            val tab = (binding!!.tabLayout.getChildAt(0) as ViewGroup).getChildAt(i)
            val layoutParams = tab.layoutParams as ViewGroup.MarginLayoutParams
            layoutParams.setMargins(15, 0, 15, 0)  // Example margins
            tab.layoutParams = layoutParams
        }

    }

    private fun showError(errorMessage: String, onRetryClick: (() -> Unit)? = null) {
        binding!!.errorView.root.visibility = View.VISIBLE
        binding!!.errorView.errorText.text = errorMessage
        binding!!.errorView.retryButton.setOnClickListener {
            onRetryClick?.invoke()
        }

    }

    private fun hideError() {
        binding!!.errorView.root.visibility = View.INVISIBLE
    }

    private fun showLoading() {
        binding!!.loaderView.visibility = View.VISIBLE

    }

    private fun hideLoading() {
        binding!!.loaderView.visibility = View.INVISIBLE
    }


    private fun SetupObservers() {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner) {
            if (it) showLoading()
            else hideLoading()
        }
        viewModel.errorLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) hideError()
            else showError(it)
        }
        viewModel.sourcesLiveData.observe(viewLifecycleOwner) {
            if (it.isNullOrEmpty()) return@observe
            else showTabs(it)
        }
        viewModel.articlesLiveData.observe(viewLifecycleOwner) {
            newsAdapter.submitArticles(it)
        }

    }
}