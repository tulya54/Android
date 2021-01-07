package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "TAG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initializing a new HashMap
        HashMap<String, String> colors = new HashMap<>();
        // Put some key value pairs to HashMap
        colors.put("Red", "#FF0000");
        colors.put("Green", "#008000");
        colors.put("Blue", "#0000FF");

        // Iterate through the HashMap
        Iterator itr = colors.entrySet().iterator();
        Log.d(TAG, "Iterate over a HashMap");
        while (itr.hasNext()){
            Map.Entry pair = (Map.Entry) itr.next();
            Log.d(TAG, "\n" + pair.getKey() + " : " + pair.getValue());
            //iterator.remove();
        }

        // Another way to iterate through the HashMap
        Log.d(TAG, "\n\nAnother way to iterate over a HashMap");
        for (Map.Entry<String,String > entry : colors.entrySet()){
            Log.d(TAG, "\n" + entry.getKey() + " : " + entry.getValue());
        }

        // Another way to iterate over HashMap keys and get values also
        Log.d(TAG, "\n\nAnother way to iterate over a HashMap");
        for(String key : colors.keySet()){
            Log.d(TAG, "\n" + key + " : " + colors.get(key));
        }

        // Iterate over HashMap keys only
        Log.d(TAG, "\n\nIterate over HashMap keys only");
        for (String key : colors.keySet()){
            Log.d(TAG, "\n" + key);
        }

        // Iterate over HashMap values only
        Log.d(TAG, "\n\nIterate over HashMap values only");
        for (String value : colors.values()){
            Log.d(TAG, "\n" + value);
        }
    }
}
