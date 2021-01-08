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
