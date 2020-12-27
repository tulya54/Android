package com.example.myapplication;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import io.reactivex.disposables.CompositeDisposable;
import okhttp3.Response;

public class BaseSubClass {

    public static final String TAG = "BaseSubClass.";
    private Context context;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<Response> response = new MutableLiveData<>();
    
    public BaseSubClass(Context context) {
        this.context = context;
    }

    private boolean isNet() {
        if (context == null)
            return false;
        try {
            return ((BaseActivity)context).isNet();
        } catch (Exception e) {
            ((BaseActivity)context).crash(TAG, "isNet()", e);
            return false;
        }
    }

    private void onLog(String msg) {
        if (context == null)
            return;
        try {
            ((BaseActivity)context).log(TAG, msg);
        } catch (Exception e) {
            ((BaseActivity)context).crash(TAG, "onLog()", e);
        }
    }

    public void openAuthActivity() {
        if (context == null) return;
        try {
            ((BaseActivity)context).launchActivity(context, MainActivity.class);
        } catch (Exception e) {
            ((BaseActivity)context).crash(TAG, "openAuthActivity()", e);
        }
    }

    public void onClearDisposable() {
        if (disposable != null)
            if (disposable.size() > 0) {
                disposable.clear();
                disposable = null;
            }
        if (response != null)
            response = null;
    }

    private void initLanguageAndToken() {
        if (context == null) return;
        try {
            String language = ((BaseActivity)context).getLanguageString();
            String token = ((BaseActivity)context).getToken();
        } catch (Exception e) {
            ((BaseActivity)context).crash(TAG, "initLanguageAndToken()", e);
        }
    }

    private void initLanguage() {
        if (context == null) return;
        try {
            String language = ((BaseActivity)context).getLanguageString();
        } catch (Exception e) {
            ((BaseActivity)context).crash(TAG, "initLanguageAndToken()", e);
        }
    }
}
