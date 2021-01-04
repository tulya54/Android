TelephonyManager tm =(TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
    //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
    String IMEINumber=tm.getDeviceId();

    /************************************************
     * **********************************************
     * This is just an icing on the cake
     * the following are other children of TELEPHONY_SERVICE
     *
     //Get Subscriber ID
     String subscriberID=tm.getDeviceId();

     //Get SIM Serial Number
     String SIMSerialNumber=tm.getSimSerialNumber();

     //Get Network Country ISO Code
     String networkCountryISO=tm.getNetworkCountryIso();

     //Get SIM Country ISO Code
     String SIMCountryISO=tm.getSimCountryIso();

     //Get the device software version
     String softwareVersion=tm.getDeviceSoftwareVersion()

     //Get the Voice mail number
     String voiceMailNumber=tm.getVoiceMailNumber();


     //Get the Phone Type CDMA/GSM/NONE
     int phoneType=tm.getPhoneType();

     switch (phoneType)
     {
     case (TelephonyManager.PHONE_TYPE_CDMA):
     // your code
     break;
     case (TelephonyManager.PHONE_TYPE_GSM)
     // your code
     break;
     case (TelephonyManager.PHONE_TYPE_NONE):
     // your code
     break;
     }

     //Find whether the Phone is in Roaming, returns true if in roaming
     boolean isRoaming=tm.isNetworkRoaming();
     if(isRoaming)
     phoneDetails+="\nIs In Roaming : "+"YES";
     else
     phoneDetails+="\nIs In Roaming : "+"NO";


     //Get the SIM state
     int SIMState=tm.getSimState();
     switch(SIMState)
     {
     case TelephonyManager.SIM_STATE_ABSENT :
     // your code
     break;
     case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
     // your code
     break;
     case TelephonyManager.SIM_STATE_PIN_REQUIRED :
     // your code
     break;
     case TelephonyManager.SIM_STATE_PUK_REQUIRED :
     // your code
     break;
     case TelephonyManager.SIM_STATE_READY :
     // your code
     break;
     case TelephonyManager.SIM_STATE_UNKNOWN :
     // your code
     break;

     }
