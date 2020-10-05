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
        final String uniqueWord = "";
        /*  THIS YOUR APPLICATION ID  */
        String thisPackageName = "com.example.myapplication";
        /*  Link from getting APK info  */
        final String URL = "https://play.google.com/store/apps/details?id=" + thisPackageName + "&hl=en";
        /*  Launch background trhead  */
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    /*  Get package info for @versionName */
                    String currentVersion = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
                    /*  Get versionName in Google Play Store  */
                    String newVersion = org.jsoup.Jsoup.connect(URL)
                            .timeout(30000)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                            .get()
                            .select("div.hAyfc:nth-child(4) > span:nth-child(2) > div:nth-child(1) > span:nth-child(1)")
                            .first()
                            .ownText();
                    ModelVersionCode currentVersionCode = getVersionCode(currentVersion);
                    ModelVersionCode newVersionVersionCode = getVersionCode(newVersion);
                    /*  Equal current and new version  */
                    if (currentVersionCode.getVersionCode() < newVersionVersionCode.getVersionCode()) {
                        if (newVersionVersionCode.getResult().equals(uniqueWord)) {
                            Log.d("TAG", "Your code to main thread");
                        } else {
                            Log.d("TAG", "Your code to main thread");
                        }
                        Log.d("TAG", "Your code to main thread");
                    } else {
                        Log.d("TAG", "Your code to main thread");
                    }
                    Log.d("TAG", "result: ");
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    private ModelVersionCode getVersionCode(String version) {
        boolean isNineDigitNumber = false;
        String[] versionNames = version.split("\\.");
        int size = versionNames.length;
        if (size == 3) {
            boolean[] isNumeric = new boolean[3];
            String[] names = {"major", "minor", "patch"};
            for (int i = 0; i < 3; i++) {
                isNumeric[i] = isNumericArray(versionNames[i]);
            }
            for (int i = 0; i < 2; i++) {
                if (!isNumeric[i]) {
                    try {
                        throw new IllegalArgumentException("Semantic Versioning: " + names[i] + "it's not number");
                    } catch (Exception e) { e.printStackTrace(); }
                }
            }
            if (isNumeric[0] && isNumeric[1]) {
                long major = Long.parseLong(versionNames[0]);
                long minor = Long.parseLong(versionNames[1]);
                long patch = 0;
                String result = "";

                if (isNumeric[2]) {
                    patch = Long.parseLong(versionNames[2]);
                    result = "empty";
                } else {
                    String[] patchWord = versionNames[2].split("-");
                    if (patchWord.length == 2) {
                        patch = Long.parseLong(patchWord[0]);
                        result = patchWord[1];
                    }
                }
                if (isNineDigitNumber) {
                    java.text.DecimalFormat decimalFormat = null;
                    try {
                        decimalFormat = new java.text.DecimalFormat("00");
                        String nineDigitNumber = major + decimalFormat.format(versionNames[1].length()) + decimalFormat.format(String.valueOf(patch).length());
                        long versionCode = Long.parseLong(nineDigitNumber);
                        Log.d("TAG", "versionCode: " + String.valueOf(versionCode) + ", result: " + String.valueOf(result));
                        return new ModelVersionCode(versionCode, result);
                    } catch (NullPointerException | NumberFormatException e) {
                        e.printStackTrace();
                    }
                } else {
                    long versionCode = major * 10000 +  minor * 100 + patch;
                    Log.d("TAG", "versionCode: " + String.valueOf(versionCode) + ", result: " + String.valueOf(result));
                    return new ModelVersionCode(versionCode, result);
                }
            }
        } else {
            try {
                throw new IllegalArgumentException("VersionNames size dots not two");
            } catch (Exception e) { e.printStackTrace(); }
        }
        Log.d("TAG", "versionCode: 0, result: empty");
        return new ModelVersionCode(0, "empty");
    }

    private boolean isNumericArray(String str) {
        if (str == null)
            return false;
        for (char c : str.toCharArray())
            if (c < '0' || c > '9')
                return false;
        return true;
    }
}
