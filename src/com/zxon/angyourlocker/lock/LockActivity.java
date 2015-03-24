
package com.zxon.angyourlocker.lock;

import android.annotation.SuppressLint;
import android.app.KeyguardManager;
import android.content.Intent;
import android.os.Build;
import android.view.*;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import com.zxon.angyourlocker.AngApplication;
import com.zxon.angyourlocker.LogUtil;
import com.zxon.angyourlocker.R;
import com.zxon.angyourlocker.R.layout;
import com.zxon.angyourlocker.receiver.BootReceiver;

import android.app.Activity;
import android.os.Bundle;
import com.zxon.angyourlocker.service.LockService;

public class LockActivity extends Activity {

    HomeLocker mHomeLocker;
    KeyguardManager keyguardManager;

    public static RelativeLayout lockScreenRoot;

    public static boolean STATE = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LogUtil.d("*** the LockActivity is launched ***");

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        setContentView(R.layout.activity_lock);

        AngApplication.STATE = true;

        try {
            fullScreenCall();
        } catch (Exception e) {
            LogUtil.d("full_screen_call failed");
        }

        mHomeLocker = new HomeLocker();
        mHomeLocker.lock(this);

        lockScreenRoot = (RelativeLayout) findViewById(R.id.look_screen_root);

        lockScreenRoot.setDrawingCacheEnabled(true);
        lockScreenRoot.buildDrawingCache();

//        LockService.addHomeLockView();

//        keyguardManager = (KeyguardManager) this.getSystemService(this.KEYGUARD_SERVICE);
//        keyguardLock = keyguardManager.newKeyguardLock("");
//        keyguardLock.disableKeyguard();//解锁系统锁屏
//        startActivity(toMainIntent);//跳转到主界面

    }

    @Override
    public void onResume(){
        super.onResume();
    }

    @SuppressLint("NewApi")
    public void fullScreenCall() {

        if (Build.VERSION.SDK_INT >= 19) { // 19 or above api
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);

        } else {
            // for lower api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_FULLSCREEN;

            decorView.setSystemUiVisibility(uiOptions);
        }
    }

    @Override
    protected void onDestroy(){

        if (mHomeLocker != null) {
            mHomeLocker.unlock();
            mHomeLocker = null;
        }

        super.onDestroy();
    }

    public void unlock(View view){

        LogUtil.d("*** unlock ***");
        mHomeLocker.unlock();
        AngApplication.STATE = false;
        finish();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        LogUtil.d("***** the keycode is  *****");
        LogUtil.d(keyCode + "");
        LogUtil.d("***************************");
        return keyCode == KeyEvent.KEYCODE_HOME
                || keyCode == KeyEvent.KEYCODE_BACK;
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event){
        LogUtil.d("************************");
        LogUtil.d(event.getKeyCode() + "");
        LogUtil.d("************************");
        return false;
    }

    @Override
    public void onPause() {
        super.onPause();
        return;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        return;
    }

    @Override
    public void onStop() {
        super.onStop();
        return;
    }


}
