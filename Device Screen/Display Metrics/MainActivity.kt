package kz.snation.myapplication

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        onDisplayMetrics()
    }

   private fun onDisplayMetrics() {
       val displayMetrics: DisplayMetrics = resources.displayMetrics
       val windowManager: WindowManager = getSystemService(Context.WINDOW_SERVICE) as WindowManager
       windowManager.defaultDisplay.getMetrics(displayMetrics)
       val widthPixels = displayMetrics.widthPixels
       val heightPixels = displayMetrics.heightPixels
       val density = displayMetrics.density
       val densityDpi = displayMetrics.densityDpi
       val scaledDensity = displayMetrics.scaledDensity
       val xdpi = displayMetrics.xdpi
       val ydpi = displayMetrics.ydpi
   }
}
