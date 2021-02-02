package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import android.os.Bundle;
import android.view.View;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        //
        init();
    }

    private void init() {
        FragmentManager fm = getSupportFragmentManager();
        List<Fragment> fragments = fm.getFragments();
        for (Fragment fragment: fragments) {
            if (fragment instanceof FirstFragment) {
                //  Your example code
                ((FirstFragment)fragment).onBegin();
            }
        }
        //  Or get by index
        Fragment lastFragment = fragments.get(fragments.size() - 1);
    }
}
