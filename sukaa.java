  public static void startServiceFriendChat(Context context) {
        if (!isServiceFriendChatRunning(context)) {
            Intent myIntent = new Intent(context, FriendChatService.class);
            context.startService(myIntent);
        } else {
            if (connectionServiceFriendChatForStart != null) {
                context.unbindService(connectionServiceFriendChatForStart);
            }
            connectionServiceFriendChatForStart = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    FriendChatService.LocalBinder binder = (FriendChatService.LocalBinder) service;
                    for (Friend friend : binder.getService().listFriend.getListFriend()) {
                        binder.getService().mapMark.put(friend.idRoom, true);
                    }
                }

                @Override
                public void onServiceDisconnected(ComponentName arg0) {
                }
            };
            Intent intent = new Intent(context, FriendChatService.class);
            context.bindService(intent, connectionServiceFriendChatForStart, Context.BIND_NOT_FOREGROUND);
        }
    }
    
    
     public static void stopRoom(Context context, final String idRoom) {
        if (isServiceFriendChatRunning(context)) {
            Intent intent = new Intent(context, FriendChatService.class);
            if (connectionServiceFriendChatForDestroy != null) {
                context.unbindService(connectionServiceFriendChatForDestroy);
                connectionServiceFriendChatForDestroy = null;
            }
            connectionServiceFriendChatForDestroy = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    FriendChatService.LocalBinder binder = (FriendChatService.LocalBinder) service;
                    binder.getService().stopNotify(idRoom);
                }

                @Override
                public void onServiceDisconnected(ComponentName arg0) {
                }
            };
            context.bindService(intent, connectionServiceFriendChatForDestroy, Context.BIND_NOT_FOREGROUND);
        }
    }
    
    
    
     public static void stopServiceFriendChat(Context context, final boolean kill) {
        if (isServiceFriendChatRunning(context)) {
            Intent intent = new Intent(context, FriendChatService.class);
            if (connectionServiceFriendChatForDestroy != null) {
                context.unbindService(connectionServiceFriendChatForDestroy);
            }
            connectionServiceFriendChatForDestroy = new ServiceConnection() {
                @Override
                public void onServiceConnected(ComponentName className,
                                               IBinder service) {
                    FriendChatService.LocalBinder binder = (FriendChatService.LocalBinder) service;
                    binder.getService().stopSelf();
                }

                @Override
                public void onServiceDisconnected(ComponentName arg0) {
                }
            };
            context.bindService(intent, connectionServiceFriendChatForDestroy, Context.BIND_NOT_FOREGROUND);
        }
    }
    
    
      public static boolean isServiceFriendChatRunning(Context context) {
        Class<?> serviceClass = FriendChatService.class;
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (serviceClass.getName().equals(service.service.getClassName())) {
                return true;
            }
        }
        return false;
    }
