package com.example.newsapp.ui.screens.home.fragments.NewsContent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.newsapp.R
import com.example.newsapp.databinding.FragmentNewsBinding
import com.example.newsapp.databinding.FragmentNewsContentBinding
import com.example.newsapp.ui.base.BaseFragment


class NewsContentFragment : BaseFragment<FragmentNewsContentBinding>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewsContentBinding.inflate(inflater, container, false)
        return binding!!.root
    }



}