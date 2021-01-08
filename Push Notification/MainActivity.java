public void goToNotificationSettings(String channel, Context context) {
    Intent intent = new Intent();
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        intent.addFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);
        if (channel != null) {
            intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        } else {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        }
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
    } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (channel != null) {
            intent.setAction(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel);
        } else {
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        }
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
    } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
    } else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
        intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
        intent.putExtra("app_package", context.getPackageName());
        intent.putExtra("app_uid", context.getApplicationInfo().uid);
    } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
        intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        intent.setData(Uri.parse("package:" + context.getPackageName()));
    }
    context.startActivity(intent);
}


if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
    Intent intent = new Intent();
    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
    intent.putExtra("app_package", getActivity().getPackageName());
    intent.putExtra("app_uid", getActivity().getApplicationInfo().uid);
    startActivity(intent);
} else if (android.os.Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
    Intent intent = new Intent();
    intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
    intent.addCategory(Intent.CATEGORY_DEFAULT);
    intent.setData(Uri.parse("package:" + getActivity().getPackageName()));
    startActivity(intent);
}


public void goToNotificationSettings(Context context) {

    String packageName = context.getPackageName();

    try {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.O) {

            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE, packageName);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK);

        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O) {

            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra("android.provider.extra.APP_PACKAGE", packageName);

        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {

            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", packageName);
            intent.putExtra("app_uid", context.getApplicationInfo().uid);

        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {

            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + packageName));

        } else {
            return;
        }

        startActivity(intent);

    } catch (Exception e) {
        // log goes here           

    }

}

fun openNotificationsSettings() {
    val intent = Intent()
    when {
        Build.VERSION.SDK_INT > Build.VERSION_CODES.O -> intent.setOpenSettingsForApiLarger25()
        Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP -> intent.setOpenSettingsForApiBetween21And25()
        else -> intent.setOpenSettingsForApiLess21()
    }
    app.startActivity(intent)
}

private fun Intent.setOpenSettingsForApiLarger25(){
    action = Settings.ACTION_APP_NOTIFICATION_SETTINGS
    putExtra("android.provider.extra.APP_PACKAGE", app.packageName)
}

private fun Intent.setOpenSettingsForApiBetween21And25(){
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    putExtra("app_package", app.packageName)
    putExtra("app_uid", app.applicationInfo?.uid)
}

private fun Intent.setOpenSettingsForApiLess21(){
    action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
    addCategory(Intent.CATEGORY_DEFAULT)
    data = Uri.parse("package:" + app.packageName)
}


public static void goToNotificationSettings(Context context) {
        Intent intent = new Intent();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.setData(Uri.fromParts(SCHEME, context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N_MR1) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
            intent.putExtra("app_package", context.getPackageName());
            intent.putExtra("app_uid", context.getApplicationInfo().uid);
        } else if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse("package:" + context.getPackageName()));
        } else {
            return;
        }
        context.startActivity(intent);
    }
    
    Intent intent = new Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS)
    .putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
startActivity(intent);


 if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
 Intent intent = new Intent("android.settings.CHANNEL_NOTIFICATION_SETTINGS");
        intent.putExtra("android.provider.extra.CHANNEL_ID", "ChannelID");
        intent.putExtra("android.provider.extra.APP_PACKAGE", getPackageName());
        startActivity(intent);
   } 
   
   public void goToPushSettingPage(Context context) {
    try {
        Intent intent=new Intent();
        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.N_MR1){
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(Settings.EXTRA_APP_PACKAGE,context.getPackageName());
        }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            intent.setAction(Settings.ACTION_APP_NOTIFICATION_SETTINGS);
            intent.putExtra(ConstUtil.PUSH_SETTING_APP_PACKAGE,context.getPackageName());
            intent.putExtra(ConstUtil.PUSH_SETTING_APP_UID,context.getApplicationInfo().uid);
        }else{
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            intent.addCategory(Intent.CATEGORY_DEFAULT);
            intent.setData(Uri.parse(ConstUtil.PUSH_SETTING_URI_PACKAGE+context.getPackageName()));
        }
        startActivity(intent);
    } catch (Exception e) {
        // log goes here
    }
}


public class NotificationService extends FirebaseMessagingService {
private static final String TAG = NotificationService.class.getSimpleName();
String body = "", dataa = "", title = "", type = "";
boolean notification, sound, vibration;
Uri uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
private Vibrator vib;
Ringtone ringtone;
private String Sender="";
private String SenderProfileUrl="";
private boolean isConnected;


@Override
public void onMessageReceived(RemoteMessage remoteMessage) {
    Log.e(TAG, "remoteMessage......" + remoteMessage.getData());
    try {
        Map<String, String> params = remoteMessage.getData();
        JSONObject object = new JSONObject(params);
        Log.e(TAG, object.toString());
        body = object.getString("not_id");
        dataa = object.getString("data");
        title = object.getString("not_type");
        type = object.getString("type");
        Sender = object.getString("Sender");
        SenderProfileUrl = object.getString("SenderProfileUrl");


    wakeUpScreen();
    addNotification(remoteMessage.getData());

}

/* when your phone is locked screen wakeup method*/
private void wakeUpScreen() {
    PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
    boolean isScreenOn = pm.isScreenOn();

    Log.e("screen on......", "" + isScreenOn);
    if (isScreenOn == false) {
        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.ON_AFTER_RELEASE, "MyLock");
        wl.acquire(10000);
        PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MyCpuLock");
        wl_cpu.acquire(10000);
    }
}

/*Add notification method use for add icon and title*/
private void addNotification(Map<String, String> data) {
    int icon = Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ?R.drawable.logo_app: R.drawable.logo_app;
    NotificationCompat.Builder builder =
            new NotificationCompat.Builder(this)
                    .setSmallIcon(icon)
                    //.setSmallIcon(R.drawable.logout_icon)
                    .setContentTitle(data.get("title") + "")
                    .setChannelId("channel-01")
                    .setAutoCancel(true)
                    .setSound(uri)
                    .setContentText(data.get("body") + "");

    Notification notification = new Notification();

    Log.e(TAG, "titlee" + data.get("title"));

    // Cancel the notification after its selected
    notification.flags |= Notification.FLAG_AUTO_CANCEL;
    if (sound)
        notification.defaults |= Notification.DEFAULT_SOUND;

    if (vibration)
        notification.defaults |= Notification.DEFAULT_VIBRATE;

    builder.setDefaults(notification.defaults);

    try {
        if (Sharedpref.getUserPreferences(this, Global.USER_Profile) == null) {
            /*start login activity*/
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        } else {

            /*notification send with type calling */
            if (data.get("not_type").equals("calling")) {
                if (data.get("type").equals("name")) {
                    Log.e(TAG, "ttt--" + type);
                    Intent notificationIntent = new Intent(this, CallingActivity.class);
                    notificationIntent.putExtra("notification_room_id", body);
                    notificationIntent.putExtra("data", dataa);
                    notificationIntent.putExtra("calling", "calling");

                    PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);
                    builder.setSound(uri);
                    builder.setAutoCancel(true);

                    // Add as notification
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        CharSequence name = "Hello";// The user-visible name of the channel.
                        int importance = NotificationManager.IMPORTANCE_HIGH;
                        NotificationChannel mChannel = new NotificationChannel("channel-01", name, importance);
                        manager.createNotificationChannel(mChannel);
                    }
                    manager.notify((int) ((new Date().getTime() / 1000L) % Integer.MAX_VALUE), builder.build());

               }

            } else {

                /* notification get no type then go to the else case*/
                Intent notificationIntent1 = new Intent(this, HomeActivity.class);
                PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent1, PendingIntent.FLAG_UPDATE_CURRENT);
                builder.setContentIntent(contentIntent);
                builder.setSound(uri);
                builder.setAutoCancel(true);
            }
        }
    } catch (NullPointerException e) {
        e.printStackTrace();
    }
}
 }
    
    
    notificationBuilder.setChannelId(channelName)
      notificationManager.createNotificationChannel(notificationChannel)
      notificationManager.notify(message.hashCode(), notificationBuilder.build())

    } else {
      notificationManager.notify(message.hashCode(), notificationBuilder.build())
    }

if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
  val importance = NotificationManager.IMPORTANCE_HIGH
  val notificationChannel = NotificationChannel(channelId, channelName, importance)
      
      notificationChannel.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
      
      
      private fun showNotificationWith(message: String) {
    val channelId = "com.example.notif.channelId"
    val channelName = "App status"
    val contentTitle = "Title"
    val notificationManager = applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_small_exclamation)
            .setContentTitle(contentTitle)
            .setContentText(message)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)

    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
        val importance = NotificationManager.IMPORTANCE_HIGH
        val notificationChannel = NotificationChannel(channelId, channelName, importance)
        notificationBuilder.setChannelId(channelName)
        notificationManager.createNotificationChannel(notificationChannel)
        notificationManager.notify(message.hashCode(), notificationBuilder.build())

    } else {
        notificationManager.notify(message.hashCode(), notificationBuilder.build())
    }
      
      
      // Logic to turn on the screen
PowerManager powerManager = (PowerManager) context.getSystemService(POWER_SERVICE);

if (!powerManager.isInteractive()){ // if screen is not already on, turn it on (get wake_lock for 10 seconds)
    PowerManager.WakeLock wl = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MH24_SCREENLOCK");
    wl.acquire(10000);
    PowerManager.WakeLock wl_cpu = powerManager.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MH24_SCREENLOCK");
    wl_cpu.acquire(10000);
}

// Notification stuff

NotificationCompat.Builder builder = new NotificationCompat.Builder(context.getApplicationContext());
builder.setSmallIcon(R.drawable.attendance_icon);
builder.setContentTitle("Attendance not marked");
builder.setContentText("Attendance is not marked for today. Please mark the same.");

TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(context.getApplicationContext());
Intent intent = new Intent(context.getApplicationContext(), ActivityNewRecord.class);
taskStackBuilder.addParentStack(ActivityNewRecord.class);
taskStackBuilder.addNextIntent(intent);

PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(100, PendingIntent.FLAG_UPDATE_CURRENT);
builder.setContentIntent(pendingIntent);
final long[] DEFAULT_VIBRATE_PATTERN = {0, 250, 250, 250};
builder.setVibrate(DEFAULT_VIBRATE_PATTERN);
builder.setLights(Color.WHITE, 2000, 3000);
builder.setSound(Settings.System.DEFAULT_NOTIFICATION_URI);

// This is the answer to OP's question, set the visibility of notification to public.
builder.setVisibility(NotificationCompat.VISIBILITY_PUBLIC); 


NotificationManager notificationManager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
notificationManager.notify(100, builder.build());
}
