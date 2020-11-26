package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        /**/
        init();
    }

    private void init() {
        //  A) drawables with theme attributes
        ContextCompat.getDrawable(this, android.R.drawable.btn_star);
        //  B) drawables without theme attributes
        ResourcesCompat.getDrawable(getResources(), android.R.drawable.btn_star, null);
        //  C) drawables with theme attributes from another theme
        ResourcesCompat.getDrawable(getResources(), android.R.drawable.btn_star, getTheme());
    }
}
