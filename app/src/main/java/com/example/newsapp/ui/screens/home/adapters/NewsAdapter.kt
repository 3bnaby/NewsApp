package com.example.newsapp.ui.screens.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.newsapp.R
import com.example.newsapp.data.api.model.Article
import com.example.newsapp.databinding.ItemNewsBinding

class NewsAdapter(var articles: List<Article?>) : Adapter<NewsAdapter.NewsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding : ItemNewsBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.item_news ,parent, false)
        return NewsViewHolder(binding)
    }

    override fun getItemCount(): Int = articles.size

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articles[position]
        holder.binding.article = article
//        Glide.with(holder.binding.root).load(article?.urlToImage).into(holder.binding.newsImage)
    }

    fun submitArticles(newArticle: List<Article?>) {
        articles = newArticle
        notifyDataSetChanged()
    }

    class NewsViewHolder(val binding: ItemNewsBinding) : ViewHolder(binding.root)

}