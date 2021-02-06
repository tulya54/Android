package com.example.myapplication

class MainActivity : BaseActivity(R.layout.activity_main) {

    companion object {
        const val TAG = "MainActivity"
        const val TEXT = "Hello World!"
    }
    
    override fun initViews() {
        /*
         *  UI created
         *  your code
         */
        binding?.tvText?.text = TEXT
    }
}
