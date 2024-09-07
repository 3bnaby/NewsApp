package com.example.newsapp.ui.base

import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity<Binding> : AppCompatActivity() {
    var binding : Binding? =null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}