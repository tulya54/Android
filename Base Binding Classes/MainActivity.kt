package com.example.myapplication

import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    companion object{
        const val TAG = "MainActivity"
        const val TEXT = "Hello World!"
    }

    override fun initViews() {
        binding?.tvText?.text = TEXT
    }
}
