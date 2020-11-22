package kz.tech.esparta.childs.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.content.ContextCompat;

import kz.tech.esparta.root.service.CheckLaunchService;

public class AlarmReceiver extends BroadcastReceiver {
    public static final String CUSTOM_INTENT = "com.test.intent.action.ALARM";
    @Override
    public void onReceive(final Context context, Intent intent) {
        MyJobIntentService.enqueueWork(context, intent);
    }
    public static void cancelAlarm(Context ctx) {
        AlarmManager alarms = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);

        /* cancel any pending alarm */
        alarms.cancel(getPendingIntent(ctx));
    }
    public static void setAlarm(Context ctx, boolean force) {
        cancelAlarm(ctx);
        AlarmManager alarms = (AlarmManager) ctx.getSystemService(Context.ALARM_SERVICE);
        // EVERY X MINUTES
        int X = 5;
        long delay = (1000 * 60 * X);
        long when = System.currentTimeMillis();
        if (!force) {
            when += delay;
        }

        /* fire the broadcast */
       // alarms.set(AlarmManager.RTC_WAKEUP, when, getPendingIntent(ctx));
        int SDK_INT = Build.VERSION.SDK_INT;
        if (SDK_INT < Build.VERSION_CODES.KITKAT)
            alarms.set(AlarmManager.RTC_WAKEUP, when, getPendingIntent(ctx));
        else if (Build.VERSION_CODES.KITKAT <= SDK_INT && SDK_INT < Build.VERSION_CODES.M)
            alarms.setExact(AlarmManager.RTC_WAKEUP, when, getPendingIntent(ctx));
        else if (SDK_INT >= Build.VERSION_CODES.M) {
            alarms.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, when, getPendingIntent(ctx));
        }
    }
    private static PendingIntent getPendingIntent(Context ctx) {
       // Context ctx = null;   /* get the application context */
        Intent alarmIntent = new Intent(ctx, AlarmReceiver.class);
        alarmIntent.setAction(CUSTOM_INTENT);

        return PendingIntent.getBroadcast(ctx, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT);
    }
}



package kz.tech.esparta.childs.service;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.PowerManager;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import kz.tech.esparta.R;
import kz.tech.esparta.root.MainActivity;
import kz.tech.esparta.root.firebase.FireBaseAutoCharge;
import kz.tech.esparta.root.firebase.FireBaseChildrenTime;

public class ChildIntentService extends IntentService {
    public static final String CHANNEL_ID = "ForegroundServiceChannel2";
    private static final String PREFS_STORAGE_LOGIN = "PREFS_STORAGE_LOGIN";
    private static final String PREFS_STORAGE_ACCOUNT = "PREFS_STORAGE_ACCOUNT";
    private static final String PREFS_STORAGE_AUTO_CHARGE = "PREFS_STORAGE_AUTO_CHARGE";
    private static final String PREFS_STORAGE_CHILDREN_TIME = "PREFS_STORAGE_CHILDREN_TIME";
    private PowerManager.WakeLock wakeLock;
    private String idLogin;
    private String idAccount;
    public ChildIntentService() {
        super("MyChildIntentService");
    }
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }
    @Override
    public void onCreate() {
        super.onCreate();
        android.util.Log.e("TAG ", "IntentService: START");
        idLogin = onLoadLoginID();
        String[] strings = onLoadAccountID();
        idAccount = strings[4];

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "eSpartaApp:Wakelock");
        wakeLock.acquire();


    }
    @Override
    protected void onHandleIntent(Intent intent) {
        String inputService = intent.getStringExtra("inputService");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(inputService)
                .setContentText(inputService)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        if (inputService != null) {
            switch (inputService) {
                case "autoCharge": onAutoCharge(); break;
                case "timeChildren": onChildrenTime(); break;
            }
        }
    }
    private void onAutoCharge() {
        DatabaseReference dbrAutoCharge = new FireBaseAutoCharge().getAutoCharge();//  AutoCharge
        dbrAutoCharge.child(idLogin).child(idAccount).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Iterator i = dataSnapshot.getChildren().iterator();
                    while (i.hasNext()) {
                        String monday = (String) ((DataSnapshot) i.next()).getValue();
                        String tuesday = (String) ((DataSnapshot) i.next()).getValue();
                        String wednesday = (String) ((DataSnapshot) i.next()).getValue();
                        String thursday = (String) ((DataSnapshot) i.next()).getValue();
                        String friday = (String) ((DataSnapshot) i.next()).getValue();
                        String saturday = (String) ((DataSnapshot) i.next()).getValue();
                        String sunday = (String) ((DataSnapshot) i.next()).getValue();
                        String timeAutoCharge = (String) ((DataSnapshot) i.next()).getValue();
                        String isSwitch = (String) ((DataSnapshot) i.next()).getValue();
                        onSavePrefsAutoCharge(monday, tuesday, wednesday, thursday, friday, saturday,
                                sunday, timeAutoCharge, isSwitch);
                        android.util.Log.e("AutoCharge Tag", " Good load and save prefs");
                    }
                    android.util.Log.e("AutoCharge Tag", " Exist");
                } else {
                    android.util.Log.e("AutoCharge Tag", " Not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                android.util.Log.e("AutoCharge Tag", " Error dbr");
            }
        });
    }
    private void onChildrenTime() {
        DatabaseReference dbrChildrenTime = new FireBaseChildrenTime().getChildrenTime();//  ChildrenTime
        dbrChildrenTime.child(idLogin).child(idAccount).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Iterator i = dataSnapshot.getChildren().iterator();
                    while (i.hasNext()) {
                        String monday = (String) ((DataSnapshot) i.next()).getValue();
                        String tuesday = (String) ((DataSnapshot) i.next()).getValue();
                        String wednesday = (String) ((DataSnapshot) i.next()).getValue();
                        String thursday = (String) ((DataSnapshot) i.next()).getValue();
                        String friday = (String) ((DataSnapshot) i.next()).getValue();
                        String saturday = (String) ((DataSnapshot) i.next()).getValue();
                        String sunday = (String) ((DataSnapshot) i.next()).getValue();
                        String isSwitch = (String) ((DataSnapshot) i.next()).getValue();
                        onSavePrefsChildrenTime(monday, tuesday, wednesday, thursday, friday, saturday, sunday,  isSwitch);
                        android.util.Log.e("ChildrenTime Tag", " Good load and save prefs");
                    }
                    android.util.Log.e("ChildrenTime Tag", " Exist");
                } else {
                    android.util.Log.e("ChildrenTime Tag", " Not exist");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                android.util.Log.e("ChildrenTime Tag", " Error dbr");
            }
        });
    }
    private String onLoadLoginID() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_STORAGE_LOGIN, 0);
        return settings.getString("id", "");
    }
    private String[] onLoadAccountID() {
        String[] strings = new String[5];
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_STORAGE_ACCOUNT, 0);
        strings[0] = settings.getString("avatar", "");
        strings[1] = settings.getString("name", "");
        strings[2] = settings.getString("pin", "");
        strings[3] = settings.getString("status", "");
        strings[4] = settings.getString("id", "");
        return strings;
    }
    private void onSavePrefsAutoCharge(String monday, String tuesday, String wednesday, String thursday, String friday,
                                 String saturday, String sunday, String timeAutoCharge, String isSwitch) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_STORAGE_AUTO_CHARGE, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("monday", monday);
        editor.putString("tuesday", tuesday);
        editor.putString("wednesday", wednesday);
        editor.putString("thursday", thursday);
        editor.putString("friday", friday);
        editor.putString("saturday", saturday);
        editor.putString("sunday", sunday);
        editor.putString("timeAutoCharge", timeAutoCharge);
        editor.putString("isSwitch", isSwitch);
        editor.apply();
    }
    private void onSavePrefsChildrenTime(String monday, String tuesday, String wednesday, String thursday,
                                         String friday, String saturday, String sunday, String isSwitch) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_STORAGE_CHILDREN_TIME, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("monday", monday);
        editor.putString("tuesday", tuesday);
        editor.putString("wednesday", wednesday);
        editor.putString("thursday", thursday);
        editor.putString("friday", friday);
        editor.putString("saturday", saturday);
        editor.putString("sunday", sunday);
        editor.putString("isSwitch", isSwitch);
        editor.apply();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        android.util.Log.e("TAG ", "IntentService: DESTROY");
        wakeLock.release();
        android.util.Log.e("TAG ", "WakeLock: Release");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        }
    }
}





package kz.tech.esparta.childs.service;


import android.app.ActivityManager;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.app.Service;
import android.app.usage.UsageEvents;
import android.app.usage.UsageStatsManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.IBinder;
import android.os.PowerManager;
import android.text.TextUtils;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;


import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.schedulers.Timed;
import kz.tech.esparta.R;
import kz.tech.esparta.block_page.LockScreenActivity;
import kz.tech.esparta.root.MainActivity;
import kz.tech.esparta.root.firebase.FireBaseChildData;
import kz.tech.esparta.root.service.CheckLaunchService;


public class ChildService extends Service {
    public static final String CHANNEL_ID = "ForegroundServiceChannel1";
    private static final String PREFS_STORAGE_LOGIN = "PREFS_STORAGE_LOGIN";
    private static final String PREFS_STORAGE_FAMILY_ACCOUNT = "PREFS_STORAGE_FAMILY_ACCOUNT";





    private static final String PREFS_STORAGE_AUTO_CHARGE = "PREFS_STORAGE_AUTO_CHARGE";
    private static final String PREFS_STORAGE_CHILDREN_TIME = "PREFS_STORAGE_CHILDREN_TIME";
    private static final String PREFS_CHILD_COINS = "PREFS_CHILD_COINS";
    private static final String PREFS_APP_LOCK_LIST_SELECT = "PREFS_APP_LOCK_LIST_SELECT";
    private PowerManager.WakeLock wakeLock;
    private String idLogin;
    private String idAccount;
    private String statusAccount;

    private ActivityManager activityManager;
    List<ActivityManager.RunningAppProcessInfo> pis= null;

    private Disposable disposable;
    private CountDownTimer countDownTimer = null;
    private boolean isCountTimer = false;
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }
    int minute = 0;
    long totalCoins = 0;
    long pizdec = 0;

    @Override
    public void onCreate() {
        super.onCreate();



        android.util.Log.e("TAG ", "Service: START");
        idLogin = onLoadLoginID();
        String[] strings = onLoadAccountID();
        statusAccount = strings[3];
        idAccount = strings[4];

        PowerManager powerManager = (PowerManager) getSystemService(POWER_SERVICE);
        wakeLock = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "eSpartaApp:Wakelock");
        wakeLock.acquire();

        activityManager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        //onRequestTotalCoins();
        onLockAppsTimerCoins();

    }
    //////////////////////             TIMER              //////////////////////////
    private void startCountDownTimer() {
        long countDownTimerCoins = totalCoins * 60 * 1000;
        countDownTimer = new CountDownTimer(countDownTimerCoins, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                minute++;
                if (minute == 60) {
                    if (totalCoins > 0) {
                        totalCoins--;
                    }

                    saveCoins(totalCoins);
                    onRequestCoinsChange();
                    minute = 0;
                } else if (minute > 50 && totalCoins == 1){
                    totalCoins = 0;

                    saveCoins(totalCoins);
                    onRequestCoinsChange();
                    minute = 0;

                    onLockAppsTimerCoins();
                    if (countDownTimer != null) {
                        stopCountDownTimer();
                    }
                }
                int currentSecond = (int) millisUntilFinished / 1000;
                if (60 == currentSecond) {//  1 minute(60 second) left
                    onPushTime("Obolas end", "1 minute left");
                }
                if (totalCoins == 20) {//  20 minutes left
                    onPushTime("Obolas end", "20 minute left");
                }
            }
            @Override
            public void onFinish() {
                if (isCountTimer)
                    stopCountDownTimer();
            }
        };
        countDownTimer.start();
        isCountTimer = true;

        if (totalCoins == 0) {
            onLockAppsTimerCoins();
        }
    }
    private void stopCountDownTimer() {
        if (countDownTimer != null) {
            countDownTimer.cancel();
            countDownTimer = null;
        }
        isCountTimer = false;
    }
    /////////////////////           NOTIFICATION            ///////////////////////////
    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel serviceChannel = new NotificationChannel(
                    CHANNEL_ID,
                    "Foreground Service Channel",
                    NotificationManager.IMPORTANCE_DEFAULT
            );

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(serviceChannel);
        }
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String input = intent.getStringExtra("inputExtra");
        createNotificationChannel();
        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle(input)
                .setContentText(input)
                .setSmallIcon(R.drawable.ic_android)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);
        //do heavy work on a background thread
        //stopSelf();
        return START_STICKY;
    }
    private void onRequestTotalCoins() {
        DatabaseReference dbrChildData = new FireBaseChildData().getChildDataFB();//  ChildData
        dbrChildData.child(idLogin).child(idAccount).child("coins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = (String) dataSnapshot.getValue();
                    totalCoins = Integer.parseInt(value);
                    saveCoins(totalCoins);
                    if (countDownTimer == null) {
                        startCountDownTimer();
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    public void onRequestCoinsChange() {
        DatabaseReference dbrChildData = new FireBaseChildData().getChildDataFB();//  ChildData
        Query query = dbrChildData.child(idLogin).child(idAccount).child("coins");
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String totalCoinsDB = (String) dataSnapshot.getValue();
                int numb = Integer.parseInt(totalCoinsDB);
                numb -= 1;
                String result = String.valueOf(numb);
                dataSnapshot.getRef().setValue(result);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {}
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    private void onPushTime(String title, String text) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentTitle(title)
                        .setContentText(text);
        Notification notification = builder.build();
        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);
    }
    //////////////////////         LOCK APPS         ////////////////////////////////
    private void onLockAppsTimerCoins() {
        disposable = Observable.interval(1000L, TimeUnit.MILLISECONDS)
                .timeInterval()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Timed<Long>>() {
                    @Override
                    public void accept(@NonNull Timed<Long> longTimed) throws Exception {

         //               android.util.Log.e("TAG ", "Second " + pizdec );
          //              pizdec++;

                        if (statusAccount != null) {
                            if (statusAccount.equals("Son") || statusAccount.equals("Daughter")) {
                                if (!onChildrenTime()) {
                                    lockApps();
                                }
                            }
                        }
                    }
                });
    }
    public String getRecentApps(Context context) {
        String topPackageName = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) context.getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            UsageEvents usageEvents = mUsageStatsManager.queryEvents(time - 1000 * 30, System.currentTimeMillis() + (10 * 1000));
            UsageEvents.Event event = new UsageEvents.Event();
            while (usageEvents.hasNextEvent()) {
                usageEvents.getNextEvent(event);
            }
            if (event != null && !TextUtils.isEmpty(event.getPackageName()) && event.getEventType() == UsageEvents.Event.MOVE_TO_FOREGROUND) {
                if (AndroidUtils.isRecentActivity(event.getClassName())) {
                    return event.getClassName();
                }
                return event.getPackageName();
            } else {
                topPackageName = "";
            }
        } else {
            ActivityManager am = (ActivityManager) context.getSystemService(context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfo = am.getRunningTasks(1);
            ComponentName componentInfo = null;
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                componentInfo = taskInfo.get(0).topActivity;
            }
            if (AndroidUtils.isRecentActivity(componentInfo.getClassName())) {
                return componentInfo.getClassName();
            }
            topPackageName = componentInfo.getPackageName();
        }
        return topPackageName;
    }
    private void lockApps() {
        ArrayList<String> servicePackageList = null;
        String selectAppLockList = onLoadAppLockList();
        switch (selectAppLockList) {
            case "standardList": servicePackageList = onStandardList(); break;
            case "customizableList": servicePackageList = onCustomizableList(); break;
        }
        if (servicePackageList != null) {
            String selectPackage = getRecentApps(getApplicationContext());
            for (int m = 0; m < servicePackageList.size(); m++) {
                if (servicePackageList.get(m).equals(selectPackage)) {
                    ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                    //  List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
                    if (am != null) {
                        //  am.killBackgroundProcesses(getRecentApps(getApplicationContext()));
                    }
                    //  android.os.Process.killProcess(android.os.Process.myPid());
                    Intent startMain = new Intent(Intent.ACTION_MAIN);
                    startMain.addCategory(Intent.CATEGORY_HOME);
                    startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    getApplicationContext().startActivity(startMain);

                    Intent i = new Intent(getApplicationContext(), LockScreenActivity.class);
                    i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    i.putExtra("package_name", selectPackage);
                    getApplicationContext().startActivity(i);
                    //  startServiceByAlarm(getApplicationContext());
                    return;
                }
            }
        }
    }
    ////////////       STANDARD         ///////////////
    private ArrayList<String> onStandardList() {
        PackageManager packageManager = getApplicationContext().getPackageManager();
        List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        ArrayList<String> servicePackageList = new ArrayList<String>();
        for(PackageInfo packageInfo : packageList) {
            boolean b = isExceptionPackage(packageInfo);
            if (!b){
                servicePackageList.add(packageInfo.packageName);
            }
        }
        return servicePackageList;
    }
    ////////////       CUSTOMIZABLE         ///////////////
    private ArrayList<String> onCustomizableList() {
        ArrayList<String> servicePackageList = new ArrayList<String>();

        Map<String, ?> allEntries;
        SharedPreferences sharedPrefs = getApplicationContext().getSharedPreferences("PREFS_APP_LOCK_LIST", Context.MODE_PRIVATE);
        allEntries = null;
        allEntries = sharedPrefs.getAll();

        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            servicePackageList.add(entry.getKey());

        }

        return servicePackageList;
    }
    private boolean isExceptionPackage(PackageInfo packageInfo) {
        if (packageInfo.packageName.contains("com.whatsapp")) {
            return true;
        } else if (packageInfo.packageName.contains("org.telegram.messenger")) {
            return true;
        } else if (packageInfo.packageName.contains("kz.tech.esparta")) {
            return true;
        } else if (packageInfo.packageName.contains("com.facebook.orca")) {
            return true;
        }
        return false;
    }
    ////////////////////////            PREFS              //////////////////////
    private String onLoadLoginID() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_STORAGE_LOGIN, 0);
        return settings.getString("id", "");
    }
    private String[] onLoadAccountID() {
        String[] strings = new String[5];
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_STORAGE_FAMILY_ACCOUNT, 0);
        strings[0] = settings.getString("avatar", "");
        strings[1] = settings.getString("name", "");
        strings[2] = settings.getString("pin", "");
        strings[3] = settings.getString("status", "");
        strings[4] = settings.getString("id", "");
        return strings;
    }
    private void saveCoins(long coins) {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_CHILD_COINS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putLong("coins", coins);
        editor.apply();
    }
    private long loadCoins() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_CHILD_COINS, 0);
        return totalCoins = settings.getLong("coins", 0);
    }
    public String onLoadAppLockList() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_APP_LOCK_LIST_SELECT, 0);
        return settings.getString("name", "standardList");
    }
    ////////////////////             CHILDREN TIME          /////////////////////
    private boolean onChildrenTime() {
        Calendar calendar = Calendar.getInstance();
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String currentTodayTime = "";
        String currentHour = "";
        String currentMinute = "";
        if (hour_of_day < 9) {
            currentHour = "0" + String.valueOf(hour_of_day);
        } else {
            currentHour = String.valueOf(hour_of_day);
        }
        if (hour_of_day < 9) {
            currentMinute = "0" + String.valueOf(minute);
        } else {
            currentMinute = String.valueOf(minute);
        }
        currentTodayTime = currentHour + ":" + currentMinute;

        String currentDay = "";

        if (Calendar.MONDAY == dayOfWeek) currentDay = "monday";
        else if (Calendar.TUESDAY == dayOfWeek) currentDay = "tuesday";
        else if (Calendar.WEDNESDAY == dayOfWeek) currentDay = "wednesday";
        else if (Calendar.THURSDAY == dayOfWeek) currentDay = "thursday";
        else if (Calendar.FRIDAY == dayOfWeek) currentDay = "friday";
        else if (Calendar.SATURDAY == dayOfWeek) currentDay = "saturday";
        else if (Calendar.SUNDAY == dayOfWeek) currentDay = "sunday";



        SharedPreferences preferences = getApplicationContext().getSharedPreferences(PREFS_STORAGE_CHILDREN_TIME, 0);

        String weekDay = "";

        switch (currentDay) {
            case "monday": weekDay = preferences.getString("monday", "22:00-12:00"); break;
            case "tuesday": weekDay = preferences.getString("tuesday", "22:00-12:00"); break;
            case "wednesday": weekDay = preferences.getString("wednesday", "22:00-12:00"); break;
            case "thursday": weekDay = preferences.getString("thursday", "22:00-12:00"); break;
            case "friday": weekDay = preferences.getString("friday", "22:00-12:00"); break;
            case "saturday": weekDay = preferences.getString("saturday", "22:00-12:00"); break;
            case "sunday": weekDay = preferences.getString("sunday", "22:00-12:00"); break;
        }
        if (weekDay.equals("close")) {
            return true;
        }
        String[] parseDate = weekDay.split("-");//  PARSE DATE DASH
        String startDate = parseDate[0];//  GET START DATE
        String endDate = parseDate[1];//  GET END DATE

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        Date currentTime = null;
        Date startTime = null;
        Date endTime = null;
        try {
            currentTime = simpleDateFormat.parse(currentTodayTime);
            startTime = simpleDateFormat.parse(startDate);
            endTime = simpleDateFormat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(startTime.getTime() >= currentTime.getTime() && currentTime.getTime() >= endTime.getTime()) {
           // return true;

        }
        return false;
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
            if (countDownTimer != null) {
                countDownTimer = null;
            }
        }
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }
        if (statusAccount != null) {
            if (statusAccount.equals("Son") || statusAccount.equals("Daughter")) {
                /////////////////////            BROADCAST RECEIVER          //////////////////////
                Intent in = new Intent();
                in.setAction("YouWillNeverKillMe");
                sendBroadcast(in);
                /////////////////////                     SERVICE                  /////////////////////////
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  new version < 26
                    boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, getApplicationContext());
                    if (!isServiceLife) {
                        Intent intent = new Intent(getApplicationContext(), ChildService.class);
                        intent.putExtra("eSparta", "inputExtra");
                        ContextCompat.startForegroundService(getApplicationContext(), intent);
                    }
                }
                if (Build.VERSION.SDK_INT < 26) {//  old version device == 24
                    boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, getApplicationContext());
                    if (!isServiceLife) {
                        Intent intent = new Intent(getApplicationContext(), ChildService.class);
                        intent.putExtra("eSparta", "inputExtra");
                        PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);
                        AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                        alarm.cancel(pendingIntent);
                        alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
                    }
                }

            }
        }
        android.util.Log.e("TAG ", "Service: DESTROY");
        wakeLock.release();
        android.util.Log.e("TAG ", "WakeLock: Release");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true);
        }

    }
}




package kz.tech.esparta.childs.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.content.ContextCompat;

import kz.tech.esparta.root.service.CheckLaunchService;

public class ChildBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            if (Build.VERSION.SDK_INT < 26) {//  OLD VERSION
                boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, context);
                if (!isServiceLife) {
                    Intent i = new Intent(context, ChildService.class);
                    i.putExtra("eSparta", "inputExtra");
                    PendingIntent pendingIntent = PendingIntent.getService(context, 0, i, 0);
                    AlarmManager alarm = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                    alarm.cancel(pendingIntent);
                    alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 60000, pendingIntent);
                }
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  NEW VERSION
                boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, context);
                if (!isServiceLife) {
                    Intent j = new Intent(context, ChildService.class);
                    j.putExtra("eSparta", "inputExtra");
                    ContextCompat.startForegroundService(context, j);
                }
            }
        }
    }
}




package kz.tech.esparta.childs.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.JobIntentService;
import androidx.core.content.ContextCompat;

import kz.tech.esparta.root.service.CheckLaunchService;

public class MyJobIntentService extends JobIntentService {

    /* Give the Job a Unique Id */
    private static final int JOB_ID = 1000;
    public static void enqueueWork(Context ctx, Intent intent) {
        enqueueWork(ctx, MyJobIntentService.class, JOB_ID, intent);
    }

    @Override
    protected void onHandleWork(@NonNull Intent intent) {
        /* your code here */
        /* reset the alarm */
        /////////////////////                     SERVICE                  /////////////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  new version < 26
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, getApplicationContext());
            if (!isServiceLife) {
                Intent intent1 = new Intent(getApplicationContext(), ChildService.class);
                intent1.putExtra("eSparta", "inputExtra");
                ContextCompat.startForegroundService(getApplicationContext(), intent1);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  old version device == 24
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, getApplicationContext());
            if (!isServiceLife) {
                Intent intent2 = new Intent(getApplicationContext(), ChildService.class);
                intent2.putExtra("eSparta", "inputExtra");
                PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent2, 0);
                AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
            }
        }
        AlarmReceiver.setAlarm(getApplicationContext(),false);
        stopSelf();
    }

}




package kz.tech.esparta.childs.service;

import android.os.Build;

public class AndroidUtils {
    private static final String RECENT_ACTIVITY;

    static {
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP) {
            RECENT_ACTIVITY = "com.android.systemui.recents.RecentsActivity";
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN_MR1) {
            RECENT_ACTIVITY = "com.android.systemui.recent.RecentsActivity";
        } else {
            RECENT_ACTIVITY = "com.android.internal.policy.impl.RecentApplicationDialog";
        }
    }

    /**
     * To get the Device recent screen activity
     *
     * @param className
     * @return activity
     */
    public static boolean isRecentActivity(String className) {
        if (RECENT_ACTIVITY.equalsIgnoreCase(className)) {
            return true;
        }

        return false;
    }
}


package kz.tech.esparta.childs.service;

import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

public class MyJobService extends JobService {
    private static final String TAG = MyJobService.class.getSimpleName();
    private LocationManager mLocationManager;

    @Override
    public boolean onStartJob(JobParameters job) {
        Log.d(TAG, "onStartJob");
        Toast.makeText(getApplicationContext(), "Job Started", Toast.LENGTH_SHORT).show();
        for (int i = 0; i < 100; i++) {
            android.util.Log.e("TAG", "COUNT " + i);
        }
      /*  boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, getApplicationContext());
        if (!isServiceLife) {
            Intent intent = new Intent(getApplicationContext(), ChildService.class);
            intent.putExtra("eSparta", "inputExtra");
            PendingIntent pendingIntent = PendingIntent.getService(getApplicationContext(), 0, intent, 0);
            AlarmManager alarm = (AlarmManager) getApplicationContext().getSystemService(Context.ALARM_SERVICE);
            alarm.cancel(pendingIntent);
            alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
        }*/
        return false; // Answers the question: "Is there still work going on?"
    }

    @Override
    public boolean onStopJob(JobParameters job) {

        return false; // Answers the question: "Should this job be retried?"
    }
}



package kz.tech.esparta.block_page;

import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;


import io.reactivex.disposables.Disposable;

import kz.tech.esparta.childs.service.ChildService;
import kz.tech.esparta.root.MainActivity;
import kz.tech.esparta.root.firebase.FireBaseChildData;
import kz.tech.esparta.root.service.CheckLaunchService;

public class LockScreenActivity extends AppCompatActivity {
    private static final String PREFS_CHILD_COINS = "PREFS_CHILD_COINS";
    private BlockPageView view;
    long totalCoins = 0;
    String idLogin = "";
    String idChild = "";
    private Disposable disposable;
    String selectPackage = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        view = new BlockPageView(this);
        setContentView(view);
        view.setOnItemClickListener(new BlockPageView.OnItemClickListener() {
            @Override
            public void onItemClick() {
                startActivity(new Intent(LockScreenActivity.this, MainActivity.class));
            }
        });


        String[] strings = onLoadAccountID();

        if (strings[0] != null) {
            String image = strings[0];
            view.setAvatar(image);
        }

        Intent intent = getIntent();
         selectPackage = intent.getStringExtra("package_name");
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        //  List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
        if (selectPackage != null) {
            if (!selectPackage.equals("")) {
                if (am != null) {
                 //   am.killBackgroundProcesses(selectPackage);
                }
            }
        }
      //   finish();

     //   android.os.Process.killProcess(android.os.Process.myPid());

    /*    loadCoins();

        disposable = Flowable.interval(1L, TimeUnit.SECONDS)//  Время для интервала, выбор интервала, секунды, минуты, часы
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                    if (totalCoins == 0) {
                        onRequestTotalCoins();
                    }
                });*/
    }
    /*
     * So here's what's going on: When the user presses the back button,
     * the Instagram app (or any other app that's being blocked) is relaunched
     * from the stack and that triggers the LockScreenActivity to be launched
     * again. Kinda goes into an infinite loop in a sense. What I'm going to do
     * to get around that, is completely kill the Instagram application.
     */
    /*
     * Every running application has a PID (process id). This is how the system
     * keeps track of which apps are running. First, we're gonna need to find the
     * PID of the blocked app (Instagram in this case).
     */
    private void saveCoins() {
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences(PREFS_CHILD_COINS, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idLogin", idLogin);
        editor.putString("idChild", idChild);
        editor.putLong("coins", totalCoins);
        editor.apply();
    }
    private void loadCoins() {
        SharedPreferences settings = getApplicationContext().getSharedPreferences(PREFS_CHILD_COINS, 0);
        idLogin =  settings.getString("idLogin", "");
        idChild =  settings.getString("idChild", "");
        totalCoins =  settings.getLong("coins", 0);
    }

    private void onRequestTotalCoins() {
        DatabaseReference dbrChildData = new FireBaseChildData().getChildDataFB();//  ChildData
        dbrChildData.child(idLogin).child(idChild).child("coins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String value = (String) dataSnapshot.getValue();
                    totalCoins = Integer.parseInt(value);
                    if (totalCoins > 0) {
                        saveCoins();
                        Intent startMain = new Intent(Intent.ACTION_MAIN);
                        startMain.addCategory(Intent.CATEGORY_HOME);
                        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        getApplicationContext().startActivity(startMain);
                    }
                } else {
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Grab a list of all running processes and their PIDs.
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
        // Now loop through the list of PIDs and find Instagram's PID.
        // Killing any process for blocked applications when the back button is pressed while the lock screen is displayed
     //   am.killBackgroundProcesses("kz.tech.esparta");
        am.killBackgroundProcesses(selectPackage);

        // Display confirmation here, finish() activity.
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

      //  android.os.Process.killProcess(android.os.Process.myPid());

        /////////////////////                     SERVICE                  /////////////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  new version < 26
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, this);
            if (!isServiceLife) {
                Intent intent = new Intent(this, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                ContextCompat.startForegroundService(this, intent);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  old version device == 24
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, this);
            if (!isServiceLife) {
                Intent intent = new Intent(this, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
                AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
            }
        }



        finish();
        // android.os.Process.killProcess(android.os.Process.myPid());
        super.onBackPressed();
    }
    /*
     * So here's what's going on: When the user presses the back button,
     * the Instagram app (or any other app that's being blocked) is relaunched
     * from the stack and that triggers the LockScreenActivity to be launched
     * again. Kinda goes into an infinite loop in a sense. What I'm going to do
     * to get around that, is completely kill the Instagram application.
     */
    /*
     * Every running application has a PID (process id). This is how the system
     * keeps track of which apps are running. First, we're gonna need to find the
     * PID of the blocked app (Instagram in this case).
     */

    @Override
    public void onStop() {
        // Grab a list of all running processes and their PIDs.
        ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
        // Now loop through the list of PIDs and find Instagram's PID.
        // Killing any process for blocked applications when the back button is pressed while the lock screen is displayed
     //   am.killBackgroundProcesses("kz.tech.esparta");
        am.killBackgroundProcesses(selectPackage);

        // Display confirmation here, finish() activity.
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);

    //    android.os.Process.killProcess(android.os.Process.myPid());

        /////////////////////                     SERVICE                  /////////////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  new version < 26
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, this);
            if (!isServiceLife) {
                Intent intent = new Intent(this, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                ContextCompat.startForegroundService(this, intent);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  old version device == 24
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, this);
            if (!isServiceLife) {
                Intent intent = new Intent(this, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                PendingIntent pendingIntent = PendingIntent.getService(this, 0, intent, 0);
                AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
            }
        }

        finish();
        // android.os.Process.killProcess(android.os.Process.myPid());
            super.onStop();
    }
    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disposable != null && !disposable.isDisposed()) {
            disposable.dispose();
        }

    }


    public String[] onLoadAccountID() {
        String[] strings = new String[5];
        SharedPreferences settings = getSharedPreferences("PREFS_STORAGE_FAMILY_ACCOUNT", 0);
        strings[0] = settings.getString("avatar", "");
        strings[1] = settings.getString("name", "");
        strings[2] = settings.getString("pin", "");
        strings[3] = settings.getString("status", "");
        strings[4] = settings.getString("id", "");
        return strings;
    }
    public String onLoadLoginID() {
        SharedPreferences settings = getSharedPreferences("PREFS_STORAGE_LOGIN", 0);
        return settings.getString("id", "");
    }
}


public byte[] recoverImageFromUrl(String urlText) throws Exception {
        try {

            URL imageUrl = new URL(urlText);
            URLConnection ucon = imageUrl.openConnection();

            InputStream is = ucon.getInputStream();

            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int read = 0;

            while ((read = is.read(buffer, 0, buffer.length)) != -1) {
                baos.write(buffer, 0, read);
            }

            baos.flush();

            return  baos.toByteArray();

        } catch (Exception e) {
           // Log.d("ImageManager", "Error: " + e.toString());
        }
        return null;
    }
    
    
      private void onXiaomiAutoStart() {
        //    Intent intent1 = new Intent();
        //    intent1.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
        //    main.startActivity(intent1);


        Intent iLOL = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        iLOL.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        Uri uri = Uri.fromParts("package", main.getPackageName(), null);
        iLOL.setData(uri);
        startActivity(iLOL);
    }
    
    
     private void addAutoStartup() {

        try {
            Intent intent = new Intent();
            String manufacturer = android.os.Build.MANUFACTURER;
            if ("xiaomi".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            } else if ("oppo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
            } else if ("vivo".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
            } else if ("Letv".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity"));
            } else if ("Honor".equalsIgnoreCase(manufacturer)) {
                intent.setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity"));
            }

            List<ResolveInfo> list = main.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
            if  (list.size() > 0) {
                main.startActivity(intent);
            }
        } catch (Exception e) {
            Log.e("exc" , String.valueOf(e));
        }
    }



package kz.tech.esparta.auth.app_lock_list.adapters;

import android.content.Context;

import android.content.SharedPreferences;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import kz.tech.esparta.R;
import kz.tech.esparta.auth.app_lock_list.ItemsAppLockList;


public class AppLockListAdapter extends RecyclerView.Adapter<AppLockListAdapter.ViewHolder> {
    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener {
        void onItemClick(int position);
        void onRemoveAllViewsClick();
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    private Context context;
    private boolean isChecked = false;
    private boolean isStandardList = false;
    private List<ApplicationInfo> arrayList;

    public AppLockListAdapter(Context context) {
        this.context = context;
        this.arrayList = new ArrayList<ApplicationInfo>();
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(new ItemsAppLockList(context));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ApplicationInfo applicationInfo = arrayList.get(position);
        if (applicationInfo != null) {
            if (position == 0) {
                holder.vTop.setVisibility(View.VISIBLE);
            }
            Drawable icon = context.getPackageManager().getApplicationIcon(applicationInfo);
            CharSequence name = context.getPackageManager().getApplicationLabel(applicationInfo);
          //  long timeInstall = context.getPackageManager().getApp
            long installed = 0;
            try {
               installed = context.getPackageManager().getPackageInfo(applicationInfo.packageName, 0).firstInstallTime;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            Date installTime = new Date( installed );
            holder.ivIcon.setImageDrawable(icon);
            holder.tvText.setText(name);
            if (!isChecked) {//          STANDARD
                holder.ivCheckBox.setOnClickListener(null);
                if (!isStandardList) {
                    holder.ivCheckBox.setImageResource(R.mipmap.lock);
                } else {
                    if (isStandardListBool2(applicationInfo.packageName)) {
                        holder.ivCheckBox.setImageResource(R.mipmap.unlock);
                    } else {
                        holder.ivCheckBox.setImageResource(R.mipmap.lock);
                    }
                }
            } else {//         CUSTOMIZABLE
                String packageNameApp = applicationInfo.packageName;
                SharedPreferences settings = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);

                int checkedApp = settings.getInt(packageNameApp, 1);
                switch (checkedApp) {
                    case 1: holder.ivCheckBox.setImageResource(R.mipmap.unlock); break;
                    case 2: holder.ivCheckBox.setImageResource(R.mipmap.lock); break;
                    case 3: holder.ivCheckBox.setImageResource(R.mipmap.lock_new); break;
                }


            }
        }
    }
    @Override
    public int getItemCount() {
        return arrayList.size();
    }
    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivIcon, ivCheckBox;
        TextView tvText;
        View vTop;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivIcon = (ImageView) itemView.findViewById(kz.tech.esparta.root.var_resources.VarID.ID_IV_APP_LOCK_LIST_ICON);
            tvText = (TextView) itemView.findViewById(kz.tech.esparta.root.var_resources.VarID.ID_TV_APP_LOCK_LIST_TEXT);
            ivCheckBox = (ImageView) itemView.findViewById(kz.tech.esparta.root.var_resources.VarID.ID_IV_APP_LOCK_LIST_CHECK_BOX);
            vTop = (View) itemView.findViewById(kz.tech.esparta.root.var_resources.VarID.ID_IV_APP_LOCK_LIST_TOP);
            ivCheckBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isChecked) {
                        int position = getAdapterPosition();

                        SharedPreferences settings = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE);
                        int checkedApp = settings.getInt(arrayList.get(position).packageName, 1);

                        SharedPreferences.Editor editPackNameChecked = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE).edit();
                        SharedPreferences.Editor editPackName = context.getSharedPreferences("PREFS_APP_LOCK_LIST", Context.MODE_PRIVATE).edit();

                        if (checkedApp == 1) {
                            ivCheckBox.setImageResource(R.mipmap.lock);
                            editPackNameChecked.putInt(arrayList.get(position).packageName, 2);
                            editPackNameChecked.apply();

                            editPackName.putString(arrayList.get(position).packageName, arrayList.get(position).packageName);
                            editPackName.apply();
                        } else if (checkedApp == 2 || checkedApp == 3) {
                            ivCheckBox.setImageResource(R.mipmap.unlock);
                            editPackNameChecked.remove(arrayList.get(position).packageName);
                            editPackNameChecked.apply();

                            editPackName.remove(arrayList.get(position).packageName);
                            editPackName.apply();
                        }
                    }
                }
            });
        }
    }
    /*
      if (checkedApp == 1) {
                            ivCheckBox.setImageResource(R.mipmap.unlock);
                            editPackNameChecked.remove(arrayList.get(position).packageName);
                            editPackNameChecked.apply();

                            editPackName.remove(arrayList.get(position).packageName);
                            editPackName.apply();
                        } else if (checkedApp == 2 || checkedApp == 3) {
                            ivCheckBox.setImageResource(R.mipmap.lock);
                            editPackNameChecked.putBoolean(arrayList.get(position).packageName, true);
                            editPackNameChecked.apply();

                            editPackName.putString(arrayList.get(position).packageName, arrayList.get(position).packageName);
                            editPackName.apply();
                        }
     */

    public void setData(String select) {
        isStandardList = false;
        arrayList.clear();
        if (onItemClickListener != null) {
            onItemClickListener.onRemoveAllViewsClick();
        }
        switch (select) {
            case "standardList":
                isStandardList = true;
                isChecked = false;
                onStandardList();
                break;
            case "customizableList":
                isChecked = true;
                onCustomizableList();
                break;
        }
        notifyDataSetChanged();
    }
    ////////       CUSTOMIZABLE      ////////////////
    private void onCustomizableList() {
        SharedPreferences settings = context.getSharedPreferences("PREFS_APP_LOCK_TIME_INSTALL", Context.MODE_PRIVATE);
        boolean checkedApp = settings.getBoolean("firstInstall", false);
        long timeInstall = settings.getLong("timeInstall", 0);

        SharedPreferences.Editor editor = context.getSharedPreferences("PREFS_APP_LOCK_TIME_INSTALL", Context.MODE_PRIVATE).edit();



        PackageManager packageManager = context.getPackageManager();
        List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);

        for (PackageInfo packageInfo : packageList) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            arrayList.add(applicationInfo);
            if (!checkedApp) {
                if (settings.getLong("timeInstall", 0) < packageInfo.firstInstallTime) {
                    editor.putLong("timeInstall", packageInfo.firstInstallTime);
                    editor.apply();
                }
            } else {
                if (settings.getLong("timeInstall", 0) < packageInfo.firstInstallTime) {
                    SharedPreferences.Editor editPackNameChecked = context.getSharedPreferences(context.getApplicationContext().getPackageName(), Context.MODE_PRIVATE).edit();
                    SharedPreferences.Editor editPackName = context.getSharedPreferences("PREFS_APP_LOCK_LIST", Context.MODE_PRIVATE).edit();

                    editPackNameChecked.putInt(applicationInfo.packageName, 3);
                    editPackNameChecked.apply();

                    editPackName.putString(applicationInfo.packageName, applicationInfo.packageName);
                    editPackName.apply();
                }
            }

        }
        if (packageList.size() > 0) {
            editor.putBoolean("firstInstall", true);
            editor.apply();
        }
    }
    /////////        STANDARD        ////////////////
    private void onStandardList() {
        PackageManager packageManager = context.getPackageManager();

        List<PackageInfo> packageList = packageManager.getInstalledPackages(PackageManager.GET_PERMISSIONS);
        for (PackageInfo packageInfo : packageList) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (isStandardListBool(packageInfo)) {
                arrayList.add(applicationInfo);
            }
        }
        for (PackageInfo packageInfo : packageList) {
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if (!isStandardListBool(packageInfo)) {
                arrayList.add(applicationInfo);
            }
        }
    }
    private boolean isStandardListBool(PackageInfo packageInfo) {
        String[] arrPackages = {"com.whatsapp", "org.telegram.messenger", "kz.tech.esparta", "com.facebook.orca"};
        for (int i = 0; i < arrPackages.length; i++) {
            if (packageInfo.packageName.equals(arrPackages[i])) {
               return true;
            }
        }
        return false;
    }
    private boolean isStandardListBool2(String strPackage) {
        String[] arrPackages = {"com.whatsapp", "org.telegram.messenger", "kz.tech.esparta", "com.facebook.orca"};
        for (int i = 0; i < arrPackages.length; i++) {
            if (strPackage.equals(arrPackages[i])) {
                return true;
            }
        }
        return false;
    }


    private boolean isSystemPackage(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) ? true
                : false;
    }
    private boolean isSystemPackage1(PackageInfo pkgInfo) {
        return ((pkgInfo.applicationInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0) ? false
                : true;
    }
    private boolean isSystemPackage2(PackageInfo pkgInfo) {
        return ((pkgInfo.packageName.contains("com.android.systemui"))) ? false
                : true;
    }
    private boolean isExceptionPackage(PackageInfo packageInfo) {
        if (packageInfo.packageName.contains("com.whatsapp")) {
            return true;
        } else if (packageInfo.packageName.contains("org.telegram.messenger")) {
            return true;
        } else if (packageInfo.packageName.contains("kz.tech.esparta")) {
            return true;
        }
        return false;
    }
}
/*

        servicePackageList.add("com.facebook.katana");         //facebook
        servicePackageList.add("com.facebook.orca");           //facebook Messenger
        servicePackageList.add("com.google.android.youtube");  //youtube
        servicePackageList.add("com.twitter.android");         //twitter
        servicePackageList.add("com.google.android.gms");      //GooglePlay
 */
 
 
 
 /*    Class<?> c = null;
                Method get = null;
                String miui = null;
                try {
                    c = Class.forName("android.os.SystemProperties");
                     get = c.getMethod("get", String.class);
                     miui = (String) get.invoke(c, "ro.miui.ui.version.code");
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }*/
                
                
                
                  implementation fileTree(dir: 'libs', include: ['*.jar'])
    implementation 'androidx.appcompat:appcompat:1.0.2'
    implementation 'androidx.constraintlayout:constraintlayout:1.1.3'
    implementation 'androidx.legacy:legacy-support-v4:1.0.0'
    implementation "androidx.drawerlayout:drawerlayout:1.0.0"
    implementation 'androidx.recyclerview:recyclerview:1.0.0'
    implementation 'androidx.cardview:cardview:1.0.0'
    implementation 'com.google.android.material:material:1.0.0'

    //noinspection GradleCompatible
    implementation 'com.android.support:support-media-compat:28.0.0'

    implementation 'de.hdodenhof:circleimageview:3.0.0'
    //Test

    testImplementation 'junit:junit:4.12'
    androidTestImplementation 'androidx.test:runner:1.2.0'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.2.0'

    implementation 'com.android.volley:volley:1.1.1'
    //Picture Load
    implementation 'com.squareup.picasso:picasso:2.5.2'
    implementation 'com.github.bumptech.glide:glide:3.7.0'

    //RxJava 2
    implementation 'io.reactivex.rxjava2:rxandroid:2.1.1'
    implementation 'io.reactivex.rxjava2:rxjava:2.2.6'

    //Retrofit 2
    implementation 'com.squareup.retrofit2:retrofit:2.5.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.3.0'
    //Retrofit 2 + RxJava 2
    implementation 'com.squareup.retrofit2:adapter-rxjava2:2.5.0'

    //Room
    def roomVer = "1.1.1"
    implementation "android.arch.persistence.room:runtime:$roomVer"
    annotationProcessor "android.arch.persistence.room:compiler:$roomVer"
    //Room + RxJava 2
    implementation "android.arch.persistence.room:rxjava2:$roomVer"

    //Dagger 2
    def daggerVer = 2.20
    implementation "com.google.dagger:dagger:$daggerVer"
    implementation "com.google.dagger:dagger-android-support:$daggerVer"
    implementation "com.google.dagger:dagger-android:$daggerVer"
    annotationProcessor "com.google.dagger:dagger-android-processor:$daggerVer"
    annotationProcessor "com.google.dagger:dagger-compiler:$daggerVer"

    //FireBase
    def fireBaseVer = "19.0.0"
    implementation "com.google.firebase:firebase-database:$fireBaseVer"
    implementation "com.google.firebase:firebase-storage:$fireBaseVer"

    implementation 'com.firebase:firebase-jobdispatcher:0.8.6'

    def work_version = "2.3.0-alpha03"
    // (Java only)
    implementation "androidx.work:work-runtime:$work_version"
    // Kotlin + coroutines
    //implementation "androidx.work:work-runtime-ktx:$work_version"

    // optional - RxJava2 support
    implementation "androidx.work:work-rxjava2:$work_version"
    // optional - Test helpers
   // androidTestImplementation "androidx.work:work-testing:$work_version"
   // implementation "android.arch.work:work-runtime:1.0.0-alpha02"

    implementation 'com.github.PhilJay:MPAndroidChart:v3.1.0'

    implementation "javax.mail:javax.mail-api:1.6.2"
