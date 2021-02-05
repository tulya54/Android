package com.example.myapplication

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

abstract class BaseActivity(@LayoutRes private val layoutResID: Int): AppCompatActivity() {

    companion object {
        const val TAG = "BaseActivity"
    }
    //  Initialize your views and start code
    protected abstract fun initViews()
    //  Interceptor for catch internet status
    private val internetReceiver = object: BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            val noConnect = intent?.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false)
            onNetworkChangeUI(!noConnect!!)
        }

    }

    //  Method override for change UI if there is no internet
    open fun onNetworkChangeUI(isInternet: Boolean) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  Before UI create
        onBegin()
        //  Override Resources ID Layout
        setContentView(layoutResID)
        //  After UI create and initialize all views inside this layout and access all widgets by ID
        initViews()
    }

    override fun onResume() {
        super.onResume()
        //  Bind CONNECTIVITY_ACTION for check network status
        try {
            registerReceiver(internetReceiver, IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (e: Exception) {
            //  Your code
        }
    }

    override fun onPause() {
        super.onPause()
        //  Unbind CONNECTIVITY_ACTION
        try {
            unregisterReceiver(internetReceiver);
        } catch (e: Exception) {
            //  Your code
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        onEnd()
    }

    //  Method override, catch create fragment
    open fun onBegin() {

    }

    //  Method override, catch destroy activity
    open fun onEnd() {

    }

    //  Resources ID
    open fun getStr(@StringRes id: Int): String {
        /*
         *  If your app supported more one language,
         *  you make add your locale
         *  example -> yourResources.getString(id);
         */
        return getString(id)
    }

    open fun concatStr(text: String): String {
        /*
         * Concat all your text, strings and resources,
         * to one String
         */
        return text
    }

    @Nullable
    open fun getImg(@DrawableRes id: Int): Drawable? {
        //  Short method name and override future deprecated methods
        return ContextCompat.getDrawable(this, id)
    }

    @ColorInt
    open fun getClr(@ColorRes id: Int): Int {
        //  Short method name and override future deprecated methods
        return ContextCompat.getColor(this, id)
    }
}
