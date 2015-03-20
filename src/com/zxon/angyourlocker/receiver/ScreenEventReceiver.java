package com.zxon.angyourlocker.receiver;

import com.zxon.angyourlocker.lock.LockActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class ScreenEventReceiver extends BroadcastReceiver{
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent i = new Intent(context, LockActivity.class);
        context.startActivity(i);
    }
}
