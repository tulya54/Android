package com.example.myapplication;

import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Init Views
        initViews();
        //  Set size typed value dimension and send data to TextView
        onDimensionToString(18);
    }

    private void initViews() {
        tvText = findViewById(R.id.tvText);
    }

    private void onDimensionToString(int size) {
        String text = "DP: " + String.valueOf(getDimensionDP(size)) + "\n"
                + "PX: " + String.valueOf(getDimensionPX(size)) + "\n"
                + "SP: " + String.valueOf(getDimensionSP(size));
        tvText.setText(text);
    }

    private int getDimensionDP(int numb) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, numb, getResources().getDisplayMetrics());
        return Math.round(px);
    }

    private int getDimensionPX(int numb) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, numb, getResources().getDisplayMetrics());
        return Math.round(px);
    }

    private int getDimensionSP(int numb) {
        float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, numb, getResources().getDisplayMetrics());
        return Math.round(px);
    }
}
