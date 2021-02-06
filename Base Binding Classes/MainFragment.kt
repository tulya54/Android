package com.example.myapplication

import android.view.View
import com.example.myapplication.databinding.FragmentMainBinding

class MainFragment: BaseFragment<FragmentMainBinding>(R.layout.fragment_main) {

    companion object{
        const val TAG = "MainFragment"
        const val TEXT = "Hello World!"
    }

    override fun initViews(view: View) {
        /*
         *  UI created
         *  your code
         */
        binding?.tvText?.text = TEXT
    }
}
