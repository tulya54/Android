package com.example.myapplication;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseBindingActivity<ActivityMainBinding> {

    private static final String TAG = "MainActivity";
    private static final String TEXT = "Hello World!";

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        binding.tvText.setText(TEXT);
    }
}
