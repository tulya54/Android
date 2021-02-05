package com.example.myapplication;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    public static final String TAG = "BaseActivity";
    //  Your layout
    @LayoutRes
    protected abstract int getLayoutResID();
    //  Initialize your views and start code
    protected abstract void initViews(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onBegin();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(setContentView(), container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        onEnd();
    }

    //  Method override, catch create fragment
    protected void onBegin() {

    }

    //  Method override, catch destroy activity
    protected void onEnd() {

    }

    //  This method called system button Home
    public void onBack() {
        getBaseActivity().onBackPressed();
    }

    public BaseActivity getBaseActivity() {
        return ((BaseActivity)getActivity());
    }

    //  This method called from outside, for example, activity, fragment manager...
    public void onNetworkChangeUI(boolean isInternet) {
        //  Change UI in fragment

    }

    //  Resources ID
    @NonNull
    public String getStr(@StringRes int id) {
        /*
         *  If your app supported more one language,
         *  you make add your locale
         *  example -> yourResources.getString(id);
         */
        return getString(id);
    }

    @NonNull
    public String concatStr(String text) {
        /*
         * Concat all your text, strings and resources,
         * to one String
         */
        return text;
    }

    @Nullable
    public Drawable getImg(@DrawableRes int id) {
        //  Short method name and override future deprecated methods
        return ContextCompat.getDrawable(getBaseActivity(), id);
    }

    @ColorInt
    public int getClr(@ColorRes int id) {
        //  Short method name and override future deprecated methods
        return ContextCompat.getColor(getBaseActivity(), id);
    }
}

