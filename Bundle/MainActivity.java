 Bundle bundle = i.getExtras();
    if (bundle != null) {
        Set<String> keys = bundle.keySet();
        Iterator<String> it = keys.iterator();
        Log.e(LOG_TAG,"Dumping Intent start");
        while (it.hasNext()) {
            String key = it.next();
            Log.e(LOG_TAG,"[" + key + "=" + bundle.get(key)+"]");
        }
        Log.e(LOG_TAG,"Dumping Intent end");
    }
