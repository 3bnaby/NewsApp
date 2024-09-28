package com.example.newsapp.ui.screens.home

import android.os.Bundle
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.newsapp.R
import com.example.newsapp.data.utilis.InternetConnectionChecker
import com.example.newsapp.databinding.ActivityHomeBinding
import com.example.newsapp.ui.base.BaseActivity
import com.example.newsapp.ui.screens.home.fragments.categories.CategoriesFragment
import com.example.newsapp.ui.screens.home.fragments.news.NewsFragment


class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    private val categoriesFragment = CategoriesFragment {
        showFragment(NewsFragment(it))
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding!!.root)
        //todo : Remove this line with something better
        //InternetConnectionChecker.context = this  ///will use it in MyApplication Class
        showFragment(categoriesFragment)
        showSideMenu()
        onItemMenuClicked()


    }

    private fun showFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun showSideMenu() {
        binding!!.appBarLayout.iconDrawer.setOnClickListener {
            binding!!.drawerLayout.openDrawer(GravityCompat.START)
        }
    }


    private fun onItemMenuClicked(){
        binding!!.drawerNavigationView.setNavigationItemSelectedListener { item ->
            binding!!.drawerLayout.closeDrawer(GravityCompat.START)

            if (item.itemId == R.id.categories_menu_item){
                showFragment(categoriesFragment)
                return@setNavigationItemSelectedListener true

            }else if (item.itemId == R.id.settings_menu_item){
                Toast.makeText(this , "Not implemented yet" , Toast.LENGTH_SHORT).show()
                return@setNavigationItemSelectedListener true
            }
            return@setNavigationItemSelectedListener true
        }
    }


}