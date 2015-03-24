package com.zxon.angyourlocker;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by leon on 15/3/23.
 */
public class AngApplication extends Application {
    private static Context mContext;
    public static boolean STATE = false;
    public static String homePkgName = null;
    public static String homeActName = null;
    @Override
    public void onCreate() {
        mContext = getApplicationContext();
        Intent intent = new Intent();
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setAction(Intent.ACTION_MAIN);

        List<ResolveInfo> resolveInfos = mContext.getPackageManager().queryIntentActivities(intent, PackageManager.MATCH_DEFAULT_ONLY);
        for (int i = 0; i < resolveInfos.size(); i++) {
            String packageName = resolveInfos.get(i).activityInfo.packageName;
            String activityName = resolveInfos.get(i).activityInfo.name;
            if (!packageName.equals(mContext.getPackageName())) {//排除自己的包名　　　　　　
                homePkgName = packageName;
                homeActName = activityName;
            }
        }

    }

    public static Context getmContext() {
        return mContext;
    }

}
