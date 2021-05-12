package com.slg.android.listenbook;

import android.app.Application;

import com.slg.android.listenbook.common.DataStore;

/**
 * Created by E540 on 2015/3/23.
 */
public class AppCtx extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataStore.createInstance(getApplicationContext());
    }
}
