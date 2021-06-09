package com.slg.android.listenbook.biz.home;

import android.content.ComponentName;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.RadioGroup;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.slg.android.listenbook.R;
import com.slg.android.listenbook.biz.bookcity.BookCityActivity;
import com.slg.android.listenbook.biz.home.fragment.BookShelfFragment;
import com.slg.android.listenbook.biz.player.receiver.PlayerReceiver;
import com.slg.android.listenbook.biz.player.service.PlayerService;
import com.slg.android.listenbook.common.FragmentMessageListener;
import com.slg.android.listenbook.configuration.Constants;

import static com.slg.android.listenbook.biz.home.fragment.BookShelfFragment.*;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 15:42
 */
public class MainActivity extends FragmentActivity implements ServiceConnection, RadioGroup.OnCheckedChangeListener, FragmentMessageListener {
    @ViewInject(R.id.main_menu_radioGroup)
    private RadioGroup mMenus;
    private FragmentManager mManager;
    private PlayerService.MusicController mMusicController;
    public static final String PLAY = "play";
    public static final String PAUSE = "pause";
    public static final String CONTINUE = "continue";
    public static final int PROGRESS = 10;
    private PlayerReceiver mReceiver;
    private MusicHandler mHandler;
    //FIXME FM完成后去掉该属性
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        ViewUtils.inject(this);
        initView();
        initData();
        addListener();
    }

    private class MusicHandler extends Handler {

        @Override
        public void handleMessage(Message msg) {
            Fragment fragment = mManager.findFragmentById(R.id.main_fragment_container);
            if (fragment instanceof BookShelfFragment) {
                ((BookShelfFragment) fragment).handleMessage(msg);
            }
        }
    }


    @OnClick({R.id.main_book_textView, R.id.main_menu_imageView})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_book_textView:
                startActivity(new Intent(this, BookCityActivity.class));
                break;

        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        unbindService(this);
        Intent service = new Intent(this, PlayerService.class);
        stopService(service);
        unregisterReceiver(mReceiver);
        super.onDestroy();
    }

    /**
     * 服务绑定
     *
     * @param name
     * @param service
     */
    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (service != null) {
            if (service instanceof PlayerService.MusicController) {
                mMusicController = (PlayerService.MusicController) service;
            }
        }
    }

    /**
     * 服务解绑
     *
     * @param name
     */
    @Override
    public void onServiceDisconnected(ComponentName name) {
        mMusicController = null;
    }

    /**
     * menu checkedChanged事件
     *
     * @param group
     * @param checkedId
     */
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

    }


    private void initData() {
        Intent service = new Intent(this, PlayerService.class);
        startService(service);
        bindService(service, this, BIND_AUTO_CREATE);
        IntentFilter intentFilter = new IntentFilter(Constants.ACTION_PLAYER_MESSAGE);
        mHandler = new MusicHandler();
        mReceiver = new PlayerReceiver(mHandler);
        registerReceiver(mReceiver, intentFilter);
    }

    private void initView() {
        mManager = getSupportFragmentManager();
        FragmentTransaction transaction = mManager.beginTransaction();
        BookShelfFragment fragment = new BookShelfFragment();
        transaction.replace(R.id.main_fragment_container, fragment);
        transaction.commit();


    }

    private void addListener() {
        mMenus.setOnCheckedChangeListener(this);
    }

    @Override
    public void getMessage(Intent intent) {
        String action = intent.getStringExtra("ACTION");
        if (TextUtils.equals(PLAY, action)) {
            String url = intent.getStringExtra("url");
            mMusicController.playUrl(url);
        } else if (TextUtils.equals(PAUSE, action)) {
            mMusicController.pause();
        } else if (TextUtils.equals(CONTINUE, action)) {
            //FIXME FM完成后改为mMusicController.play();
            if (TextUtils.isEmpty(url)) {
                url = "http://abv.cn/music/光辉岁月.mp3";
                mMusicController.playUrl(url);
            } else {
                mMusicController.play();
            }
            //mMusicController.play();
        }
    }
}
