package com.example.newsapp.ui.screens.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.screens.home.fragments.categories.CategoriesFragment


class HomeActivity : BaseActivity<ActivityHomeBinding>() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        showFragment(CategoriesFragment())

    }

    fun showFragment(fragment : Fragment){
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container , fragment)
            .commit()
    }


}