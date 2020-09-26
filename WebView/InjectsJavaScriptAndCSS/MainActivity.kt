package com.example.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.webkit.WebView
import androidx.webkit.WebViewClientCompat

class MainActivity : AppCompatActivity() {

    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*  Init views  */
        initViews()
        /*  Init webView methods  */
        initWebView()
    }

    private fun initViews() {
        webView = findViewById(R.id.webView)
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = object : WebViewClientCompat() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                /*  Inject JavaScript to HTML  */
                injectJavaScript()
                /*  Inject CSS to HTML  */
                injectCSS()
            }
        }
    }

    /*           JavaScript           */
    private fun injectJavaScript() {
        fun injectJavaScript() {
            try {
                val inputStream = assets.open("myJavaScript.js")
                val buffer = ByteArray(inputStream.available())
                inputStream.read(buffer)
                inputStream.close()
                val encoded = Base64.encodeToString(buffer , Base64.NO_WRAP)
                webView.loadUrl("javascript:(function() {" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var script = document.createElement('script');" +
                        "script.type = 'text/javascript';" +
                        // don't forget to use decodeURIComponent after base64 decoding
                        //      "script.innerHTML = decodeURIComponent(window.atob('" + encoded + "'));" +
                        "script.innerHTML = window.atob('" + encoded + "');" +
                        "parent.appendChild(script)" +
                        "})()");
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    /*           CSS           */
    private fun injectCSS() {
        try {
            val inputStream = assets.open("myCSS.css")
            val buffer = ByteArray(inputStream.available())
            inputStream.read(buffer)
            inputStream.close()
            val encoded = Base64.encodeToString(buffer , Base64.NO_WRAP)
            webView.loadUrl(
                "javascript:(function() {" +
                        "var parent = document.getElementsByTagName('head').item(0);" +
                        "var style = document.createElement('style');" +
                        "style.type = 'text/css';" +
                        // Tell the browser to BASE64-decode the string into your script !!!
                        "style.innerHTML = window.atob('" + encoded + "');" +
                        "parent.appendChild(style)" +
                        "})()"
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
