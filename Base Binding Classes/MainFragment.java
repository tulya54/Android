package com.example.myapplication;

import android.view.View;
import com.example.myapplication.databinding.FragmentMainBinding;

public class MainFragment extends BaseFragment<FragmentMainBinding> {

    private static final String TAG = "MainFragment";
    private static final String TEXT = "Hello World!";

    @Override
    protected int getLayoutResID() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initViews(View view) {
        binding.tvText.setText(TEXT);
    }
}
