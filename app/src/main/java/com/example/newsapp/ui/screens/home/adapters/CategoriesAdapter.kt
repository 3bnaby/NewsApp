package com.example.newsapp.ui.screens.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.newsapp.R
import com.example.newsapp.databinding.ItemCategoryBinding
import com.example.newsapp.ui.model.Category

class CategoriesAdapter(private var categories: List<Category>, private var onItemClick :(Category) -> Unit) :
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
        holder.binding.root.setOnClickListener{
            onItemClick.invoke(category)
        }

        holder.binding.itemCategoryContainer.setBackgroundResource(
            if (category.isLeftSided) R.drawable.left_side_item_category_rounded_corners
            else R.drawable.right_side_item_category_rounded_corners
        )

        holder.binding.itemCategoryContainer.setBackgroundColor(
            ContextCompat.getColor(
                holder.binding.itemCategoryContainer.context,
                category.backgroundColorId
            )
        )


    }


    class CategoriesViewHolder(val binding: ItemCategoryBinding) : ViewHolder(binding.root)

}