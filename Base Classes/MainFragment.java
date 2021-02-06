package com.example.myapplication;

import android.view.View;

public class MainFragment extends BaseFragment {

    private static final String TAG = "MainFragment";
    private static final String TEXT = "Hello World!";

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews(View view) {
        /*
         *  UI created
         *  your code
         */
        binding.tvText.setText(TEXT);
    }
}
