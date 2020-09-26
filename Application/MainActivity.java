package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import org.jsoup.Jsoup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(new View(this));
        /*  Check app version in Google play store */
        onCheckAppVersion();
    }

    private void onCheckAppVersion() {
        String currentVersion = "";
        /*  THIS YOUR APPLICATION ID  */
        final String thisPackageName = "com.example.myapplication";
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    String newVersion = Jsoup.connect("https://play.google.com/store/apps/details?id=" + thisPackageName + "&hl=en")
                            .timeout(30000)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .get()
                            .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                            .first()
                            .ownText();
                    if (currentVersion.equals(newVersion)) {
                        /*  YOUR CODE  */
                    } else {
                        /*  YOUR CODE  */
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
