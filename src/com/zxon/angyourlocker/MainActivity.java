
package com.zxon.angyourlocker;

import com.zxon.angyourlocker.lock.LockActivity;
import com.zxon.angyourlocker.receiver.AdminReceiver;
import com.zxon.angyourlocker.receiver.BootReceiver;
import com.zxon.angyourlocker.receiver.ScreenEventReceiver;
import com.zxon.angyourlocker.service.LockService;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

    public static DevicePolicyManager policyManager;
    public static ComponentName componentName;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button lockNowBtn = (Button) findViewById(R.id.lock_now_btn);

        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);

        componentName = new ComponentName(this, AdminReceiver.class);

        Intent i = new Intent(this, LockService.class);
        startService(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
            lockNow();
        } else {
            startAddDeviceAdminActivity();
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public void lockNowBtn(View view) {
        boolean ifActive = policyManager.isAdminActive(componentName);
        if (!ifActive) {
            startAddDeviceAdminActivity();
        } else {
            lockNow();
        }
    }

    public void lockNow() {
        policyManager.lockNow();
        
        Intent i = new Intent(MainActivity.this, LockActivity.class);
        startActivity(i);
        finish();
    }
    
    private void startAddDeviceAdminActivity() {
        Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
        i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "注册此组件后才能拥有锁屏功能");

        startActivityForResult(i, 0);
    }
}
