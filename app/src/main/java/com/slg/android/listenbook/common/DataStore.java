package com.slg.android.listenbook.common;

import android.content.Context;
import android.os.Environment;
import android.telephony.TelephonyManager;

import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.HttpUtils;
import com.slg.android.listenbook.configuration.BuildConfig;

import java.io.File;

/**
 * Created by E540 on 2015/3/23.
 */
public class DataStore implements BuildConfig {
    private static DataStore sDataStore;

    private BitmapUtils mBitmapUtils;
    private HttpUtils mHttpUtils;
    private DbUtils mDbUtils;
    private String mIMEI;

    private DataStore(Context context) {
        mDbUtils = DbUtils.create(context);
        mDbUtils.configDebug(DEBUG);
        mHttpUtils = new HttpUtils();
        mHttpUtils = new HttpUtils();
        TelephonyManager telephonyManager =
                (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        mIMEI = telephonyManager.getDeviceId();
        File diskCacheDir = null;
        if (context != null) {
            //上下文有，那么进行存储设置
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                diskCacheDir = context.getExternalCacheDir();
            } else {
                diskCacheDir = context.getCacheDir();
            }
        } else {
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                File dir = Environment.getExternalStorageDirectory();
                diskCacheDir = new File(dir, "ListenBook");
            }
        }
        if (diskCacheDir != null) {
            final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 8);
            mBitmapUtils = new BitmapUtils(context, diskCacheDir.getAbsolutePath(), maxMemory
                    , DiskCacheSize);
        } else {
            mBitmapUtils = new BitmapUtils(context);
        }

    }

    public static void createInstance(Context context) {
        sDataStore = new DataStore(context);

    }


    //  getter && setter
    public static DataStore getInstance() {
        return sDataStore;
    }

    public String getIMEI() {
        return mIMEI;
    }

    public BitmapUtils getBitmapUtils() {
        return mBitmapUtils;
    }

    public HttpUtils getHttpUtils() {
        return mHttpUtils;
    }

    public DbUtils getDbUtils() {
        return mDbUtils;
    }
}
