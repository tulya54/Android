<receiver
    android:name=".receivers.BootReceiver"
    android:enabled="true">
    <intent-filter>
       <action android:name="android.intent.action.BOOT_COMPLETED" />
    </intent-filter>
</receiver>
