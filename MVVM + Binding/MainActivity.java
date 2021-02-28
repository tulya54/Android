package com.example.myapplication;

import com.example.myapplication.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<MainViewModel, ActivityMainBinding> {

    private static final String TAG = "MainActivity";

    @Override
    protected Class<MainViewModel> getViewModel() {
        return MainViewModel.class;
    }

    @Override
    protected int getLayoutResID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initViews() {
        viewModel.getResponse().observe(this, this::consumeResponse);
        viewModel.getClickResponse().observe(this, this::onClickResponse);
    }

    //  Api response
    private void consumeResponse(ApiResponse apiResponse) {

    }

    //  Click action
    private void onClickResponse(int btn) {
        switch (btn) {

        }
    }
}
