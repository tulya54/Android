package kz.snation.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    private lateinit var tvText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //  Init Views
        initViews()
        //  Set size typed value dimension and send data to TextView
        onDimensionToString(18)
    }

    private fun initViews() {
        tvText = findViewById(R.id.tvText)
    }

    private fun onDimensionToString(size: Int) {
        var text = "DP: " + getDimensionDP(size).toString() + "\n"
        text += "PX: " + getDimensionPX(size).toString() + "\n"
        text += "SP: " + getDimensionSP(size).toString()

        tvText.text = text
    }

    private fun getDimensionDP(numb: Int) : Int {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, numb.toFloat(), getResources().getDisplayMetrics());
        return Math.round(px)
    }

    private fun getDimensionPX(numb: Int) : Int {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, numb.toFloat(), getResources().getDisplayMetrics());
        return Math.round(px)
    }

    private fun getDimensionSP(numb: Int) : Int {
        val px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, numb.toFloat(), getResources().getDisplayMetrics());
        return Math.round(px)
    }
}
