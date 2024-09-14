package com.example.newsapp.ui.screens.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.ui.model.Category
import com.google.android.material.color.MaterialColors

class CategoriesAdapter(var categories: List<Category>) :
    Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val binding =
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoriesViewHolder(binding)
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        val category = categories[position]
        holder.binding.itemCategoryImage.setImageResource(category.imageId)
        holder.binding.itemCategoryNameTv.text = category.title
        holder.binding.itemCategoryContainer.setBackgroundColor(ContextCompat.getColor(holder.binding.root.context , category.backgroundColorId))

    }


    class CategoriesViewHolder(val binding: ItemCategoryBinding) : ViewHolder(binding.root)

}