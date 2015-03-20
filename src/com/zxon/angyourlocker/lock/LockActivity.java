
package com.zxon.angyourlocker.lock;

import com.zxon.angyourlocker.R;
import com.zxon.angyourlocker.R.layout;
import com.zxon.angyourlocker.receiver.BootReceiver;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class LockActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        
        setContentView(R.layout.activity_lock);
        
        
    }
    
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
}
