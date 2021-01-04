 <service
            android:name="kz.tech.esparta.childs.child_main.service.MyJobService"
            android:exported="false"
            android:permission="android.permission.BIND_JOB_SERVICE" />



    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ComponentName componentName = new ComponentName(getActivity(), MyJobService.class);
            JobInfo.Builder jobInfo = new JobInfo.Builder(123, componentName);
                   // .setRequiresCharging(true)
            jobInfo
                //    .setPersisted(true)
                    .setOverrideDeadline(2 * 60 * 1000)
                    .build();
            JobScheduler jobScheduler = (JobScheduler) getActivity().getSystemService(JOB_SCHEDULER_SERVICE);
            int resultCode = jobScheduler.schedule(jobInfo.build());

              if (resultCode == JobScheduler.RESULT_SUCCESS) {
                android.util.Log.e("Tag", "jobScheduler");
            } else {
                android.util.Log.e("Tag", "jobScheduler error");
            }

        }
        
        
        import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import kz.tech.esparta.childs.child_main.service.ChildService;
import kz.tech.esparta.root.service.CheckLaunchService;

public class ChildBroadcastReceiver extends BroadcastReceiver {
    private static final String TAG_BOOT_BROADCAST_RECEIVER = "BOOT_BROADCAST_RECEIVER";
    static final String TAG = "SR";

    final int startupID = 1111111;
    @Override
    public void onReceive(Context context, Intent intent) {
        String[] strings = onLoadAccountID(context);
        String idLogin = onLoadLoginID(context);
        if (strings[3].equals("Son") || strings[3].equals("Daughter")) {
         //   context.startService(new Intent(context, ChildService.class)
          //  .putExtra("idLogin", idLogin)
         //   .putExtra("idAccount", strings[4]));

         //   startServiceByAlarm(context);
        }


        String action = intent.getAction();

        String message = "BootDeviceReceiver onReceive, action is " + action;

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

      //  Log.fdfd(TAG_BOOT_BROADCAST_RECEIVER, action);

        if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, main);
            if (!isServiceLife) {
                Intent i = new Intent(main, ChildService.class);
                i.putExtra("eSparta", "inputExtra");
                PendingIntent pendingIntent = PendingIntent.getService(main, 0, i, 0);
                AlarmManager alarm = (AlarmManager) main.getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
            }


        }

    }
    /* Start RunAfterBootService service directly and invoke the service every 10 seconds. */
    private void startServiceDirectly(Context context) {
        try {
            while (true) {
                String message = "BootDeviceReceiver onReceive start service directly.";

                Toast.makeText(context, message, Toast.LENGTH_LONG).show();

                Log.d(TAG_BOOT_BROADCAST_RECEIVER, message);

                // This intent is used to start background service. The same service will be invoked for each invoke in the loop.
                Intent startServiceIntent = new Intent(context, ChildService.class);
                context.startService(startServiceIntent);

                // Current thread will sleep one second.
                Thread.sleep(10000);
            }
        }catch(InterruptedException ex) {
            Log.e(TAG_BOOT_BROADCAST_RECEIVER, ex.getMessage(), ex);
        }
    }
    /* Create an repeat Alarm that will invoke the background service for each execution time.
     * The interval time can be specified by your self.  */
    private void startServiceByAlarm(Context context) {
        // Get alarm manager.
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Create intent to invoke the background service.
        Intent intent = new Intent(context, ChildService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long startTime = System.currentTimeMillis();
        long intervalTime = 60*1000;

        String message = "Start service use repeat alarm. ";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();

        Log.d(TAG_BOOT_BROADCAST_RECEIVER, message);

        // Create repeat alarm.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);
    }

    public String[] onLoadAccountID(Context context) {
        String[] strings = new String[5];
        SharedPreferences settings = context.getSharedPreferences("PREFS_STORAGE_ACCOUNT", 0);
        strings[0] = settings.getString("avatar", "");
        strings[1] = settings.getString("name", "");
        strings[2] = settings.getString("pin", "");
        strings[3] = settings.getString("status", "");
        strings[4] = settings.getString("id", "");
        return strings;
    }
    public String onLoadLoginID(Context context) {
        SharedPreferences settings = context.getSharedPreferences("PREFS_STORAGE_LOGIN", 0);
        return settings.getString("id", "");
    }
}


public void getForegroundLaunchApp() {
        ArrayList<String> packagezList = new ArrayList<String>();
        packagezList.add("com.android.gallery3d");       //Gallery
        packagezList.add("com.android.mms");             //??
        packagezList.add("com.tencent.mm");              //??
        packagezList.add("com.android.contacts");        //??????
        packagezList.add("com.facebook.katana");         //facebook
        packagezList.add("com.facebook.orca");           //facebook Messenger
        packagezList.add("com.mediatek.filemanager");    //?????
        packagezList.add("com.sec.android.gallery3d");   //?????
        packagezList.add("com.android.email");           //??
        packagezList.add("com.sec.android.app.myfiles"); //?????
        packagezList.add("com.android.vending");         //????
        packagezList.add("com.google.android.youtube");  //youtube
        packagezList.add("com.tencent.mobileqq");        //qq
        packagezList.add("com.tencent.qq");              //qq
        packagezList.add("com.android.dialer");          //??
        packagezList.add("com.twitter.android");         //twitter
        packagezList.add("com.google.android.gms");         //GooglePlay

        String topPackageName;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            UsageStatsManager mUsageStatsManager = (UsageStatsManager) getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
            long time = System.currentTimeMillis();
            // We get usage stats for the last 10 seconds
            List < UsageStats > stats = mUsageStatsManager.queryUsageStats(UsageStatsManager.INTERVAL_DAILY, time - 1000 * 10, time);
            // Sort the stats by the last time used
            if (stats != null) {
                SortedMap<Long, UsageStats> mySortedMap = new TreeMap<Long, UsageStats>();
                for (UsageStats usageStats : stats) {
                    mySortedMap.put(usageStats.getLastTimeUsed(), usageStats);
                }
                if (mySortedMap != null && !mySortedMap.isEmpty()) {
                    topPackageName = mySortedMap.get(mySortedMap.lastKey()).getPackageName();
                   for (int m = 0; m < packagezList.size(); m++) {
                       if (packagezList.get(m).contains(topPackageName)) {
                           ActivityManager am = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
                           List<ActivityManager.RunningAppProcessInfo> pids = am.getRunningAppProcesses();
                           am.killBackgroundProcesses(topPackageName);
                           //  android.os.Process.killProcess(android.os.Process.myPid());
                           Intent startMain = new Intent(Intent.ACTION_MAIN);
                           startMain.addCategory(Intent.CATEGORY_HOME);
                           startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                           getApplicationContext().startActivity(startMain);

                           Intent i = new Intent(getApplicationContext(), LockScreenActivity.class);
                           i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_NO_ANIMATION);
                           i.putExtra("", "");
                           getApplicationContext().startActivity(i);
                          return;
                       }
                   }
                }
            }
        } else {
            List<ActivityManager.RunningTaskInfo> appTasks = activityManager.getRunningTasks(1);
            if (null != appTasks && !appTasks.isEmpty()) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.Q) {
                    String lol =  appTasks.get(0).topActivity.getPackageName();
                }
            }
        }
    }


    public void amKillProcess(String process) {
        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        final List<ActivityManager.RunningAppProcessInfo> runningProcesses = am.getRunningAppProcesses();

        for(ActivityManager.RunningAppProcessInfo runningProcess : runningProcesses) {
            if(runningProcess.processName.equals(process)) {
                android.os.Process.sendSignal(runningProcess.pid, android.os.Process.SIGNAL_KILL);
            }
        }
    }
    private String getTopPackageTry() {


        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return activityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
            }
        } else {
            ActivityManager manager = (ActivityManager) getApplicationContext().getApplicationContext().getSystemService(ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> tasks = manager.getRunningAppProcesses();

            return tasks.get(0).processName;
        }

        return "";
    }





 public void clearMemory(String text) {
        ActivityManager activityManger = (ActivityManager) getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> list = activityManger.getRunningAppProcesses();
        if (list != null)
            for (int i = 0; i < list.size(); i++) {
                ActivityManager.RunningAppProcessInfo apinfo = list.get(i);

                String[] pkgList = apinfo.pkgList;
                String sss = pkgList[0];
                if (sss.contains(text)) {
                    android.util.Log.e("Shag", "FUCK YOU ");
                 //   activityManger.killBackgroundProcesses(text);
                }

                if (apinfo.importance > ActivityManager.RunningAppProcessInfo.IMPORTANCE_PERCEPTIBLE ) {
                    for (int j = 0; j < pkgList.length; j++) {
                        activityManger.killBackgroundProcesses(pkgList[j]);
                    }
                }
            }
       }



private static final int NOTIFICATION_ID = 1;

 	private void showForegroundNotification(String contentText) {
		// Create intent that will bring our app to the front, as if it was tapped in the app
		// launcher
		Intent showTaskIntent = new Intent(getApplicationContext(), MyMainActivity.class);
		showTaskIntent.setAction(Intent.ACTION_MAIN);
		showTaskIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		showTaskIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		PendingIntent contentIntent = PendingIntent.getActivity(
				getApplicationContext(),
				0,
				showTaskIntent,
				PendingIntent.FLAG_UPDATE_CURRENT);

		Notification notification = new Notification.Builder(getApplicationContext())
				.setContentTitle(getString(R.string.app_name))
				.setContentText(contentText)
				.setSmallIcon(R.drawable.ic_notification)
				.setWhen(System.currentTimeMillis())
				.setContentIntent(contentIntent)
				.build();
		startForeground(NOTIFICATION_ID, notification);
	}


void onPushContract(ModelContract modelContract, int count) {
        String longText = "To have a notification appear in an expanded view, " +
                "first create a NotificationCompat.Builder object " +
                "with the normal view options you want. " +
                "Next, call Builder.setStyle() with an " +
                "expanded layout object as its argument.";


        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.present);
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)

                       // .setSmallIcon(R.mipmap.present)
                        .setColor(Color.BLUE)
                        .setContentTitle(modelContract.getTitle())
                        .setContentText(modelContract.getDescription())
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                        .setLargeIcon(bitmap);
             //   .setStyle(new NotificationCompat.BigTextStyle().bigText(longText));
        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(count, notification);
    }


 private void startMyOwnForeground(String input){
        String NOTIFICATION_CHANNEL_ID = "kz.tech.esparta";
        String channelName = "My Background Service";
        NotificationChannel chan = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan = new NotificationChannel(NOTIFICATION_CHANNEL_ID, input, NotificationManager.IMPORTANCE_NONE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan.setLightColor(Color.BLUE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            chan.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        }
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        assert manager != null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            manager.createNotificationChannel(chan);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.drawable.ic_android)
                .setContentTitle(input)
                .setPriority(NotificationManager.IMPORTANCE_MIN)
                .setCategory(Notification.CATEGORY_SERVICE)
                .build();
        startForeground(2, notification);
    }










  private void startServiceByAlarm(Context context) {
        // Get alarm manager.
        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);

        // Create intent to invoke the background service.
        Intent intent = new Intent(context, ChildService.class);
        PendingIntent pendingIntent = PendingIntent.getService(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long startTime = System.currentTimeMillis();
        long intervalTime = 60*1000;

        String message = "Start service use repeat alarm. ";

        Toast.makeText(context, message, Toast.LENGTH_LONG).show();



        // Create repeat alarm.
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startTime, intervalTime, pendingIntent);
    }
