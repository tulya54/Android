public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {

            Intent startServiceIntent = new Intent(context, FBTokenService.class);
            context.startService(startServiceIntent);

            Intent notificationServiceIntent = new Intent(context, FBNotificationService.class);
            context.startService(notificationServiceIntent);
        }
    }
}
