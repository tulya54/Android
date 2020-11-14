package com.example.myapplication

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /**/
        initViews()
        /**/
        initWebChromeClient()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
    }

    private fun initWebChromeClient() {
        webView.webChromeClient = object : WebChromeClient() {

            private var mCustomView: View? = null
            private var mCustomViewCallback: CustomViewCallback? = null
            protected var mFullscreenContainer: FrameLayout? = null
            private var mOriginalOrientation: Int = 0
            private var mOriginalSystemUiVisibility: Int = 0

            override fun getDefaultVideoPoster(): Bitmap? {
                return if (mCustomView == null) {
                    null
                } else BitmapFactory.decodeResource(applicationContext.resources, 2130837573)
            }

            override fun onHideCustomView() {
                (window.decorView as FrameLayout).removeView(this.mCustomView)
                this.mCustomView = null
                window.decorView.systemUiVisibility = this.mOriginalSystemUiVisibility
                requestedOrientation = this.mOriginalOrientation
                this.mCustomViewCallback!!.onCustomViewHidden()
                this.mCustomViewCallback = null
            }

            override fun onShowCustomView(paramView: View, paramCustomViewCallback: CustomViewCallback) {
                if (this.mCustomView != null) {
                    onHideCustomView()
                    return
                }
                this.mCustomView = paramView
                this.mOriginalSystemUiVisibility = window.decorView.systemUiVisibility
                this.mOriginalOrientation = requestedOrientation
                this.mCustomViewCallback = paramCustomViewCallback
                (window.decorView as FrameLayout).addView(this.mCustomView, FrameLayout.LayoutParams(-1, -1))
                window.decorView.systemUiVisibility = 3846
            }

        }
    }
}

