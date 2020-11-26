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
        //  Get color for TextView
        val textColor = ContextCompat.getColor(this, android.R.color.white)
        //  Get color background for Views
        val backgroundColorNoTheme = ResourcesCompat.getDrawable(resources, android.R.color.white, null)
        //  Get color background for Views
        val backgroundColorWithTheme = ResourcesCompat.getDrawable(resources, android.R.color.white, theme)
        //  A) drawables with theme attributes
        val drawable = ContextCompat.getDrawable(this, android.R.drawable.btn_star)
        //  B) drawables without theme attributes
        val drawableNoTheme = ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star, null)
        //  C) drawables with theme attributes from another theme
        val drawableWithTheme = ResourcesCompat.getDrawable(resources, android.R.drawable.btn_star, theme)
    }
}
