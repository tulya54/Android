package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  Add fragment
        supportFragmentManager
                .beginTransaction()
                .add(R.id.frame, FirstFragment.newInstance("Hello", 1))
                .commit()
        //  Call method in fragment
        init()
    }
    
    private fun init() {
        val fm = supportFragmentManager
        val fragments = fm.fragments
        for (fragment in fragments) {
            if (fragment is FirstFragment) {
                //  Your example code
                (fragment as FirstFragment).onBegin()
            }
        }
        //  Or get by index
        val lastFragment = fragments[fragments.size - 1]
    }
}
