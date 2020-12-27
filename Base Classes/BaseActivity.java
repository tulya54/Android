package com.example.myapplication;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.ColorInt;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import java.util.Locale;
import timber.log.Timber;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

public class BaseActivity extends AppCompatActivity {
    /*  Constants  */
    private static final String TAG = "BaseActivity().";
    private static final String NETWORK_CHANGE_UI = "network.change.ui";
    private static final String ON_RESUME = "onResume().";
    private static final String ON_PAUSE = "onPause().";
    private static final String ON_DESTROY = "onDestroy().";
    private static final String PREFS_LANGUAGE = "prefs_language";
    private static final String LANGUAGE = "language";
    private static final String PREFS_TOKEN = "prefs_token";
    private static final String TOKEN = "token";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    /*  CONSOLE LOG  */
    public void log(String tag, String msg, @Nullable Throwable t) {
        onLog(tag, msg, t);
    }
    public void log(String tag, String msg) {
        onLog(tag, msg, null);
    }
    private void onLog(String tag, String msg, @Nullable Throwable throwable) {
        if (throwable == null)
            Timber.tag(tag).d(msg);
        else
            Timber.tag(tag).d(throwable, msg);
    }
    /*  FIREBASE CRASHLYTICS  */
    public void crash(@NonNull String tag, @NonNull String methodName, @NonNull Object data, @NonNull Throwable throwable) {
        onLogCrash(tag, methodName, data, throwable);
    }
    public void crash(@NonNull String tag, @NonNull String methodName, @NonNull Object data) {
        onLogCrash(tag, methodName, data, null);
    }
    public void crash(@NonNull String tag, @NonNull String methodName, @NonNull Throwable throwable) {
        onLogCrash(tag, methodName, null, throwable);
    }
    private void onLogCrash(@NonNull String tag, @NonNull String methodName, @Nullable Object data, @Nullable Throwable throwable) {
        String key = tag + methodName;
        FirebaseCrashlytics firebaseCrashlytics = FirebaseCrashlytics.getInstance();
        if (data != null)
            if (data instanceof String)
                firebaseCrashlytics.setCustomKey(key, (String) data);
            else if (data instanceof Boolean)
                firebaseCrashlytics.setCustomKey(key, (boolean) data);
            else if (data instanceof Integer)
                firebaseCrashlytics.setCustomKey(key, (int) data);
            else if (data instanceof Long)
                firebaseCrashlytics.setCustomKey(key, (long) data);
            else if (data instanceof Float)
                firebaseCrashlytics.setCustomKey(key, (float) data);
            else if (data instanceof Double)
                firebaseCrashlytics.setCustomKey(key, (double) data);
        if (throwable != null)
            firebaseCrashlytics.recordException(throwable);
    }
    /*  INTERNET  */
    public boolean isNet() {
        try {
            boolean result = false;
            ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (connectivityManager != null) {
                    NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                    if (networkCapabilities != null)
                        if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))
                            result = true;
                        else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI))
                            result = true;
                        else if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET))
                            result = true;
                }
            } else
            if (connectivityManager != null) {
                NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
                if (activeNetwork != null && activeNetwork.isConnected())
                    result = true;
            }
            return result;
        } catch (Exception e) {
            crash(TAG, "isNet()", e);
            return false;
        }
    }
    /*  LANGUAGE  */
    public Resources getLanguageResources() {
        Locale locale = new Locale(getLanguageString());
        Locale.setDefault(locale);
        Resources resources = getResources();
        Configuration config = new Configuration(resources.getConfiguration());
        config.locale = locale;
        resources.updateConfiguration(config, resources.getDisplayMetrics());
        return resources;

    }
    public void setLanguageResources(String language) {
        setLanguageString(language);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Configuration configuration = getResources().getConfiguration();
            configuration.setLocale(locale);
            configuration.setLayoutDirection(locale);
            createConfigurationContext(configuration);
        } else {
            Locale locale = new Locale(language);
            Locale.setDefault(locale);
            Resources resources = getResources();
            Configuration configuration = resources.getConfiguration();
            configuration.locale = locale;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1)
                configuration.setLayoutDirection(locale);
            resources.updateConfiguration(configuration, resources.getDisplayMetrics());
        }
    }
    public String getLanguageString() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_LANGUAGE, Context.MODE_PRIVATE);
        String language = sharedPreferences.getString(LANGUAGE, "kk");
        return language != null ? language : "kk";

    }
    public void setLanguageString(String language) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_LANGUAGE, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(LANGUAGE, language).apply();
    }
    /*  MODAL WINDOW  */
    public void showToast(@NonNull Object msg) {
        onShowToast(msg, false);
    }
    public void showToast(@NonNull Object msg, boolean isToastLength) {
        onShowToast(msg, isToastLength);
    }
    private void onShowToast(@NonNull Object msg, boolean isToastLength) {
        try {
            int length = isToastLength ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT;
            if (msg instanceof String)
                Toast.makeText(this, (String) msg , length).show();
            else if (msg instanceof Integer)
                Toast.makeText(this, (Integer) msg , length).show();
        } catch (Exception e) {
            crash(TAG, "showToast()", e);
        }
    }
    /*  RESOURCES  */
    @ColorInt
    public int getRColor(int resources) {
        return ContextCompat.getColor(this, resources);
    }
    @Nullable
    public android.graphics.drawable.Drawable getRDrawable(int resources) {
        return ContextCompat.getDrawable(this, resources);
    }
    @NonNull
    public String getRStr(@StringRes int str) {
        return getLanguageResources().getString(str);
    }
    @NonNull
    public String concatStr(String str) {
        return str;
    }
    /*  STORAGE  */
    public void savePrefs(@NonNull String prefs, @NonNull String key, @NonNull Object value) {
        SharedPreferences sharedPreferences = getSharedPreferences(prefs, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (value instanceof String)
            editor.putString(key, (String) value);
        else if (value instanceof Boolean)
            editor.putBoolean(key, (Boolean) value);
        else if (value instanceof Integer)
            editor.putInt(key, (Integer) value);
        else if (value instanceof Long)
            editor.putLong(key, (Long) value);
        else if (value instanceof Float)
            editor.putFloat(key, (Float) value);
        else
            return;
        editor.apply();
        editor.commit();

    }
    public Object loadPrefs(@NonNull String prefs, @NonNull String key, @Nullable Object defValue) {
        if (defValue instanceof String)
            return getSharedPreferences(prefs, Context.MODE_PRIVATE).getString(key, (String) defValue);
        else if (defValue instanceof Boolean)
            return getSharedPreferences(prefs, Context.MODE_PRIVATE).getBoolean(key, (Boolean) defValue);
        else if (defValue instanceof Integer)
            return getSharedPreferences(prefs, Context.MODE_PRIVATE).getInt(key, (Integer) defValue);
        else if (defValue instanceof Long)
            return getSharedPreferences(prefs, Context.MODE_PRIVATE).getLong(key, (Long) defValue);
        else if (defValue instanceof Float)
            return getSharedPreferences(prefs, Context.MODE_PRIVATE).getFloat(key, (Float) defValue);
        else
            return null;
    }
    public void clearPrefs(String prefs) {
        getSharedPreferences(prefs, Context.MODE_PRIVATE).edit().clear().apply();
    }
    /*  TOKEN  */
    public String getToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_TOKEN, Context.MODE_PRIVATE);
        String token = sharedPreferences.getString(TOKEN, null);
        return token != null ? token : "";
    }
    public void setToken(String token) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_TOKEN, Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(TOKEN, token).apply();
    }
    public void removeToken() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS_TOKEN, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    /*  KEYBOARD  */
    public void showKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        InputMethodManager methodManager = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        assert methodManager != null && view != null;
        methodManager.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
    public void showKeyboard(Activity activity, EditText editText) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        editText.requestFocus();
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
    public void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null)
            view = new View(activity);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService( Context.INPUT_METHOD_SERVICE );
        View f = getCurrentFocus();
        if( null != f && null != f.getWindowToken() && EditText.class.isAssignableFrom( f.getClass() ) )
            imm.hideSoftInputFromWindow( f.getWindowToken(), 0 );
        else
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
    /*  Fragment Manager  */
    public Fragment getCurrentFragment(int id) {
        return getSupportFragmentManager().findFragmentById(id);
    }
    public void addFragment(int layout, Fragment fragment, boolean isBackStack, String name) {
        onAddFragment(layout, fragment, isBackStack, name);
    }
    public void addFragment(int layout, Fragment fragment, boolean isBackStack) {
        onAddFragment(layout, fragment, isBackStack, null);
    }
    public void addFragment(int layout, Fragment fragment) {
        onAddFragment(layout, fragment, false, null);
    }
    private void onAddFragment(int layout, Fragment fragment, boolean isBackStack, @Nullable String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isBackStack)
            fragmentTransaction.addToBackStack(name);
        fragmentTransaction.add(layout, fragment);
        fragmentTransaction.commit();
    }
    public void replaceFragment(int layout, Fragment fragment, boolean isBackStack, String name) {
        onReplaceFragment(layout, fragment, isBackStack, name);
    }
    public void replaceFragment(int layout, Fragment fragment, boolean isBackStack) {
        onReplaceFragment(layout, fragment, isBackStack, null);
    }
    public void replaceFragment(int layout, Fragment fragment) {
        onReplaceFragment(layout, fragment, false, null);
    }
    private void onReplaceFragment(int layout, Fragment fragment, boolean isBackStack, @Nullable String name) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (isBackStack)
            fragmentTransaction.addToBackStack(name);
        fragmentTransaction.replace(layout, fragment);
        fragmentTransaction.commit();
    }
    /*  Start Activity  */
    public void launchActivity(Context packageContext, Class<?> cls) {
        onLaunchActivity(packageContext, cls, false, null, null);
    }
    public void launchActivity(Context packageContext, Class<?> cls, boolean isFinish) {
        onLaunchActivity(packageContext, cls, isFinish, null, null);
    }
    public void launchActivity(Context packageContext, Class<?> cls, boolean isFinish, @Nullable Object key, @Nullable Object value) {
        onLaunchActivity(packageContext, cls, isFinish, key, value);
    }
    public void launchActivity(Context packageContext, Class<?> cls, @Nullable Object key, @Nullable Object value) {
        onLaunchActivity(packageContext, cls, false, key, value);
    }
    private void onLaunchActivity(Context packageContext, Class<?> cls, boolean isFinish, @Nullable Object key, @Nullable Object value) {
        Intent intent = new Intent(packageContext, cls);
        if (key != null && value != null)
            if (key instanceof String[] && value instanceof String[]) {
                String[] k = (String[]) key;
                String[] v = (String[]) value;
                if (k.length > 0 && k.length == v.length)
                    for (int i = 0; i < k.length; i++)
                        intent.putExtra(k[i], v[i]);
            } else if (key instanceof String && value instanceof String)
                intent.putExtra((String) key, (String) value);
        startActivity(intent);
        if (isFinish)
            ((AppCompatActivity)packageContext).finish();
    }
    /*  Activity Lifecycle  */
    @Override
    protected void onResume() {
        super.onResume();
        onRegisterConnectivityAction(ON_RESUME);
        onRegisterNetworkChangeAction(ON_RESUME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        onRegisterConnectivityAction(ON_PAUSE);
        onRegisterNetworkChangeAction(ON_PAUSE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        onUnregisterConnectivityAction(ON_DESTROY);
        onUnregisterNetworkChangeAction(ON_DESTROY);
    }

    private void onRegisterConnectivityAction(String lifecycle) {
        try {
            registerReceiver(connectivityActionReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));
        } catch (Exception e) {
            crash(TAG + lifecycle, "onRegisterConnectivityAction()", e);
        }
    }

    private void onRegisterNetworkChangeAction(String lifecycle) {
        try {
            registerReceiver(networkChangeActionReceiver, new IntentFilter(NETWORK_CHANGE_UI));
        } catch (Exception e) {
            crash(TAG + lifecycle,  "onRegisterNetworkChangeAction()", e);
        }
    }

    private void onUnregisterConnectivityAction(String lifecycle) {
        try {
            unregisterReceiver(connectivityActionReceiver);
        } catch (Exception e) {
            crash(TAG + lifecycle, "onUnregisterConnectivityAction()", e);
        }
    }

    private void onUnregisterNetworkChangeAction(String lifecycle) {
        try {
            unregisterReceiver(networkChangeActionReceiver);
        } catch (Exception e) {
            crash(TAG + lifecycle, "onUnregisterConnectivityAction()", e);
        }
    }

    private final BroadcastReceiver networkChangeActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(NETWORK_CHANGE_UI)) {
                if (isNet()) {
                    onNetworkStatus(intent.getExtras().getString("status").equals("1"));
                } else
                    onNetworkStatus(false);
            }
        }
    };

    private final BroadcastReceiver connectivityActionReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            boolean noConnect = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY, false);
            Intent intent1 = new Intent(NETWORK_CHANGE_UI);
            intent1.putExtra("status", !noConnect ? "1" : "0");
            context.sendBroadcast(intent1);
        }
    };
    public void onNetworkStatus(boolean isInternet) {

    }
}
