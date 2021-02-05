package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.os.Bundle;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public abstract class BaseActivity extends AppCompatActivity  {

    public static final String TAG = "BaseActivity.";
    //  @getContentView() Your layout
    protected abstract int getContentView();
    //  @initViews() Initialize your views and start code
    protected abstract void initViews();
    //  Interceptor for catch internet status
    private final BroadcastReceiver internetReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean noConnect = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            onNetworkChangeUI(!noConnect);
        }
    };
    //  Method override for change UI if there is no internet
    public void onNetworkChangeUI(boolean isInternet) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  Bind CONNECTIVITY_ACTION for check network status
        try {
            registerReceiver(internetReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (Exception e) {
            //  Your code
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //  Unbind CONNECTIVITY_ACTION
        try {
            unregisterReceiver(internetReceiver);
        } catch (Exception e) {
            //  Your code
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onEnd();
    }

    //  Method override, catch destroy activity
    protected void onEnd() {

    }
    //  Resources ID
    @NonNull
    public String getStr(@StringRes int id) {
        return getString(id);
    }

    @NonNull
    public String concatStr(String text) {
        return text;
    }

    @Nullable
    public Drawable getImg(@DrawableRes int id) {
        return ContextCompat.getDrawable(this, id);
    }

    @ColorInt
    public int getClr(@ColorRes int id) {
        return ContextCompat.getColor(this, id);
    }
}
