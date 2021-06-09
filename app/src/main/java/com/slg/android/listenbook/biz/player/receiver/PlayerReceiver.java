package com.slg.android.listenbook.biz.player.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;

import com.slg.android.listenbook.biz.home.MainActivity;
import com.slg.android.listenbook.configuration.Constants;

public class PlayerReceiver extends BroadcastReceiver {
    private static final String TAG = "PlayerReceiver";
    private Handler mHandler;
    public PlayerReceiver(){
    }
    public PlayerReceiver(Handler handler) {
        mHandler = handler;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        //int pos = intent.getIntExtra("CurrentPos", 0);

        //Log.d(TAG, "pos=" + pos);
        if(TextUtils.equals(Constants.ACTION_PLAYER_MESSAGE,action)){
            Message message = mHandler.obtainMessage();
            message.obj = intent;
            message.what = MainActivity.PROGRESS;
            message.sendToTarget();
        }
    }
}
