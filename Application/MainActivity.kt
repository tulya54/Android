package com.example.myapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import org.jsoup.Jsoup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))
        /*  Check app version in Google play store */
        onCheckAppVersion()
    }

     private fun onCheckAppVersion() {
        var currentVersion = ""
        try {
            //Get package info
            val pInfo = packageManager.getPackageInfo(packageName, 0)
            //Get version name (not binary)
            currentVersion = pInfo.versionName
            Log.e("TAG", "")
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }
        /*  THIS YOUR APPLICATION ID  */
        val thisPackageName = "com.example.myapplication"
        //Link from getting APK info
        val URL = "https://play.google.com/store/apps/details?id=$thisPackageName&hl=en"
        val thread = Thread(Runnable {
            val newVersion = Jsoup.connect(URL)
                .timeout(30000)
                .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                .referrer("http://www.google.com")
                .get()
                .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                .first()
                .ownText()
            if (currentVersion == newVersion) {
                /*  YOUR CODE  */
            } else {
                /*  YOUR CODE  */
            }
         })
         thread.start()
    }
}
