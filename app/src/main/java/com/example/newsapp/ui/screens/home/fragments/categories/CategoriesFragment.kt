package com.example.newsapp.ui.screens.home.fragments.categories

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentCategoriesBinding
import com.example.newsapp.ui.base.BaseFragment
import com.example.newsapp.ui.model.Category
import com.example.newsapp.ui.screens.home.adapters.CategoriesAdapter

class CategoriesFragment : BaseFragment<FragmentCategoriesBinding>() {
    var categoriesAdapter = CategoriesAdapter(Category.categories)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initRecyclerView ()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater , container , false)

        return binding!!.root
    }
    fun initRecyclerView (){
        binding!!.categoriesRv.adapter = categoriesAdapter
    }

}
