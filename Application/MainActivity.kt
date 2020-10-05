package com.example.myapplication

import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))
        /*  Check app version in Google play store */
        onCheckAppVersion()
    }

     private fun onCheckAppVersion() {
         val uniqueWord = ""
        /*  THIS YOUR APPLICATION ID  */
        val thisPackageName = "com.example.myapplication"
        /*  Link from getting APK info  */
        val URL = "https://play.google.com/store/apps/details?id=$thisPackageName&hl=en"
        /*  Launch background trhead  */
        val thread = Thread(Runnable {
            try {
                /*  Get package info for @versionName */
                val currentVersion = context.packageManager.getPackageInfo(context.packageName, 0).versionName
                /*  Get versionName in Google Play Store  */
                val newVersion = org.jsoup.Jsoup.connect(URL)
                    .timeout(30000)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get()
                    .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                    .first()
                    .ownText()
                val currentVersionCode = getVersionCode(currentVersion)
                val newVersionVersionCode = getVersionCode(newVersion)
                /*  Equal current and new version  */
                if (currentVersionCode.versionCode < newVersionVersionCode.versionCode) {
                    if (newVersionVersionCode.result == uniqueWord) {
                        Log.d("TAG", "Your code to main thread")
                    } else {
                        Log.d("TAG", "Your code to main thread")
                    }
                    Log.d("TAG", "Your code to main thread")
                } else {
                    Log.d("TAG", "Your code to main thread")
                }
                Log.d("TAG", "result: ")
            } catch (e: PackageManager.NameNotFoundException) {
                e.printStackTrace()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        })
        thread.start()
    }
    
    private fun getVersionCode(version: String, isNineDigitNumber: Boolean = false): ModelVersionCode {
        val versionNames = version.split("\\.".toRegex()).toTypedArray()
        val size = versionNames.size
        if (size == 3) {
            val isNumeric = BooleanArray(3)
            val names = arrayOf("major", "minor", "patch")
            for (i in 0..2) {
                isNumeric[i] = isNumericArray(versionNames.get(i))
            }
            for (i in 0..1) {
                if (!isNumeric[i]) {
                    try {
                        throw IllegalArgumentException("Semantic Versioning: " + names[i] + "it's not number")
                    } catch (e: Exception) { e.printStackTrace() }
                }
            }
            if (isNumeric[0] && isNumeric[1]) {
                val major: Long = versionNames[0].toLong()
                val minor: Long = versionNames[1].toLong()
                var patch: Long = 0
                var result = ""

                if (isNumeric[2]) {
                    patch = versionNames[2].toLong()
                    result = "empty"
                } else {
                    val patchWord: Array<String> = versionNames[2].split("-".toRegex()).toTypedArray()
                    if (patchWord.size == 2) {
                        patch = patchWord[0].toLong()
                        result = patchWord[1]
                    }
                }
                if (isNineDigitNumber) {
                    val decimalFormat: java.text.DecimalFormat
                    try {
                        decimalFormat = java.text.DecimalFormat("00")
                        val nineDigitNumber = major.toString() + decimalFormat.format(versionNames[1].length) + decimalFormat.format(patch.toString().length)
                        val versionCode: Long = nineDigitNumber.toLong()
                        Log.d("TAG", "versionCode: ${versionCode}, result: ${result}")
                        return ModelVersionCode(versionCode, result)
                    } catch (e: NullPointerException) {
                        e.printStackTrace()
                    } catch (e:NumberFormatException) {
                        e.printStackTrace()
                    }
                } else {
                    val versionCode: Long = major * 10000 +  minor * 100 + patch
                    Log.d("TAG", "versionCode: ${versionCode}, result: ${result}")
                    return ModelVersionCode(versionCode, result)
                }
            }
        } else {
            try {
                throw IllegalArgumentException("VersionNames size dots not two")
            } catch (e: Exception) { e.printStackTrace() }
        }
        Log.d("TAG", "versionCode: 0, result: empty")
        return ModelVersionCode(0, "empty")
    }

    private fun isNumericArray(str: String?): Boolean {
        if (str == null) return false
        for (c in str.toCharArray()) if (c < '0' || c > '9') return false
        return true
    }
}
