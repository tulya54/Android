package com.example.myapplication

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.play.core.review.ReviewInfo
import com.google.android.play.core.review.ReviewManager
import com.google.android.play.core.review.ReviewManagerFactory
import com.google.android.play.core.tasks.Task

class MainActivity : AppCompatActivity() {

    private var reviewManager: ReviewManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(View(this))
        /**/
        init()
    }

    private fun init() {
        reviewManager = ReviewManagerFactory.create(this)
        /*  Launch rating app  */
        showRateApp()
    }

    private fun showRateApp() {
        val request = reviewManager!!.requestReviewFlow()
        request.addOnCompleteListener { task: Task<ReviewInfo?> ->
            if (task.isSuccessful) {
                // We can get the ReviewInfo object
                val reviewInfo = task.result
                val flow =
                    reviewManager!!.launchReviewFlow(this, reviewInfo)
                flow.addOnCompleteListener { task1: Task<Void?>? -> }
            } else {
                // There was some problem, continue regardless of the result.
                // show native rate app dialog on error
                showRateAppFallbackDialog()
            }
        }
    }

     private fun showRateAppFallbackDialog() {
        MaterialAlertDialogBuilder(this)
            .setTitle("Rate us")
            .setMessage("Liked us? Please rate us on play store")
            .setPositiveButton("Okay") { dialog, which -> }
            .setNegativeButton("Not now") { dialog, which -> }
            .setNeutralButton("Cancel") { dialog, which -> }
            .setOnDismissListener(DialogInterface.OnDismissListener { dialog: DialogInterface? -> })
            .show()
    }
}

