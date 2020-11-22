        int[][] states = new int[][] { new int[] { android.R.attr.state_activated }, new int[] { -android.R.attr.state_activated } };
        int[] colors = {kz.tech.esparta.root.var_resources.ColorsRGB.GRAY_THREE, kz.tech.esparta.root.var_resources.ColorsRGB.BLACK};
        ColorStateList colorStateList = new ColorStateList(states, colors);
        
         Observable.interval(1, TimeUnit.SECONDS, Schedulers.io())
                    .take(2)
                    .map(v -> 2 - v)
                    .subscribe(onNext -> {
                            },//on every second pass trigger
                            onError -> {
                            },//do on error
                            () -> {
                                onSelectFragment("SignInFragment");
                            },
                            onSubscribe -> {
                                onSelectFragment("Splash");
                            });
                            
                             public static int WHITE = Color.rgb(255, 255, 255);
    public static int BLACK = Color.rgb(0, 0, 0);

    public static int RED_ONE = Color.rgb(229, 19, 60);

    public static int GRAY_ONE = Color.rgb(67, 67, 66);
    public static int GRAY_TWO = Color.rgb(245, 245, 245);
    public static int GRAY_THREE = Color.rgb(152, 152, 152);
    public static int GRAY_FOUR = Color.rgb(74, 74, 74);
    public static int GRAY_FIVE = Color.rgb(206, 206, 206);

    public static int BLUE_ONE = Color.rgb(0, 137, 208);

    public static int GREEN_ONE = Color.rgb(0, 149, 137);
    
     tv.setCompoundDrawablesWithIntrinsicBounds(R.mipmap.red_print, 0, 0, 0);
     
     editText.setInputType(android.text.InputType.TYPE_CLASS_NUMBER);
     
         editText.setFilters(new android.text.InputFilter[] {
                                    kz.tech.esparta.root.tools.GetInputFilterNumb.filter ,
                                    new android.text.InputFilter.LengthFilter((int)4)});
                                    
                                    
                                     private void setSelectedTextColor(NumberPicker np, Context c) {
        int selectedColorCode = Color.rgb(0, 137, 208);
        int defaultColor = Color.rgb(152, 152, 152);
        final ColorStateList colors = new ColorStateList(
                new int[][]{
                        new int[]{android.R.attr.state_selected},
                        new int[]{-android.R.attr.state_selected}
                },
                new int[]{selectedColorCode, defaultColor}
        );
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            final int count = np.getChildCount();
            for (int i = 0; i < count; i++) {
                View child = np.getChildAt(i);
                if (child instanceof EditText) {
                    try {
                        ((EditText) child).setTextColor(colors);
                        np.invalidate();

                        Field selectorWheelPaintField = np.getClass().getDeclaredField("mSelectorWheelPaint");
                        boolean accessible = selectorWheelPaintField.isAccessible();
                        selectorWheelPaintField.setAccessible(true);
                    //    ((Paint) selectorWheelPaintField.get(np)).setColor(defaultColor);
                        selectorWheelPaintField.setAccessible(accessible);
                        np.invalidate();

                        Field selectionDividerField = np.getClass().getDeclaredField("mSelectionDivider");
                        accessible = selectionDividerField.isAccessible();
                        selectionDividerField.setAccessible(true);
                        selectionDividerField.set(np, null);
                        selectionDividerField.setAccessible(accessible);
                        np.invalidate();
                    } catch (Exception exception) {
                      //  Logger.exc(exception);
                    }
                }
            }
        } else {
            np.setTextColor(defaultColor);
        }
    }
    
        final String[] values1 = new String[]{"-10", "-9", "-8", "-7", "-6", "-5", "-4","-3","-2","-1", "0",
                                    "+1", "+2", "+3", "+4", "+5", "+6", "+7", "+8", "+9", "+10", "+11", "+12", "+13", "+14", "+15"};
                            np.setDisplayedValues(values1);
                            
                              np.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
                                      case "Wrap"://  Wrap Selector Wheel
                    switch (data[1]) {//  VALUE
                        case "false":
                            np.setWrapSelectorWheel(false);
                            break;
                    }
                    break;
                case "MinValue":
                    np.setMinValue(Integer.parseInt(data[1]));
                    break;
                case "MaxValue":
                    np.setMaxValue(Integer.parseInt(data[1]));
                    break;
                case "Value":
                    np.setValue(Integer.parseInt(data[1]));
                    break;
                    
                    NumberPicker
                    
                        public GradientDrawable onChangeBackground(float radius, int stroke, int colorStroke, int colorBackground) {
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setCornerRadius(radius);
        gradientDrawable.setStroke(
                stroke,
                colorStroke
                );
        gradientDrawable.setColor(colorBackground);
        return gradientDrawable;
    }
    
    
       @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RippleDrawable getRipple(float radius, int stroke, int colorStroke, int colorFirst, int colorSecond) {
        return new RippleDrawable(
                new kz.tech.esparta.root.ui.animation.GetColorStateList()
                .getStateList(colorSecond),
                new kz.tech.esparta.root.ui.animation.GetSelectDrawable()
                .onChangeBackground(radius, stroke, colorStroke, colorFirst), null);
    }
    
        public ColorStateList getStateList(int color) {
        return ColorStateList.valueOf(color);
    }
    
    
    package kz.tech.esparta.root;

public class MyThreadHint {

}

/*  disposable = Flowable.interval(1L, TimeUnit.SECONDS)//  Время для интервала, выбор интервала, секунды, минуты, часы
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(aLong -> {
                   // android.util.Log.e("TAG", "COUNT ");
                    if (totalCoins == 0) {
                      //  getForegroundLaunchApp();
                       // Toast.makeText(getApplicationContext(), getRecentApps(getApplicationContext()),Toast.LENGTH_SHORT).show();
                       // android.util.Log.e("APP ", getRecentApps(getApplicationContext()));
                       lockApps();
                    }
                });*/


 /*  Observable.interval(20, 4000, TimeUnit.MILLISECONDS)
                .map(aLong -> aLong + offset)

                .observeOn(AndroidSchedulers.mainThread())
                // 購読(subscribe)
                .subscribe(aLong -> {
                    android.util.Log.e("Shag", "FUCK YOU " + loh);
                    loh++;
                });*/
 /*
 //
      //  PeriodicWorkRequest ddddd = new PeriodicWorkRequest.Builder(WorkerChildService.class, 60, TimeUnit.SECONDS)
            //    60, TimeUnit.SECONDS)
     //           .build();
     //   WorkManager.getInstance().enqueue(ddddd);


//
     //   PeriodicWorkRequest PeriodicWorkRequest = new PeriodicWorkRequest.Builder(WorkerChildService.class, 1, TimeUnit.MINUTES)
      //          .build();
     //   WorkManager.getInstance().enqueue(PeriodicWorkRequest);

      //  OneTimeWorkRequest myWorkRequest = new OneTimeWorkRequest.Builder(WorkerChildService.class)

            //    .build();
       // WorkManager.getInstance().enqueue(myWorkRequest);

  /*      FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(new GooglePlayDriver(getActivity()));

        Job myJob = dispatcher.newJobBuilder()
                .setService(MyJobService.class) // the JobService that will be called
                .setTag("my-unique-tag")        // uniquely identifies the job
                .setRecurring(false)
                .setLifetime(Lifetime.FOREVER)
                .build();

        dispatcher.mustSchedule(myJob);*/
/*
 private void test1() {
        packageManager = main.getPackageManager();
        List<PackageInfo> packageList1 = new ArrayList<PackageInfo>();

        List<PackageInfo> packageList = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);

        List<PackageInfo> packageList2 = packageManager
                .getInstalledPackages(PackageManager.GET_PERMISSIONS);


        for(PackageInfo pi : packageList) {

            boolean b = isSystemPackage(pi);
            boolean c = isSystemPackage1(pi);
            boolean d = isSystemPackage2(pi);


            if ((!b || !c ) && d ){
                packageList1.add(pi);
            }

        }
        android.util.Log.e("Shag", "FUCK YOU ");

        for(PackageInfo pi : packageList) {

            boolean b = isExceptionPackage(pi);
           // boolean b = isSystemPackage3(pi);
           // boolean c = isSystemPackage4(pi);
          //  boolean x = isSystemPackage5(pi);

            if (b) {
                packageList1.add(pi);
            }

        }

        android.util.Log.e("Shag", "FUCK YOU ");

        final PackageItemInfo.DisplayNameComparator comparator = new PackageItemInfo.DisplayNameComparator(packageManager);

        Collections.sort(packageList1, new Comparator<PackageInfo>() {
            @Override
            public int compare(PackageInfo lhs, PackageInfo rhs) {
                return comparator.compare(lhs.applicationInfo, rhs.applicationInfo);
            }
        });

        SharedPreferences.Editor editor = main.getSharedPreferences(main.getApplicationContext().getPackageName(), Context.MODE_PRIVATE).edit();
        SharedPreferences.Editor editorapp = main.getSharedPreferences("appdb", Context.MODE_PRIVATE).edit();


        for (int i = 0; i < packageList1.size(); i++) {
            final PackageInfo packageInfo = (PackageInfo) packageList1.get(i);
            android.util.Log.e("Shag", packageInfo.packageName);
            editor.putBoolean(packageInfo.packageName, true);
            editorapp.putString(packageInfo.packageName, packageInfo.packageName);
            editor.apply();
            editorapp.apply();
        }


    }


    public void amKillProcess(String process) {
        ActivityManager am = (ActivityManager) main.getSystemService(Context.ACTIVITY_SERVICE);
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
                return mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
            }
        } else {
            ActivityManager manager = (ActivityManager) main.getApplicationContext().getSystemService(ACTIVITY_SERVICE);

            List<ActivityManager.RunningAppProcessInfo> tasks = manager.getRunningAppProcesses();

            return tasks.get(0).processName;
        }

           return "";
    }
    private String getTopPackageName() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                return mActivityManager.getRunningTasks(1).get(0).topActivity.getPackageName();
            }
        } else {
            // Hack, see
            // http://stackoverflow.com/questions/24625936/getrunningtasks-doesnt-work-in-android-l/27140347#27140347
            pis = mActivityManager.getRunningAppProcesses();
            if(pis!=null){
            }
            else {


                for (ActivityManager.RunningAppProcessInfo pi : pis) {
                    if (pi.pkgList.length == 1) return pi.pkgList[0];
                }
            }
        }
        return "";
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
 */
 
 
 
 package kz.tech.esparta.root.tools;

import android.text.InputFilter;
import android.text.Spanned;

public class EditTextInputFilterMinMax implements InputFilter {
    private int min;
    private int max;

    public EditTextInputFilterMinMax(int min, int max) {
        this.min = min;
        this.max = max;
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        //noinspection EmptyCatchBlock
        try {
            int input = Integer.parseInt(dest.subSequence(0, dstart).toString() + source + dest.subSequence(dend, dest.length()));
            if (isInRange(min, max, input))
                return null;
        } catch (NumberFormatException nfe) { }
        return "";
    }

    private boolean isInRange(int a, int b, int c) {
        return b > a ? c >= a && c <= b : c >= b && c <= a;
    }
}


    public static byte[] getAvatar(CircleImageView civAvatar) {
        civAvatar.setDrawingCacheEnabled(true);
       // civAvatar.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
      //          View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
      //  civAvatar.layout(0, 0, civAvatar.getMeasuredWidth(), civAvatar.getMeasuredHeight());
       // civAvatar.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(civAvatar.getDrawingCache());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();

    }
    
       public static InputFilter filter = new InputFilter() {
        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            String blockCharacterSet = "~#^|$%*!@/()-'\":;,?{}=!$^';,?×÷<>{}€£¥₩%~`¤♡♥_|《》¡¿°•○●□■◇◆♧♣▲▼▶◀↑↓←→☆★▪:-);-):-D:-(:'(: N.+";
            if (source != null && blockCharacterSet.contains(("" + source))) {
                return "";
            }
            return null;
        }
    };
    
      public static byte[] intToByteArray(int value) {
        if ((value >>> 24) > 0) {
            return new byte[]{
                    (byte) (value >>> 24),
                    (byte) (value >>> 16),
                    (byte) (value >>> 8),
                    (byte) value
            };
        } else if ((value >> 16) > 0) {
            return new byte[]{
                    (byte) (value >>> 16),
                    (byte) (value >>> 8),
                    (byte) value
            };
        } else if ((value >> 8) > 0) {
            return new byte[]{
                    (byte) (value >>> 8),
                    (byte) value
            };
        } else {
            return new byte[]{
                    (byte) value
            };
        }
    }
    
    public static byte[] getData(ImageView imageView) {
        imageView.setDrawingCacheEnabled(true);
        // civAvatar.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
        //          View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        //  civAvatar.layout(0, 0, civAvatar.getMeasuredWidth(), civAvatar.getMeasuredHeight());
        // civAvatar.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(imageView.getDrawingCache());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
        return outputStream.toByteArray();

    }
    
    public static boolean isMyServiceRunning(Class<?> serviceClass, Context context) {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
    
    package kz.tech.esparta.root.service;

import java.util.List;
import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

public class CheckRunningApplicationReceiver extends BroadcastReceiver {
    public final String TAG = "CRAR"; // CheckRunningApplicationReceiver
    @Override
    public void onReceive(Context context, Intent intent) {
        try {

            // Using ACTIVITY_SERVICE with getSystemService(String)
            // to retrieve a ActivityManager for interacting with the global system state.

            ActivityManager am = (ActivityManager) context
                    .getSystemService(Context.ACTIVITY_SERVICE);

            // Return a list of the tasks that are currently running,
            // with the most recent being first and older ones after in order.
            // Taken 1 inside getRunningTasks method means want to take only
            // top activity from stack and forgot the olders.

            List<ActivityManager.RunningTaskInfo> alltasks = am
                    .getRunningTasks(1);

            //
            for (ActivityManager.RunningTaskInfo aTask : alltasks) {


                // Used to check for CALL screen

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (aTask.topActivity.getClassName().equals("com.android.phone.InCallScreen")
                            || aTask.topActivity.getClassName().equals("com.android.contacts.DialtactsActivity"))
                    {
                        // When user on call screen show a alert message
                        Toast.makeText(context, "Phone Call Screen.", Toast.LENGTH_LONG).show();
                    }
                }

                // Used to check for SMS screen

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (aTask.topActivity.getClassName().equals("com.android.mms.ui.ConversationList")
                            || aTask.topActivity.getClassName().equals("com.android.mms.ui.ComposeMessageActivity"))
                    {
                        // When user on Send SMS screen show a alert message
                        Toast.makeText(context, "Send SMS Screen.", Toast.LENGTH_LONG).show();
                    }
                }


                // Used to check for CURRENT example main screen

                String packageName = "com.example.checkcurrentrunningapplication";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    if (aTask.topActivity.getClassName().equals(
                            packageName + ".Main"))
                    {
                        // When opens this example screen then show a alert message
                        Toast.makeText(context, "Check Current Running Application Example Screen.",
                                Toast.LENGTH_LONG).show();
                    }
                }


                // These are showing current running activity in logcat with
                // the use of different methods

                Log.i(TAG, "===============================");

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Log.i(TAG, "aTask.baseActivity: "
                            + aTask.baseActivity.flattenToShortString());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Log.i(TAG, "aTask.baseActivity: "
                            + aTask.baseActivity.getClassName());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Log.i(TAG, "aTask.topActivity: "
                            + aTask.topActivity.flattenToShortString());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    Log.i(TAG, "aTask.topActivity: "
                            + aTask.topActivity.getClassName());
                }

                Log.i(TAG, "===============================");


            }

        } catch (Throwable t) {
            Log.i(TAG, "Throwable caught: "
                    + t.getMessage(), t);
        }
    }
}


package kz.tech.esparta.root.permission_access;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import kz.tech.esparta.root.MainActivity;
import kz.tech.esparta.root.var_resources.PermissionCode;

public class PermissionAccess implements IPermissionAccess{
    private Context context;
    public PermissionAccess(Context context) {
        this.context = context;
    }
    private boolean hasPermissionToReadNetworkHistory() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            return true;
        }
        final AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
        int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                android.os.Process.myUid(), context.getPackageName());
        if (mode == AppOpsManager.MODE_ALLOWED) {
            return true;
        }
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            appOps.startWatchingMode(AppOpsManager.OPSTR_GET_USAGE_STATS,
                    context.getApplicationContext().getPackageName(),
                    new AppOpsManager.OnOpChangedListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.KITKAT)
                        public void onOpChanged(String op, String packageName) {
                            int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS,
                                    android.os.Process.myUid(), context.getPackageName());
                            if (mode != AppOpsManager.MODE_ALLOWED) {
                                return;
                            }
                            appOps.stopWatchingMode(this);
                           // Intent intent = new Intent(MainActivity.this, MainActivity.class);
                           // intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                          //  getApplicationContext().startActivity(intent);
                        }
                    });
        }
        //  requestReadNetworkHistoryAccess();
        return false;
    }

    @Override
    public boolean isToReadNetworkHistory() {
        return hasPermissionToReadNetworkHistory();
    }

    /////////////////          PHONE ACCESS           ///////////////////////////////
    @Override
    public boolean isPhoneAccess(MainActivity main) {
        return isCheckPhonePermission(main);
    }
    @Override
    public void onPhoneAccess(MainActivity main) {
        String[] permissions = { Manifest.permission.READ_PHONE_STATE };
        ActivityCompat.requestPermissions(main, permissions, PermissionCode.LOCATION_PERMISSION_REQUEST_CODE);
    }
    private boolean isCheckPhonePermission(MainActivity main) {
        if (ContextCompat.checkSelfPermission(main.getApplicationContext(), PermissionCode.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            return false;
        }
    }
}
/*


if (mode == AppOpsManager.MODE_DEFAULT) {
    granted = (context.checkCallingOrSelfPermission(Manifest.permission.PACKAGE_USAGE_STATS) == PackageManager.PERMISSION_GRANTED);
} else {
    granted = (mode == AppOpsManager.MODE_ALLOWED);
}


if (mode == AppOpsManager.MODE_DEFAULT) {
    granted = false;
} else {
    granted = (mode == AppOpsManager.MODE_ALLOWED);
}

granted = (mode == AppOpsManager.MODE_ALLOWED);

AppOpsManager appOps = (AppOpsManager) context.getSystemService(Context.APP_OPS_SERVICE);
int mode = appOps.checkOpNoThrow(AppOpsManager.OPSTR_GET_USAGE_STATS, Process.myUid(), context.getPackageName());
boolean granted = (mode == AppOpsManager.MODE_ALLOWED);
 */

  /*
     public boolean check_UsgAccs(){
        long tme = System.currentTimeMillis();
        UsageStatsManager usm = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            usm = (UsageStatsManager)getActivity().getApplicationContext().getSystemService(Context.USAGE_STATS_SERVICE);
            List<UsageStats> al= usm.queryUsageStats(UsageStatsManager.INTERVAL_YEARLY, tme - (1000 * 1000), tme);
            return  al.size()>0;
        }

       return false;

    }
     */
     
     
     
      private void onImageDataByte(byte[] data) {
        UploadTask uploadTask = new FireBaseImage().uploadImage("Avatars").putBytes(data);//  Set image byte array
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String uriToString = uri.toString();//  Get download URI
                        if (uriToString != null) {
                            strAvatar = uriToString;
                        }
                    }
                });
            }
        });
    }
    
     String firstText = res.getString(R.string.forgotten_password);
        String textFirst =  res.getString(R.string.drop_him);
        int startIndex = 0;
        int endIndex = firstText.length();

        SpannableString spannableString = new SpannableString(firstText+ " "  + textFirst);
        //spannableString.setSpan(new UnderlineSpan(), startIndex, endIndex, 0);
        spannableString.setSpan(new ForegroundColorSpan(Color.rgb(152, 152, 152)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);



 private void getUrlImage(byte[] data, ModelContract m) {
        UploadTask uploadTask = new FireBaseImage().uploadImage("Avatars").putBytes(data);//  Set image byte array
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Task<Uri> result = taskSnapshot.getMetadata().getReference().getDownloadUrl();
                result.addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        String uriToString = uri.toString();//  Get download URI
                        if (uriToString != null) {
                            avatarIcon = uriToString;
                            if (isEditContract) {
                                m.setAvatar(avatarIcon);
                            }
                        }
                    }
                });
            }
        });
    }
    
    
     BarChart barChart = new BarChart(context);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 0);
        params.setMargins(DimensionDP.sizeDP(30,context) ,0,DimensionDP.sizeDP(30, context),0);
        params.weight = 8;
        barChart.setLayoutParams(params);
        llBottom.addView(barChart);
        barChart.setScaleEnabled(false);
        barChart.setTouchEnabled(false);
        
         public void setDataWeek(BarChart barChart) {
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0,3.8f));
        barEntries.add(new BarEntry(1,4));
        barEntries.add(new BarEntry(2,4));
        barEntries.add(new BarEntry(3,4));
        barEntries.add(new BarEntry(4,4));
        barEntries.add(new BarEntry(5,4));
        barEntries.add(new BarEntry(6,3.8f));

        int blue1 = Color.rgb(100,149,237);
        int blue2 = Color.rgb(0,191,255);
        int blue3 = Color.rgb(0,191,255);
        int blue4 = Color.rgb(135,206,250);

        int[] colors = {blue1, blue1, blue2, blue3, blue3, blue4, blue4};

        BarDataSet barDataSet = new BarDataSet(barEntries, "");
        barDataSet.setColors(colors);

        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        String[] weeks = res.getStringArray(R.array.days_of_the_week);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(weeks));

        barChart.setData(barData);
        barChart.setDescription(null);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);//  Remove right table column
        barChart.getAxisLeft().setAxisMinimum(1f);//  Start numb table left numbers

        barChart.getAxisLeft().setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) Math.floor(value));
            }
        });

        int max = (int) barChart.getData().getYMax();
        barChart.getAxisLeft().setLabelCount(max);

        //  barChart.setViewPortOffsets(0f, 0f, 0f, 0f);

        // tvWeekText1, tvWeekText2, tvMonthText1, tvMonthText2;
        tvWeekText1.setText(res.getString(R.string.reports_week_text_one) + "3.5 " + res.getString(R.string.obols));
        tvWeekText2.setText(res.getString(R.string.reports_week_text_two) + "1.5 " + res.getString(R.string.times));


        barChart.getBarData().setDrawValues(false);

        LimitLine ll = new LimitLine(2.0f, "");
        ll.setLineWidth(1f);//  Line size height
        ll.enableDashedLine(20f, 5f, 0f);//  Line length and space
        //     ll.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_BOTTOM);





        barChart.getAxisLeft().addLimitLine(ll);//  Add LimitLine to BarChart
    }
    public void setDataMonth(BarChart barChart) {
        String[] weeks = new String[24];
        int[] months = {3,4,1,1,1,5,6,7,5,9,9,9,9,9,9,7,8,8,6,7,6,5,8,9};
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        for (int i = 0; i < 24; i++) {
            weeks[i] = "";
            barEntries.add(new BarEntry(i,months[i]));
        }


        BarDataSet barDataSet = new BarDataSet(barEntries, " ");
        BarData barData = new BarData(barDataSet);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

        xAxis.setValueFormatter(new IndexAxisValueFormatter(weeks));


        barChart.setData(barData);
        barChart.setDescription(null);
        barChart.getLegend().setEnabled(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getXAxis().setDrawGridLines(false);

        barChart.getAxisRight().setEnabled(false);//  Remove right table column


        tvMonthText1.setText(res.getString(R.string.reports_month_text_one) + "5.5 " + res.getString(R.string.obols));
        tvMonthText2.setText(res.getString(R.string.reports_week_text_two) + "2.2 " + res.getString(R.string.times));
        barChart.getBarData().setDrawValues(false);

        LimitLine ll = new LimitLine(2, "");
        ll.setLineWidth(1f);
        ll.enableDashedLine(20f, 5f, 0f);

        barChart.getAxisLeft().addLimitLine(ll);
    }
    
    
    
    package kz.tech.esparta.childs;

public class V {
}
/*
   String s = main.getCurrentDate();
        AlarmManager mAlarmManger = (AlarmManager)main.getSystemService(Context.ALARM_SERVICE);

        //Create pending intent & register it to your alarm notifier class
        Intent intent = new Intent(main, IntentService.class);
        intent.putExtra("uur", "1e"); // if you want
        PendingIntent pendingIntent = PendingIntent.getBroadcast(main, 0, intent, 0);

        //set timer you want alarm to work (here I have set it to 9.00)
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 11);
        calendar.set(Calendar.HOUR_OF_DAY, 22);
        calendar.set(Calendar.MINUTE, 44);
        calendar.set(Calendar.SECOND, 0);
        String z = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));

        //set that timer as a RTC Wakeup to alarm manager object
        mAlarmManger.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
 */
/*
   final PackageManager pm = getActivity().getPackageManager();
//get a list of installed apps.
        List<ApplicationInfo> packages = pm.getInstalledApplications(PackageManager.GET_META_DATA);

        for (ApplicationInfo packageInfo : packages) {
            Log.fdfd("TAG", "Installed package :" + packageInfo.packageName);
            Log.fdfd("TAG", "Source dir : " + packageInfo.sourceDir);
            Log.fdfd("TAG", "Launch Activity :" + pm.getLaunchIntentForPackage(packageInfo.packageName));
        }




        android.util.Log.e("Shag", "FUCK YOU ");
 */

/*
public String getUniqueID(){
    String myAndroidDeviceId = "";
    TelephonyManager mTelephony = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
    if (mTelephony.getDeviceId() != null){
        myAndroidDeviceId = mTelephony.getDeviceId();
    }else{
         myAndroidDeviceId = Secure.getString(getApplicationContext().getContentResolver(), Secure.ANDROID_ID);
    }
    return myAndroidDeviceId;
}
 */



// onLoadData();

      /*  BitmapFactory.Options options = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.present, options);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(getActivity())
                        .setSmallIcon(R.mipmap.present)
                        .setContentTitle("Title")
                        .setContentText("Notification text")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap));

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1, notification);*/

    /*    disposable1 = Flowable.interval(2L, TimeUnit.SECONDS)//  Время для интервала, выбор интервала, секунды, минуты, часы
                // スレッド(thread)
                //.delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                // 購読(subscribe)
                .subscribe(aLong -> {
                    android.util.Log.e("Shag", count +" Msg"); count++;
                });




        PowerManager pm = (PowerManager)getActivity().getSystemService(Context.POWER_SERVICE);




        disposable2 = Flowable.interval(1L, TimeUnit.SECONDS)//  Время для интервала, выбор интервала, секунды, минуты, часы
                // スレッド(thread)
                //.delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                // 購読(subscribe)
                .subscribe(aLong -> {
                    if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.KITKAT_WATCH) {
                        isScreenOn = pm.isScreenOn();
                    } else {
                        isScreenOn = pm.isInteractive();
                    }
                    android.util.Log.e("Shag", "is " + isScreenOn);
                    if(!isScreenOn) {
                        startTH();
                    }
                });


*/



/*
  Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int hour_of_day = calendar.get(Calendar.HOUR_OF_DAY);
        int hour = calendar.get(Calendar.HOUR);
        //int month = calendar.get(Calendar.MONTH);
 */

/*
    private void onBlockApplications() {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(main)
                        .setSmallIcon(R.drawable.ic_android)
                        .setContentTitle("Title")
                        .setContentText("Notification text");

        Notification notification = builder.build();

        NotificationManager notificationManager =
                (NotificationManager) main.getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(numb, notification);

        PackageManager packageManager = mainActivity.getPackageManager();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> appList = packageManager.queryIntentActivities(mainIntent, 0);
        Collections.sort(appList, new ResolveInfo.DisplayNameComparator(packageManager));
        List<PackageInfo> packs = packageManager.getInstalledPackages(0);
        for (int i = 0; i < packs.size(); i++) {
            PackageInfo p = packs.get(i);
            ApplicationInfo a = p.applicationInfo;
            // skip system apps if they shall not be included
            if ((a.flags & ApplicationInfo.FLAG_SYSTEM) == 1) {
                continue;
            }
           // appList.add(p.packageName);
        }
        android.util.Log.e("Tag", " Msg");
        numb++;
    }
 */

/*
private void startTH() {
        disposable3 = Flowable.interval(1L, TimeUnit.SECONDS)//  Время для интервала, выбор интервала, секунды, минуты, часы
                // スレッド(thread)
                //.delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                // 購読(subscribe)
                .subscribe(aLong -> {

                    android.util.Log.e("Shag", "FUCK YOU ");

                });
    }
    private void endTH() {
        if (disposable3 != null && !disposable3.isDisposed()) {
            disposable3.dispose();
        }
    }
 */




/*
 if (childSize ==3) {
                        ArrayList<ModelChildContracts> arrayList = new ArrayList<>();
                        ModelChildDataParse parse = dataSnapshot.getValue(ModelChildDataParse.class);
                        String totalCoins = parse.getCoins();
                        if (totalCoins != null) {
                            numbMinute = Integer.parseInt(totalCoins);
                            int coins = ((int) (10 * numbMinute) / 20) / 10;

                            if (numbMinute > 0) {
                                int[] data = new int[coins];

                                for (int i = 0; i < coins; i++) {
                                    data[i] = 1;
                                }


                                coinsAdapter.setData(data);
                                view.setDataCoins(totalCoins, main.getResLanguage());
                            }

                            if (parse.getContracts() instanceof HashMap) {
                                HashMap hashMap1 = (HashMap) parse.getContracts();
                                ArrayList<ModelContract> contractsList = new ArrayList<>(hashMap1.values());

                                for (int i = 0; i < contractsList.size(); i++) {

                                    HashMap hashMap2 = new HashMap((Map) contractsList.get(i));
                                    String title = String.valueOf(hashMap2.get("title"));
                                    if (title != null) {
                                        if (!title.equals("0")) {
                                            String avatar = String.valueOf(hashMap2.get("avatar"));
                                            String description = String.valueOf(hashMap2.get("description"));
                                            String countSteps = String.valueOf(hashMap2.get("countSteps"));

                                            arrayList.add(new ModelChildContracts(avatar, countSteps, description));
                                        }
                                    }
                                }
                                view.onClearContainerForContracts();
                                view.onSelectContractStatus(true);
                            } else if (parse.getContracts() instanceof ArrayList) {
                                view.onClearContainerForContracts();
                                view.onContractEmpty();//  No Contracts
                                view.onSelectContractStatus(false);
                            }
                        }
                        contractsAdapter.setData(arrayList);
                    } else if (childSize == 4) {
                        Iterator i = dataSnapshot.getChildren().iterator();
                        String s1 = null, s2 = null, s3 = null;
                        Object o1 = null;
                        ArrayList<ModelChildContracts> arrayList2 = new ArrayList<>();
                        while (i.hasNext()) {
                            s1 = (String) ((DataSnapshot) i.next()).getValue();
                            s2 = (String) ((DataSnapshot) i.next()).getValue();
                            o1 = (Object) ((DataSnapshot) i.next()).getValue();
                            s3 = (String) ((DataSnapshot) i.next()).getValue();
                        }
                        if (s1 != null) {
                            numbMinute = Integer.parseInt(s1);
                            int coins = ((int) (10 * numbMinute) / 20) / 10;

                            if (numbMinute > 0) {
                                int[] data = new int[coins];

                                for (int j = 0; j < coins; j++) {
                                    data[j] = 1;
                                }
                                coinsAdapter.setData(data);
                                view.setDataCoins(s1, main.getResLanguage());
                                view.getCoins().invalidate();
                            }
                        }
                        if (s2 != null) {
                            int totalPiggyCoins = Integer.parseInt(s2);
                            piggyAdapter.insertData(totalPiggyCoins);
                        }
                        if (o1 instanceof HashMap) {
                            HashMap hashMap1 = (HashMap) o1;
                            ArrayList<ModelContract> contractsList = new ArrayList<>(hashMap1.values());

                            for (int w = 0; w < contractsList.size(); w++) {

                                HashMap hashMap2 = new HashMap((Map) contractsList.get(w));
                                String title = String.valueOf(hashMap2.get("title"));
                                if (title != null) {
                                    if (!title.equals("0")) {
                                        String avatar = String.valueOf(hashMap2.get("avatar"));
                                        String description = String.valueOf(hashMap2.get("description"));
                                        String countSteps = String.valueOf(hashMap2.get("countSteps"));

                                        arrayList2.add(new ModelChildContracts(avatar, countSteps, description));
                                    }
                                }
                            }
                            view.onClearContainerForContracts();
                            view.onSelectContractStatus(true);
                        } else if (o1 instanceof ArrayList) {
                            view.onClearContainerForContracts();
                            view.onContractEmpty();//  No Contracts
                            view.onSelectContractStatus(false);
                        }
                    }
 */
 
 
 
 package kz.tech.esparta.childs;




import android.app.AlarmManager;
import android.app.PendingIntent;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;


import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import kz.tech.esparta.R;
import kz.tech.esparta.childs.adapters.ChildMainCoinsAdapter;
import kz.tech.esparta.childs.adapters.ChildMainContractsAdapter;


import kz.tech.esparta.childs.adapters.PiggyAdapter;

import kz.tech.esparta.childs.models.ModelPiggy;
import kz.tech.esparta.childs.service.AlarmReceiver;
import kz.tech.esparta.childs.service.ChildIntentService;
import kz.tech.esparta.childs.worker.WorkerChildService;
import kz.tech.esparta.root.MainActivity;
import kz.tech.esparta.root.models.ModelChildDataParse;
import kz.tech.esparta.root.models.ModelContract;
import kz.tech.esparta.childs.service.ChildService;
import kz.tech.esparta.root.models.ModelDevice;
import kz.tech.esparta.root.service.CheckLaunchService;


public class ChildMainFragment extends Fragment {
    private static final String PREFS_CHILD_COINS = "PREFS_CHILD_COINS";
    private static final String PREFS_CHILD_LOGIN = "PREFS_CHILD_LOGIN";
    private Disposable disChildDataUpdate;

    private MainActivity main;
    private ChildMainView view;
    private ChildMainCoinsAdapter coinsAdapter;
    private ChildMainContractsAdapter contractsAdapter;
    private PiggyAdapter piggyAdapter;

    private int numbMinute = 0;

    private boolean isHuawei = false;

    View.OnLongClickListener longClick;
    MyDragListener dragListener;


    private PiggyLongClickListener piggyListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        initViews();
        saveChildLogin();//  Save ID Login and ID Account for child by Service
        onRequestChildData();
        onTimerUpdateChildData();

    //   MyUtils.StartPowerSaverIntent(getActivity());
    //    Intent nIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
    //    nIntent.addCategory("android.intent.category.DEFAULT");
    //    nIntent.putExtra("message", "test");
     //   nIntent.setClass(main, AlarmReceiver.class);

    //    PendingIntent broadcast = PendingIntent.getBroadcast(main, 100, nIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.P && Build.MANUFACTURER.equals("HUAWEI")) {//HUAWEI
          //  MyUtils.StartPowerSaverIntent(getActivity());
            isHuawei = true;
        }
       ////////////////////               WORKER                 ///////////////////////////
       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            PeriodicWorkRequest.Builder myWorkBuilder =
                    new PeriodicWorkRequest.Builder(WorkerChildService.class, 15, TimeUnit.MINUTES);



         //   myWorkBuilder.setInitialDelay(10, TimeUnit.SECONDS);

            PeriodicWorkRequest myWork = myWorkBuilder.build();
            WorkManager.getInstance(main).enqueueUniquePeriodicWork("jobTag", ExistingPeriodicWorkPolicy.KEEP, myWork);
       }
       /////////////////////                     SERVICE                  /////////////////////////
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  new version < 26
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                ContextCompat.startForegroundService(main, intent);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  old version device == 24
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildService.class);
                intent.putExtra("eSparta", "inputExtra");
                PendingIntent pendingIntent = PendingIntent.getService(main, 0, intent, 0);
                AlarmManager alarm = (AlarmManager) main.getSystemService(Context.ALARM_SERVICE);
                alarm.cancel(pendingIntent);
                alarm.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 15000, pendingIntent);
            }
        }

        /////////////////        INTENT SERVICE          ////////////////////////
        //                 AUTO CHARGE                 //
 /*       if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  NEW VERSION
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildIntentService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildIntentService.class);
                intent.putExtra("inputService", "autoCharge");
                ContextCompat.startForegroundService(main, intent);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  OLD VERSION
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildIntentService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildIntentService.class);
                intent.putExtra("inputService", "autoCharge");
                main.startService(intent);
            }
        }
        //                 TIME CHILDREN               //
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {//  NEW VERSION
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildIntentService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildIntentService.class);
                intent.putExtra("inputService", "timeChildren");
                ContextCompat.startForegroundService(main, intent);
            }
        }
        if (Build.VERSION.SDK_INT < 26) {//  OLD VERSION
            boolean isServiceLife = CheckLaunchService.isMyServiceRunning(ChildIntentService.class, main);
            if (!isServiceLife) {
                Intent intent = new Intent(main, ChildIntentService.class);
                intent.putExtra("inputService", "timeChildren");
                main.startService(intent);
            }
        }*/
        return view;
    }

    private void initViews() {
        main = (MainActivity) getActivity();
        view = new ChildMainView(getActivity(), main.iRes.getIRes());

        coinsAdapter = new ChildMainCoinsAdapter(getActivity());
        view.getCoins().setAdapter(coinsAdapter);
        coinsAdapter.setOnItemClickListener(new ChildMainCoinsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v) {
                v.setOnLongClickListener(longClick);
            }
        });



        contractsAdapter = new ChildMainContractsAdapter(getActivity());
        view.getRvContracts().setAdapter(contractsAdapter);

        piggyAdapter = new PiggyAdapter(getActivity());
        view.getPiggy().setAdapter(piggyAdapter);
        piggyAdapter.setOnItemClickListener(new PiggyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ImageView imageView) {
             //   imageView.setOnTouchListener(new CoinsChoiceTouchListener());
            }
        });

        view.setOnItemClickListener(new ChildMainView.OnItemClickListener() {
            @Override
            public void onBackButtonClick() {

            }

            @Override
            public void onMenuClick(View view) {
                PopupMenu menu = new PopupMenu(main, view);
                menu.getMenu().add(main.iRes.getIRes().getString(R.string.exit)).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        main.presenter.onSelectFragment("QuickAccessSignOutFragment");
                        return false;
                    }
                });

                menu.show();
            }
        });

        if (main.prefs.getFamilyData("avatar") != null) {
            String image = main.prefs.getFamilyData("avatar");
            view.setAvatar(image);
        }


     //   view.getCoins().setOnTouchListener(new PiggyChoiceTouchListener());




        longClick = new MyOnLongClickListener();

        view.getCoins().setOnLongClickListener(longClick);
        view.getCardViewCoins().setOnLongClickListener(longClick);


        dragListener = new MyDragListener();
        view.getCardViewPiggy().setOnDragListener(dragListener);
        ///////         TOUCH AND   DRUG

        //  TOUCH
     //   view.getCoins().setOnTouchListener(new ChoiceTouchListener());

   //     view.getBankIcon().setOnTouchListener(new ChoiceTouchListener());
        //  DRUG

    //   view.getPiggyIcon().setOnDragListener(new PiggyChoiceDragListener());

        //////////////            PIGGY              //////////////////
        piggyListener = new PiggyLongClickListener();
        view.getCardViewPiggy().setOnLongClickListener(piggyListener); //  TO TAKE

        view.getCardViewCoins().setOnDragListener(new PiggyDragListener());  //  DROP

    }
    ///////////             PIGGY           ////////////////
    class PiggyLongClickListener implements View.OnLongClickListener {
        public boolean onLongClick(View v) {
            view.onPiggyToCoinsOn();
            ClipData clipData = ClipData.newPlainText("Image", "Picture");
            View.DragShadowBuilder shadowBuilder = new DrawableDragShadowBuilder(v,  getResources().getDrawable(R.mipmap.coins_gold));
            v.startDrag(clipData, shadowBuilder, v, 0);
            return true;
        }
    }
    class PiggyDragListener implements View.OnDragListener {
        private boolean iGot;
        @SuppressWarnings("deprecation")
        public boolean onDrag(View v, DragEvent event) {
            int dragAction = event.getAction();
            switch (dragAction) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //  v.setBackgroundColor(Color.GREEN);
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //     v.setBackgroundColor(Color.BLUE);
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("TAG", "Location: "+event.getX()+":"+event.getY());
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                    //  v.setBackgroundColor(Color.YELLOW);
                    return true;
                case DragEvent.ACTION_DROP:
                    //      ImageView image = (ImageView)event.getLocalState();
                    //      v.setBackgroundDrawable(image.getDrawable());
                    //      iGot=true;

                    //   ClipData clipData= event.getClipData();
                    //    ClipData.Item item = clipData.getItemAt(0);
                    //    String str= (String) item.getText();
                    //  Log.i(TAG,"Item Text :"+str);
                    view.onPiggyToCoinsOff();
                    piggyAdapter.deleteData();
                    coinsAdapter.updateData();
                    view.updateDataCoinsPlus(20, numbMinute, main.iRes.getIRes());
                    insertCoins(20);


                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    if(!iGot)
                        v.setBackgroundColor(Color.WHITE);
                    if(event.getResult())
                        Log.i("","Dropped Successfully");
                    else
                        Log.i("","Drop not Successful");


                    view.onPiggyToCoinsOff();
                    return true;
            }
            return false;
        }


    }


    private void onTimerUpdateChildData() {
        disChildDataUpdate = Flowable.interval(30, TimeUnit.SECONDS)
                // スレッド(thread)
              //  .delay(10, TimeUnit.SECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                // 購読(subscribe)
                .subscribe(aLong -> {
                    onRequestChildData();
                });
    }
    private void onRequestChildData() {
        main.presenter.model.getChildDataDBR().child(main.prefs.getLoginData("id"))
                .child(main.prefs.getFamilyData("id"))
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                   if (dataSnapshot.getChildrenCount() == 6) {
                        Iterator i = dataSnapshot.getChildren().iterator();
                        String sCoins = null, sCoinsBank = null, sCoinsPiggy = null, sCoinsSpentToday = null, sChild = null;
                        Object oContracts = null;
                        ArrayList<ModelChildContracts> arrayList2 = new ArrayList<>();
                        while (i.hasNext()) {
                            sCoins = (String) ((DataSnapshot) i.next()).getValue();
                            sCoinsBank = (String) ((DataSnapshot) i.next()).getValue();
                            sCoinsPiggy = (String) ((DataSnapshot) i.next()).getValue();
                            sCoinsSpentToday = (String) ((DataSnapshot) i.next()).getValue();
                            oContracts = (Object) ((DataSnapshot) i.next()).getValue();
                            sChild = (String) ((DataSnapshot) i.next()).getValue();
                        }
                        if (sCoins != null) {
                            numbMinute = Integer.parseInt(sCoins);
                            int coins = ((int) (10 * numbMinute) / 20) / 10;
                            if (numbMinute > 0) {
                                int[] data = new int[coins];

                                for (int j = 0; j < coins; j++) {
                                    data[j] = 1;
                                }
                                coinsAdapter.setData(data);
                                view.setDataCoins(sCoins, main.iRes.getIRes());
                                view.getCoins().invalidate();
                            }
                        }
                       if (sCoinsBank != null) {
                           view.setBankCount(sCoinsBank);
                       }
                        if (sCoinsPiggy != null) {
                            int totalPiggyCoins = Integer.parseInt(sCoinsPiggy);
                            piggyAdapter.insertData(totalPiggyCoins);
                            view.setPiggyCount(sCoinsPiggy);
                        }
                        if (oContracts instanceof HashMap) {
                            HashMap hashMap1 = (HashMap) oContracts;
                            ArrayList<ModelContract> contractsList = new ArrayList<>(hashMap1.values());

                            for (int w = 0; w < contractsList.size(); w++) {

                                HashMap hashMap2 = new HashMap((Map) contractsList.get(w));
                                String title = String.valueOf(hashMap2.get("title"));
                                if (title != null) {
                                    if (!title.equals("0")) {
                                        String avatar = String.valueOf(hashMap2.get("avatar"));
                                        String description = String.valueOf(hashMap2.get("description"));
                                        String countSteps = String.valueOf(hashMap2.get("countSteps"));

                                        arrayList2.add(new ModelChildContracts(avatar, countSteps, description));
                                    }
                                }
                            }
                            view.onClearContainerForContracts();
                            view.onSelectContractStatus(true);
                        } else if (oContracts instanceof ArrayList) {
                            view.onClearContainerForContracts();
                            view.onContractEmpty();//  No Contracts
                            view.onSelectContractStatus(false);
                        }
                    }
                } else {
                    //  DataSnapshot: NOT EXIST
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void saveChildLogin() {
        SharedPreferences sharedPreferences = main.getSharedPreferences(PREFS_CHILD_LOGIN, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("idLogin", main.prefs.getLoginData("id"));
        editor.putString("idChild", main.prefs.getFamilyData("id"));
        editor.apply();
    }

    private static boolean isCallable(Context context, Intent intent) {
        List<ResolveInfo>list=context.getPackageManager().queryIntentActivities(intent,
                PackageManager.MATCH_DEFAULT_ONLY);
        return list.size() > 0;
    }
    @Override
    public void onResume() {
        super.onResume();

        //saveUserSessionManager is just a Preference you can set your preference class instead of SessionManager

      ///  if (!saveUserSessionManager.getDataByKey("skipProtectedAppCheck", false)) {

      //      Utils.startPowerSaverIntent(mContext, saveUserSessionManager);
       // }
    }
    public static void LogDeviceBrandActivities(Context context) {
        String brand = Build.BRAND.toLowerCase();
        String manufacturer = Build.MANUFACTURER.toLowerCase();

        List<PackageInfo> apps = context.getPackageManager().getInstalledPackages(PackageManager.GET_ACTIVITIES);

        Collections.sort(apps, (a, b) -> a.packageName.compareTo(b.packageName));
        for (PackageInfo pi : apps) {
            if (pi.packageName.toLowerCase().contains(brand) || pi.packageName.toLowerCase().contains(manufacturer)) {
                boolean print = false;
                StringBuilder activityInfo = new StringBuilder();

                if (pi.activities != null && pi.activities.length > 0) {
                    List<ActivityInfo> activities = Arrays.asList(pi.activities);

                    Collections.sort(activities, (a, b) -> a.name.compareTo(b.name));
                    for (ActivityInfo ai : activities) {
                        if (ai.name.toLowerCase().contains(brand) || ai.name.toLowerCase().contains(manufacturer)) {
                            activityInfo.append("  Activity: ").append(ai.name)
                                    .append(ai.permission == null || ai.permission.length() == 0 ? "" : " - Permission: " + ai.permission)
                                    .append("\n");
                            print = true;
                        }
                    }
                }

                if (print) {
                    Log.e("brand.activities", "PackageName: " + pi.packageName);
                    Log.w("brand.activities", activityInfo.toString());
                }
            }
        }
    }
    private void insertPiggyCoins(int coinsPiggyNumb1) {
        String idLogin = main.prefs.getLoginData("id");
        String idAccount = main.prefs.getFamilyData("id");
        String coins = "coins";
        String coinsPiggy = "coinsPiggy";
        final int coinsPiggyNumb2 = coinsPiggyNumb1;
        DatabaseReference dbr = main.presenter.model.getChildDataDBR();

        Query query = dbr.child(idLogin).child(idAccount);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    /////////      COINS      /////////////
                    String totalCoins = (String) dataSnapshot.child(coins).getValue();
                    int numbCoins = Integer.parseInt(totalCoins);
                    numbCoins -= coinsPiggyNumb1;
                    dataSnapshot.getRef().child(coins).setValue(String.valueOf(numbCoins));

                    /////////         PIGGY COINS        //////////
                    int numbPiggyCoins = 0;
                    String totalPiggyCoins = (String) dataSnapshot.child(coinsPiggy).getValue();
                    if (totalPiggyCoins != null) {
                        numbPiggyCoins = Integer.parseInt(totalPiggyCoins);
                    }
                    numbPiggyCoins += coinsPiggyNumb2;
                    if (numbPiggyCoins > 0) {
                        dataSnapshot.getRef().child(coinsPiggy).setValue(String.valueOf(numbPiggyCoins));
                    } else if (numbPiggyCoins == 0) {
                        dataSnapshot.getRef().child(coinsPiggy).setValue(String.valueOf(coinsPiggyNumb1));
                    }
                  //  android.util.Log.e("TAG", "DeviceCheck: EXIST");
                } else {
                    dataSnapshot.getRef().child(coinsPiggy).setValue(String.valueOf(coinsPiggyNumb1));
                 //   android.util.Log.e("TAG", "DeviceCheck: EXIST");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
             //   android.util.Log.e("TAG", "DeviceCheck: ERROR");
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    private void insertCoins(int coinsNumb1) {
        String idLogin = main.prefs.getLoginData("id");
        String idAccount = main.prefs.getFamilyData("id");
        String coins = "coins";
        String coinsPiggy = "coinsPiggy";
        final int coinsNumb2 = coinsNumb1;
        DatabaseReference dbr = main.presenter.model.getChildDataDBR();

        Query query = dbr.child(idLogin).child(idAccount);
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    /////////      PIGGY COINS      /////////////
                    String totalCoins = (String) dataSnapshot.child(coinsPiggy).getValue();
                    int numbCoins = Integer.parseInt(totalCoins);
                    numbCoins -= coinsNumb1;
                    dataSnapshot.getRef().child(coinsPiggy).setValue(String.valueOf(numbCoins));

                    /////////          COINS        //////////
                    String totalPiggyCoins = (String) dataSnapshot.child(coins).getValue();
                    int numbPiggyCoins = Integer.parseInt(totalPiggyCoins);
                    numbPiggyCoins += coinsNumb2;
                    if (numbPiggyCoins > 0) {
                        dataSnapshot.getRef().child(coins).setValue(String.valueOf(numbPiggyCoins));
                    } else if (numbPiggyCoins == 0) {
                        dataSnapshot.getRef().child(coins).setValue(String.valueOf(coinsNumb1));
                    }
                    //  android.util.Log.e("TAG", "DeviceCheck: EXIST");
                } else {
                    dataSnapshot.getRef().child(coins).setValue(String.valueOf(coinsNumb1));
                    //   android.util.Log.e("TAG", "DeviceCheck: EXIST");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                //   android.util.Log.e("TAG", "DeviceCheck: ERROR");
            }
        };
        query.addListenerForSingleValueEvent(valueEventListener);
    }
    private final class PiggyChoiceTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {

                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);

                return true;
            }
            else {
                return false;
            }
        }
    }
    private class PiggyChoiceDragListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view - asta de deasupra
                    //handle the dragged view being dropped over a target view -asta de jos
                    View vImage = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                //    vImage.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    ImageView dropTarget = (ImageView) v;
                    ViewGroup owner = (ViewGroup) v.getParent();
                    owner.removeView(vImage);


                    //view being dragged and dropped
                    ImageView dropped = (ImageView) vImage;
                    //update the image in the target view to reflect the data being dropped
                    //    dropTarget.setImageDrawable(dropped.getDrawable());
                    piggyAdapter.updateData(20);
                    view.updateDataCoinsMinus(20, numbMinute, main.iRes.getIRes());
                    insertPiggyCoins(20);
                    coinsAdapter.deleteData();

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    }
    private final class CoinsChoiceTouchListener implements View.OnTouchListener{
        public boolean onTouch(View view, MotionEvent motionEvent){
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                //setup drag
                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                //start dragging the item touched
                view.startDrag(data, shadowBuilder, view, 0);
                return true;
            }
            else {
                return false;
            }
        }
    }
    private class CoinsChoiceDragListener implements View.OnDragListener{
        @Override
        public boolean onDrag(View v, DragEvent event) {
            //handle drag events
            switch (event.getAction()) {
                case DragEvent.ACTION_DRAG_STARTED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_ENTERED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DRAG_EXITED:
                    //no action necessary
                    break;
                case DragEvent.ACTION_DROP:
                    //handle the dragged view being dropped over a drop view - asta de deasupra
                    //handle the dragged view being dropped over a target view -asta de jos
                    View vImage = (View) event.getLocalState();
                    //stop displaying the view where it was before it was dragged
                //    vImage.setVisibility(View.INVISIBLE);
                    //view dragged item is being dropped on
                    CardView dropTarget = (CardView) v;
                    //view being dragged and dropped

                    ViewGroup owner = (ViewGroup) v.getParent();
                    owner.removeView(vImage);

                    ImageView dropped = (ImageView) vImage;

                    dropped.setVisibility(View.VISIBLE);
                    //update the image in the target view to reflect the data being dropped
                    //    dropTarget.setImageDrawable(dropped.getDrawable());
               //     piggyAdapter.updateData(20);
                 //   view.updateDataCoins(20, numbMinute, main.getResLanguage());
                  //  insetPiggyCoins(20);
                    piggyAdapter.deleteData();
                    coinsAdapter.updateData();
                    view.updateDataCoinsPlus(20, numbMinute, main.iRes.getIRes());
                    insertCoins(20);
                //    view.getCoins().invalidate();

                    break;
                case DragEvent.ACTION_DRAG_ENDED:
                    //no action necessary
                    break;
                default:
                    break;
            }

            return true;
        }
    }



    class MyOnLongClickListener implements View.OnLongClickListener {


        public boolean onLongClick(View v) {
          //  listener1.setVisibility(View.VISIBLE);
           // listener2.setVisibility(View.VISIBLE);
            view.onCoinsToBankOrPiggyOn();


            ClipData clipData = ClipData.newPlainText("Image", "Picture");
// Default shadow
//DragShadowBuilder shadowBuilder = new DragShadowBuilder(v);
// Custom Shadow
            View.DragShadowBuilder shadowBuilder = new DrawableDragShadowBuilder(v,  getResources().getDrawable(R.mipmap.coins_gold));

        //    getResources().getDrawable(R.mipmap.coins_gold);
          //  shadowBuilder.getView().setBackgroundColor(Color.RED);



        //    View.DragShadowBuilder shadowBuilder = new MyDragShadow(v);

          //  ImageView image = (ImageView)v.getParent();
       //     image.setImageResource(R.mipmap.coins_gold);
            //      v.setBackgroundDrawable(image.getDrawable());


            v.startDrag(clipData, shadowBuilder, v, 0);

            Log.i("","Drag the shadow");

            return true;
        }


    }
    class DrawableDragShadowBuilder extends View.DragShadowBuilder {
        private Drawable mDrawable;

        public DrawableDragShadowBuilder(View view, Drawable drawable) {
            super(view);
            // Set the drawable and apply a green filter to it
            mDrawable = drawable;
          //  mDrawable.setColorFilter(new PorterDuffColorFilter(Color.GREEN, PorterDuff.Mode.MULTIPLY));
        }

        @Override
        public void onProvideShadowMetrics(Point shadowSize, Point touchPoint) {
            // Fill in the size
            shadowSize.x = mDrawable.getIntrinsicWidth();
            shadowSize.y = mDrawable.getIntrinsicHeight();
            // Fill in the location of the shadow relative to the touch.
            // Here we center the shadow under the finger.
            touchPoint.x = mDrawable.getIntrinsicWidth() / 2;
            touchPoint.y = mDrawable.getIntrinsicHeight() / 2;

            mDrawable.setBounds(new Rect(0, 0, shadowSize.x, shadowSize.y));
        }

        @Override
        public void onDrawShadow(Canvas canvas) {
            //Draw the shadow view onto the provided canvas
            mDrawable.draw(canvas);
        }
    }
    class MyDragListener implements View.OnDragListener {
        private boolean iGot;
        @SuppressWarnings("deprecation")
        public boolean onDrag(View v, DragEvent event) {
            int dragAction = event.getAction();
            switch (dragAction) {
                case DragEvent.ACTION_DRAG_STARTED:
                  //  v.setBackgroundColor(Color.GREEN);
                    return true;
                case DragEvent.ACTION_DRAG_ENTERED:
               //     v.setBackgroundColor(Color.BLUE);
                    return true;
                case DragEvent.ACTION_DRAG_LOCATION:
                    Log.i("TAG", "Location: "+event.getX()+":"+event.getY());
                    return true;
                case DragEvent.ACTION_DRAG_EXITED:
                  //  v.setBackgroundColor(Color.YELLOW);
                    return true;
                case DragEvent.ACTION_DROP:
              //      ImageView image = (ImageView)event.getLocalState();
              //      v.setBackgroundDrawable(image.getDrawable());
              //      iGot=true;

                 //   ClipData clipData= event.getClipData();
                //    ClipData.Item item = clipData.getItemAt(0);
                //    String str= (String) item.getText();
                  //  Log.i(TAG,"Item Text :"+str);
                    view.onCoinsToBankOrPiggyOff();
                    piggyAdapter.updateData(20);
                    view.updateDataCoinsMinus(20, numbMinute, main.iRes.getIRes());
                    insertPiggyCoins(20);
                    coinsAdapter.deleteData();


                    return true;
                case DragEvent.ACTION_DRAG_ENDED:
                    if(!iGot)
                        v.setBackgroundColor(Color.WHITE);
                    if(event.getResult())
                        Log.i("","Dropped Successfully");
                    else
                        Log.i("","Drop not Successful");


                    view.onCoinsToBankOrPiggyOff();
                    return true;
            }
            return false;
        }


    }




    @Override
    public void onDestroy() {
        super.onDestroy();
        if (disChildDataUpdate != null && !disChildDataUpdate.isDisposed()) {
            disChildDataUpdate.dispose();
        }
        if (main != null) {
            main = null;
        }
        if (view != null) {
            view = null;
        }
    }
}



package kz.tech.esparta.childs;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Build;
import android.widget.CompoundButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.AppCompatCheckBox;

import java.util.Arrays;
import java.util.List;

import kz.tech.esparta.R;

public class MyUtils {
    private static String SKIP_INTENT_CHECK = "skipAppListMessage";

    private static List<Intent> POWERMANAGER_INTENTS = Arrays.asList(
        new Intent().setComponent(new ComponentName("com.miui.securitycenter", "com.miui.permcenter.autostart.AutoStartManagementActivity")),
            new Intent().setComponent(new ComponentName("com.letv.android.letvsafe", "com.letv.android.letvsafe.AutobootManageActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.appcontrol.activity.StartupAppControlActivity")),
            new Intent().setComponent(new ComponentName("com.huawei.systemmanager", "com.huawei.systemmanager.optimize.process.ProtectActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.coloros.safecenter", "com.coloros.safecenter.startupapp.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.oppo.safe", "com.oppo.safe.permission.startup.StartupAppListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.AddWhiteListActivity")),
            new Intent().setComponent(new ComponentName("com.iqoo.secure", "com.iqoo.secure.ui.phoneoptimize.BgStartUpManager")),
            new Intent().setComponent(new ComponentName("com.vivo.permissionmanager", "com.vivo.permissionmanager.activity.BgStartUpManagerActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity")),
            new Intent().setComponent(new ComponentName("com.htc.pitroad", "com.htc.pitroad.landingpage.activity.LandingPageActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.autostart.AutoStartActivity")),
            new Intent().setComponent(new ComponentName("com.asus.mobilemanager", "com.asus.mobilemanager.entry.FunctionActivity")).setData(android.net.Uri.parse("mobilemanager://function/entry/AutoStart")),
            new Intent().setComponent(new ComponentName("com.dewav.dwappmanager", "com.dewav.dwappmanager.memory.SmartClearupWhiteList")),


            new Intent().setComponent(new ComponentName("kz.tech.esparta", "kz.tech.esparta.root.MainActivity")),
            new Intent().setComponent(new ComponentName("com.samsung.android.lool", "com.samsung.android.sm.ui.battery.BatteryActivity"))



    );



    public static void StartPowerSaverIntent(Context context)
    {
        SharedPreferences settings = context.getSharedPreferences("ProtectedApps", Context.MODE_PRIVATE);
        boolean skipMessage = settings.getBoolean(SKIP_INTENT_CHECK, false);
        if (!skipMessage)
        {
            boolean HasIntent = false;
            SharedPreferences.Editor editor = settings.edit();
            for (Intent intent : POWERMANAGER_INTENTS) {
              //  if (context.PackageManager.ResolveActivity(intent, PackageInfoFlags.MatchDefaultOnly) != null) {
                if (isCallable(context, intent)) {

                        final AppCompatCheckBox dontShowAgain = new AppCompatCheckBox(context);
                        dontShowAgain.setText("Do not show again");
                        dontShowAgain.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                            @Override
                            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                editor.putBoolean(SKIP_INTENT_CHECK, isChecked);
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


                    HasIntent = true;

                    break;
                }
            }

            if (!HasIntent)
            {
                editor.putBoolean(SKIP_INTENT_CHECK, true);
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



package kz.tech.esparta.childs;


import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/** * Custom Application which can detect application state of whether it enter * background or enter foreground. * * @reference http://www.vardhan-justlikethat.blogspot.sg/2014/02/android-solution-to-detect-when-android.html */
    public abstract class QQQ { /* extends Application implements Application.ActivityLifecycleCallbacks {
        public static final int STATE_UNKNOWN = 0x00;
        public static final int STATE_CREATED = 0x01;
        public static final int STATE_STARTED = 0x02;
        public static final int STATE_RESUMED = 0x03;
        public static final int STATE_PAUSED = 0x04;
        public static final int STATE_STOPPED = 0x05;
        public static final int STATE_DESTROYED = 0x06;
        private static final int FLAG_STATE_FOREGROUND = -1;
        private static final int FLAG_STATE_BACKGROUND = -2;
        private int mCurrentState = STATE_UNKNOWN;
        private int mStateFlag = FLAG_STATE_BACKGROUND;
        @Override
        public void onCreate() {
            super.onCreate();
            mCurrentState = STATE_UNKNOWN; registerActivityLifecycleCallbacks(this);
        }


    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
         mCurrentState = STATE_CREATED; }
        @Override
        public void onActivityStarted(Activity activity) {
        if (mCurrentState == STATE_UNKNOWN || mCurrentState == STATE_STOPPED) {
            if (mStateFlag == FLAG_STATE_BACKGROUND) { applicationWillEnterForeground();
            mStateFlag = FLAG_STATE_FOREGROUND; } } mCurrentState = STATE_STARTED;
    }
    @Override
    public void onActivityResumed(Activity activity) {
        mCurrentState = STATE_RESUMED;
    } @Override public void onActivityPaused(Activity activity) {
        mCurrentState = STATE_PAUSED;
    }
    @Override public void onActivityStopped(Activity activity) {
        mCurrentState = STATE_STOPPED;
    }
    @Override public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    } @Override
    public void onActivityDestroyed(Activity activity) {
        mCurrentState = STATE_DESTROYED;
    } @Override public void onTrimMemory(int level) {
        super.onTrimMemory(level);
        if (mCurrentState == STATE_STOPPED && level >= TRIM_MEMORY_UI_HIDDEN) {
            if (mStateFlag == FLAG_STATE_FOREGROUND) { applicationDidEnterBackground();
            mStateFlag = FLAG_STATE_BACKGROUND;
            }
        }else if (mCurrentState == STATE_DESTROYED && level >= TRIM_MEMORY_UI_HIDDEN) {
            if (mStateFlag == FLAG_STATE_FOREGROUND) {
                applicationDidDestroyed(); mStateFlag = FLAG_STATE_BACKGROUND;
            }
        }
    } /** * The method be called when the application been destroyed. But when the * device screen off,this method will not invoked. */
   // protected abstract void applicationDidDestroyed(); /** * The method be called when the application enter background. But when the * device screen off,this method will not invoked. */
  //  protected abstract void applicationDidEnterBackground(); /** * The method be called when the application enter foreground. */
  //  protected abstract void applicationWillEnterForeground();
  //  }*/

}
