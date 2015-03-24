package com.zxon.angyourlocker;

import android.app.Activity;
import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import com.zxon.angyourlocker.service.LockService;

public class MainActivity extends Activity {

    public static DevicePolicyManager policyManager;
    public static ComponentName componentName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.d("*** MainActivity onCreate ***");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        setContentView(R.layout.activity_main);
//
//        Button lockNowBtn = (Button) findViewById(R.id.lock_now_btn);
//
//        policyManager = (DevicePolicyManager) getSystemService(Context.DEVICE_POLICY_SERVICE);
//
//        componentName = new ComponentName(this, AdminReceiver.class);


        Intent i = new Intent(this, LockService.class);
        startService(i);

        if (AngApplication.STATE == false) {
            if(AngApplication.homePkgName != null && AngApplication.homePkgName != "") {
                ComponentName componentName = new ComponentName(AngApplication.homePkgName, AngApplication.homeActName);
                Intent intent = new Intent();
                intent.setComponent(componentName);

                if(intent != null) {
                    startActivity(intent);
                } else {
                    LogUtil.d("*** intent is still null ***");
                }
            }
            finish();
        } else {

        }
    }

    @Override
    protected void onResume() {

        LogUtil.d("*** MainActivity onResume ***");


        super.onResume();
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        if (resultCode == Activity.RESULT_OK) {
//            lockNow();
//        } else {
//            startAddDeviceAdminActivity();
//        }
//
//        super.onActivityResult(requestCode, resultCode, data);
//    }

//    public void lockNowBtn(View view) {
//        boolean ifActive = policyManager.isAdminActive(componentName);
//        if (!ifActive) {
//            //startAddDeviceAdminActivity();
//        } else {
//            //lockNow();
//        }
//    }
//
//    public void lockNow() {
//        policyManager.lockNow();
//
//        Intent i = new Intent(MainActivity.this, LockActivity.class);
//        startActivity(i);
//        finish();
//    }
//
//    private void startAddDeviceAdminActivity() {
//        Intent i = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
//        i.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, componentName);
//        i.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "注册此组件后才能拥有锁屏功能");
//
//        startActivityForResult(i, 0);
//    }
}
