package com.example.myapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment

abstract class BaseFragment(@LayoutRes private val layoutResID: Int): Fragment() {

    companion object {
        const val TAG = "BaseFragment"
    }
    //  Initialize your views and start code
    protected abstract fun initViews(view: View)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //  Before UI create
        onBegin()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(layoutResID, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //  After UI create and initialize all views inside this layout and access all widgets by ID
        initViews(view)
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

    //  This method called system button Home
    open fun onBack() {
        getBaseActivity().onBackPressed()
    }

    open fun getBaseActivity(): BaseActivity {
        return activity as BaseActivity
    }

    //  This method called from outside, for example, activity, fragment manager...
    open fun onNetworkChangeUI(isInternet: Boolean ) {
        //  Change UI in fragment
    }

    @Nullable
    open fun getImg(@DrawableRes id: Int): Drawable? {
        //  Short method name and override future deprecated methods
        return ContextCompat.getDrawable(getBaseActivity(), id)
    }

    @ColorInt
    open fun getClr(@ColorRes id: Int): Int {
        //  Short method name and override future deprecated methods
        return ContextCompat.getColor(getBaseActivity(), id)
    }
}
