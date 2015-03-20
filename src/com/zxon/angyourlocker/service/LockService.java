package com.zxon.angyourlocker.service;

import com.zxon.angyourlocker.LogUtil;
import com.zxon.angyourlocker.receiver.ScreenEventReceiver;

import android.app.Service;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;

public class LockService extends Service{

    public static ScreenEventReceiver RECEIVER;  
    public static boolean ifListenToScreenOff = false;
    
    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();
        RECEIVER = new ScreenEventReceiver();
        LogUtil.d("LockService onCreate");
    }
    
    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        LogUtil.d("LockService onStartCommand");
        
        if (ifListenToScreenOff == false) {
            ifListenToScreenOff = true;
            addScreenEventListener();
        }
        
        return super.onStartCommand(intent, flags, startId);
    }
    
    @Override
    public void onDestroy(){
        LogUtil.d("LockService onDestroy");
        super.onDestroy();
    }
    
    public void addScreenEventListener(){
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_SCREEN_OFF);
        registerReceiver(RECEIVER, intentFilter);
    }
}
