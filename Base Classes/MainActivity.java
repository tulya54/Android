package com.example.myapplication;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";
    private static final String TEXT = "Hello World!";

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        /*
         *  UI created
         *  your code
         */
        binding.tvText.setText(TEXT);
    }
}
