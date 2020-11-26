package com.example.myapplication

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))
        /**/
        init()
    }

    private fun init() {
        //  A) drawables with theme attributes
        ContextCompat.getDrawable(this, android.R.drawable.btn_star)
        //  B) drawables without theme attributes
        ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star, null)
        //  C) drawables with theme attributes from another theme
        ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star, theme)
    }
}

