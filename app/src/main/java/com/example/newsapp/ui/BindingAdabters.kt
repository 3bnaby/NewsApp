package com.example.newsapp.ui

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("imageUrl")
fun loadImageFromUrl(image: ImageView, imageUrl: String){

    Glide.with(image).load(imageUrl).into(image)

}