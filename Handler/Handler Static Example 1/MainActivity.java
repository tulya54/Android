package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    private TextView tvText;
    private static final String TAG = "TAG";
    private CustomHandler customHandler;
    private int range = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //  Init Views
        initViews();
        //  Init Handler
        initHandler();
        //  Use
        onStartHandler();
    }

    private void initViews() {
        tvText = findViewById(R.id.tvText);
    }

    private void initHandler() {
        customHandler = new CustomHandler(this);
    }

    private void onStartHandler() {
        Thread t = new Thread(new Runnable() {
            public void run() {
                //  start iterate = 5
                for (int i = 0; i < range; i++) {
                    try {
                        Thread.sleep(1000);
                        customHandler.sendEmptyMessage(i +1);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }
    
      @Override
    protected void onDestroy() {
        super.onDestroy();
        if (customHandler != null) {
            customHandler = null;
        }
    }

    /*  Inner Static Class  */
    private static class CustomHandler extends android.os.Handler {

        private final java.lang.ref.WeakReference activity;

        CustomHandler(MainActivity activity) {
            this.activity = new java.lang.ref.WeakReference<>(activity);
        }

        @Override
        public void handleMessage(@NotNull android.os.Message msg) {
            Object o =  this.activity.get();
            MainActivity activity = (MainActivity) o;
            if (activity == null) {
                return;
            }
            Log.e(TAG, "Increment " + String.valueOf(msg.what));
            activity.tvText.setText("Increment " + String.valueOf(msg.what));
            //  Update UI

        }
    }
}
