package kz.snation.myapplication

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvText: TextView
    companion object Variable {
        private val TAG = "TAG"
    }
    private var customHandler: CustomHandler? = null
    private var range: Int = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  Init Views
        initViews()
        //  Init Handler
        initHandler()
        //  Use
        onStartHandler()
    }

    private fun initViews() {
        tvText = findViewById(R.id.tvText)
    }

    private fun initHandler() {
        customHandler = CustomHandler(this)
    }

    private fun onStartHandler() {
        val t = Thread(Runnable {
             run {
                 //  start iterate = 5
                 for (i in 1..range) {
                     try {
                         Thread.sleep(1000)
                         customHandler!!.sendEmptyMessage(i)
                     } catch (e: Exception) {
                         e.printStackTrace()
                     }

                 }
             }
        })
        t.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (customHandler != null) {
            customHandler!!.onDestroyHandler()
            customHandler = null
        }
    }

    /*  Inner Class  */
    @SuppressLint("HandlerLeak")
    private inner class CustomHandler(activity: MainActivity ): android.os.Handler() {

        private var activity: java.lang.ref.WeakReference<MainActivity>? = null

        init {
            this.activity = java.lang.ref.WeakReference<MainActivity>(activity)
        }

        override fun handleMessage(msg: Message) {
            val any: Any = this.activity!!.get()!!
            val activity: MainActivity = any as MainActivity
            if (activity == null) {
                return;
            }
            Log.e(TAG, "Increment " + msg.what)
            activity.tvText.text = "Increment " + msg.what
        }

        fun onDestroyHandler() {
            if (activity != null) {
                activity = null
            }
        }
    }
}
