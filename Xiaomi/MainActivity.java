 try {
        Intent intent = new Intent();
        String manufacturer = android.os.Build.MANUFACTURER;
        if ("xiaomi".equalsIgnoreCase(manufacturer)) {
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
        } else if ("oppo".equalsIgnoreCase(manufacturer)) {
            intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
        } else if ("vivo".equalsIgnoreCase(manufacturer)) {
            intent.setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity"));
        }

        List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        if  (list.size() > 0) {
            context.startActivity(intent);
        } 
    } catch (Exception e) {
        Crashlytics.logException(e);
    }
    
    
    
    String manufacturer = "xiaomi";
        if(manufacturer.equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
            //this will open auto start screen where user can enable permission for your app
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        }
        
        
        if ("xiaomi".equalsIgnoreCase(str)) 
{
 intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
                    } 
else if ("oppo".equalsIgnoreCase(str)) 
{
 intent.setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity"));
                    } 
else if ("vivo".equalsIgnoreCase(str)) 
{
  intent.setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.MainGuideActivity."));
 }
 
 import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.support.v7.widget.AppCompatCheckBox;
import android.widget.CompoundButton;
import java.util.List;

public class Utils {

public static void startPowerSaverIntent(Context context) {
    SharedPreferences settings = context.getSharedPreferences("ProtectedApps", Context.MODE_PRIVATE);
    boolean skipMessage = settings.getBoolean("skipProtectedAppCheck", false);
    if (!skipMessage) {
        final SharedPreferences.Editor editor = settings.edit();
        boolean foundCorrectIntent = false;
        for (Intent intent : Constants.POWERMANAGER_INTENTS) {
            if (isCallable(context, intent)) {
                foundCorrectIntent = true;
                final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(context);
                dontShowAgain.setText("Do not show again");
                dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        editor.putBoolean("skipProtectedAppCheck", isChecked);
                        editor.apply();
                    }
                });

                new AlertDialog.Builder(context)
                        .setTitle(Build.MANUFACTURER + " Protected Apps")
                        .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", context.getString(R.string.app_name)))
                        .setView(dontShowAgain)
                        .setPositiveButton("Go to settings", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                context.startActivity(intent);
                            }
                        })
                        .setNegativeButton(android.R.string.cancel, null)
                        .show();
                break;
            }
        }
        if (!foundCorrectIntent) {
            editor.putBoolean("skipProtectedAppCheck", true);
            editor.apply();
        }
    }
}

private static boolean isCallable(Context context, Intent intent) {
    List<ResolveInfo> list = context.getPackageManager().queryIntentActivities(intent,
            PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
}
}
}

import android.content.ComponentName;
import android.content.Intent;
import java.util.Arrays;
import java.util.List;

public class Constants {

public static List<Intent> POWERMANAGER_INTENTS = Arrays.asList(
        new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
        new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
        new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
        new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
        new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
        new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(android.net.Uri.parse("mobilemanager://function/entry/AutoStart"))
);
}

if (manufactXiaomi.equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
        if (!session.getVisibilityOfAutoStartDialog()) {Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);}}




private void ifHuaweiAlert() {
    final SharedPreferences settings = getSharedPreferences("ProtectedApps", MODE_PRIVATE);
    final String saveIfSkip = "skipProtectedAppsMessage";
    boolean skipMessage = settings.getBoolean(saveIfSkip, false);
    if (!skipMessage) {
        final SharedPreferences.Editor editor = settings.edit();
        Intent intent = new Intent();
        intent.setClassName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity");
        if (isCallable(intent)) {
            final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(this);
            dontShowAgain.setText("Do not show again");
            dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    editor.putBoolean(saveIfSkip, isChecked);
                    editor.apply();
                }
            });

            new AlertDialog.Builder(this)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Huawei Protected Apps")
                    .setMessage(String.format("%s requires to be enabled in 'Protected Apps' to function properly.%n", getString(R.string.app_name)))
                    .setView(dontShowAgain)
                    .setPositiveButton("Protected Apps", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            huaweiProtectedApps();
                        }
                    })
                    .setNegativeButton(android.R.string.cancel, null)
                    .show();
        } else {
            editor.putBoolean(saveIfSkip, true);
            editor.apply();
        }
    }
}

private boolean isCallable(Intent intent) {
    List<ResolveInfo> list = getPackageManager().queryIntentActivities(intent,
            PackageManager.MATCH_DEFAULT_ONLY);
    return list.size() > 0;
}

private void huaweiProtectedApps() {
    try {
        String cmd = "am start -n com.huawei.systemmanager/.optimize.process.ProtectActivity";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            cmd += " --user " + getUserSerial();
        }
        Runtime.getRuntime().exec(cmd);
    } catch (IOException ignored) {
    }
}

private String getUserSerial() {
    //noinspection ResourceType
    Object userManager = getSystemService("user");
    if (null == userManager) return "";

    try {
        Method myUserHandleMethod = android.os.Process.class.getMethod("myUserHandle", (Class<?>[]) null);
        Object myUserHandle = myUserHandleMethod.invoke(android.os.Process.class, (Object[]) null);
        Method getSerialNumberForUser = userManager.getClass().getMethod("getSerialNumberForUser", myUserHandle.getClass());
        Long userSerial = (Long) getSerialNumberForUser.invoke(userManager, myUserHandle);
        if (userSerial != null) {
            return String.valueOf(userSerial);
        } else {
            return "";
        }
    } catch (NoSuchMethodException | IllegalArgumentException | InvocationTargetException | IllegalAccessException ignored) {
    }
    return "";
}

String manufacturer = "xiaomi";
        if(manufacturer.equalsIgnoreCase(android.os.Build.MANUFACTURER)) {
            //this will open auto start screen where user can enable permission for your app
            Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity"));
            startActivity(intent);
        }


String xiaomi = "Xiaomi";
final String CALC_PACKAGE_NAME = "com.miui.securitycenter";
final String CALC_PACKAGE_ACITIVITY = "com.miui.permcenter.autostart.AutoStartManagementActivity";
if (deviceManufacturer.equalsIgnoreCase(xiaomi)) {
    DisplayUtils.showDialog(activity, "Ask for permission", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            try {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName(CALC_PACKAGE_NAME, CALC_PACKAGE_ACITIVITY));
                activity.startActivity(intent);
            } catch (ActivityNotFoundException e) {
                Logger.e(TAG, "Failed to launch AutoStart Screen ", e);
            } catch (Exception e) {
                Logger.e(TAG, "Failed to launch AutoStart Screen ", e);
            }
        }
    }, new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialogInterface, int i) {

        }
    });
}
