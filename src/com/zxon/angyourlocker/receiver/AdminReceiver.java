package com.zxon.angyourlocker.receiver;

import android.app.admin.DeviceAdminReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class AdminReceiver extends DeviceAdminReceiver{

    @Override
    public void onDisabled(Context context, Intent intent) {
        Toast.makeText(context, "AngYourLocker Device Admin Disabled",  Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onEnabled(Context context, Intent intent) {
        Toast.makeText(context, "AngYourLocker Device Admin is now enabled", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("debug_ang", "MyDevicePolicyReciever Received: " + intent.getAction());
        super.onReceive(context, intent);
    }
}
