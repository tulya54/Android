package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import android.graphics.drawable.Drawable;
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
        //  Get color for TextView
        int textColor = ContextCompat.getColor(this, android.R.color.white);
        //  Get color background for Views
        Drawable backgroundColorNoTheme = ResourcesCompat.getDrawable(getResources(), android.R.color.white, null);
        //  Get color background for Views
        Drawable backgroundColorWithTheme = ResourcesCompat.getDrawable(getResources(), android.R.color.white, getTheme());
        //  A) drawables with theme attributes
        Drawable drawable = ContextCompat.getDrawable(this, android.R.drawable.btn_star);
        //  B) drawables without theme attributes
        Drawable drawableNoTheme = ResourcesCompat.getDrawable(getResources(), android.R.drawable.btn_star, null);
        //  C) drawables with theme attributes from another theme
        Drawable drawableWithTheme = ResourcesCompat.getDrawable(getResources(), android.R.drawable.btn_star, getTheme());
    }
}
