package com.slg.android.listenbook.biz.home.fragment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.slg.android.listenbook.R;
import com.slg.android.listenbook.biz.home.MainActivity;
import com.slg.android.listenbook.biz.player.receiver.PlayerReceiver;
import com.slg.android.listenbook.common.FragmentMessageListener;
import com.slg.android.listenbook.common.utils.TimeUtils;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 15:42
 */
public class BookShelfFragment extends Fragment implements Animation.AnimationListener {
    @ViewInject(R.id.shelf_player_progress)
    private ProgressBar mProgressBar;
    @ViewInject(R.id.shelf_player_time)
    private TextView mTime;
    @ViewInject(R.id.shelf_player_loading)
    private ImageView mLoading;
    @ViewInject(R.id.shelf_player_play)
    private ImageView mPlay;
    private FragmentMessageListener mListener;
    private Animation mLoadingAnim;
    private boolean playing;
    private boolean isCancel = true;
    private static final String TAG = "ShelfFragment";


    public BookShelfFragment() {
    }

    @OnClick({R.id.shelf_player_play})
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId) {
            case R.id.shelf_player_play:
                if (!playing) {
                    Intent intent = new Intent();
                    intent.putExtra("ACTION", MainActivity.CONTINUE);
                    mListener.getMessage(intent);
                    playing = true;
                    mPlay.setImageResource(R.drawable.btn_music_pause_normal);
                    mLoading.setVisibility(View.GONE);
                } else {
                    Intent intent = new Intent();
                    intent.putExtra("ACTION", MainActivity.PAUSE);
                    mListener.getMessage(intent);
                    mPlay.setImageResource(R.drawable.btn_music_play_normal);
                    playing = false;
                }
                break;
        }
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof FragmentMessageListener) {
            mListener = (FragmentMessageListener) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_shelf, container, false);
        ViewUtils.inject(this, view);
        return view;
    }


    public void handleMessage(Message msg) {
        switch (msg.what) {
            case MainActivity.PROGRESS:
                Intent intent = (Intent) msg.obj;
                //Log.d(TAG, " " + intent);
                updateProgress(intent);
                break;
        }
    }

    private void updateProgress(Intent intent) {
        int buf = intent.getIntExtra("CurrentBuf", 0);
        int pos = intent.getIntExtra("CurrentPos", 0);
        int duration = intent.getIntExtra("Duration", 0);
        //Log.d(TAG, "duration=" + duration);
        double v = buf - (pos + 0.0) / duration;
        //Log.d(TAG, "v=" + v + "buf=" + buf + "---" + (pos + 0.0) / duration);
        if (v <= 1) {//缓冲量过小时
            mLoading.setVisibility(View.VISIBLE);
            if(isCancel){
                mLoading.startAnimation(mLoadingAnim);
                isCancel = false;
            }
            mPlay.setImageResource(R.drawable.btn_music_play_normal);
        } else {
            if(!isCancel){
                mLoadingAnim.cancel();
                isCancel = true;
            }
            if(mLoading.getVisibility() == View.VISIBLE){
                mLoading.setVisibility(View.GONE);
            }
            if (playing) {
                mPlay.setImageResource(R.drawable.btn_music_pause_normal);
            }
            int max = mProgressBar.getMax();
            if (duration != 0) {
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mLoading.setVisibility(View.GONE);
                mProgressBar.setProgress((int) ((pos + 0.0) / duration * max));
                mProgressBar.setSecondaryProgress((int) ((buf + 0.0) / 100 * max));
                mTime.setText(TimeUtils.formatToMinute(pos) + "/" + TimeUtils.formatToMinute(duration));
            }
        }

    }


    private void initData() {
        mLoadingAnim = AnimationUtils.loadAnimation(getActivity(), R.anim.anim_loading);
        mLoadingAnim.setAnimationListener(this);
    }

    @Override
    public void onAnimationStart(Animation animation) {
        isCancel = false;
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        isCancel = true;
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
