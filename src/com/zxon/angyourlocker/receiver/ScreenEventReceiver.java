package com.zxon.angyourlocker.receiver;

import android.app.KeyguardManager;
import com.zxon.angyourlocker.LogUtil;
import com.zxon.angyourlocker.lock.HomeLocker;
import com.zxon.angyourlocker.lock.LockActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenEventReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF) || intent.getAction().equals(Intent.ACTION_SCREEN_ON) || intent.getAction().equals(Intent.ACTION_USER_PRESENT)){
            LogUtil.d("--- start LockActivity ---");

            //解锁
            KeyguardManager km = (KeyguardManager)context.getSystemService(Context.KEYGUARD_SERVICE);
            KeyguardManager.KeyguardLock kk = km.newKeyguardLock("");
            kk.disableKeyguard();

            Intent i = new Intent(context, LockActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        }
    }
}
