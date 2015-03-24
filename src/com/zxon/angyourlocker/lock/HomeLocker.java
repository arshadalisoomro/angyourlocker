package com.zxon.angyourlocker.lock;

/**
 * Created by leon on 15/3/20.
 */


import android.app.Activity;
import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.FrameLayout;
import com.zxon.angyourlocker.R;

import static android.view.WindowManager.LayoutParams.*;


public class HomeLocker {

    private OverlayDialog mOverlayDialog;


    public void lock(Activity activity) {

        if (mOverlayDialog == null) {

            mOverlayDialog = new OverlayDialog(activity);

            mOverlayDialog.show();

            mOverlayDialog.show();
        }
    }

    public void unlock() {

        if (mOverlayDialog != null) {
            mOverlayDialog.dismiss();
            mOverlayDialog = null;
        }
    }

    private static class OverlayDialog extends AlertDialog {

        public OverlayDialog(Activity activity) {

            super(activity, R.style.OverlayDialog);
//            super(activity);

            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.type = TYPE_SYSTEM_ALERT;
            params.dimAmount = 1.0F;
            params.width = 300;
            params.height = 300;
            params.gravity = Gravity.BOTTOM;
            getWindow().setAttributes(params);
            getWindow().setFlags(FLAG_SHOW_WHEN_LOCKED | FLAG_NOT_TOUCH_MODAL, 0xffffff);
            setOwnerActivity(activity);
            setCancelable(false);


        }

        public final boolean dispatchTouchEvent(MotionEvent motionevent) {
            return true;
        }

        protected final void onCreate(Bundle bundle) {

            super.onCreate(bundle);

            FrameLayout framelayout = new FrameLayout(getContext());
            framelayout.setBackgroundColor(Color.YELLOW);
            setContentView(framelayout);

        }


    }
}
