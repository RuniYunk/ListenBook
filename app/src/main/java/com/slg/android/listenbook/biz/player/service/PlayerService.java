package com.slg.android.listenbook.biz.player.service;

import android.app.Service;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import com.slg.android.listenbook.configuration.Constants;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class PlayerService extends Service implements MediaPlayer.OnBufferingUpdateListener {
    private static final String TAG = "play service";
    private MediaPlayer mMediaPlayer;
    /**
     * 当前缓冲 0-100
     */
    private int mCurrentBuf;
    /**
     * 计时器  更新进度条
     */
    private Timer mTimer;
    private boolean running;
    // 计时器任务
    TimerTask mTask;

    public PlayerService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mMediaPlayer = new MediaPlayer();
        mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mMediaPlayer.setOnBufferingUpdateListener(this);
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                while (running) {
                    Intent intent = new Intent(Constants.ACTION_PLAYER_MESSAGE);
                    intent.putExtra("CurrentBuf", mCurrentBuf);
                    //Log.d(TAG,"CurrentPos="+mMediaPlayer.getCurrentPosition());
                    intent.putExtra("CurrentPos", mMediaPlayer.getCurrentPosition());
                    intent.putExtra("Duration", mMediaPlayer.getDuration());
                    sendBroadcast(intent);
                }
            }
        };
    }

    @Override
    public void onDestroy() {
        mMediaPlayer.stop();
        mMediaPlayer.release();
        running = false;
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MusicController();
    }

    /**
     * 监听缓冲
     *
     * @param mp
     * @param percent
     */
    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        mCurrentBuf = percent;
        //int currentPosition = mp.getCurrentPosition();
        //int duration = mp.getDuration();
        //double v = percent - (currentPosition + 0.0) / duration;

        //Log.d(TAG, "percent=" + percent);
    }

    /**
     * 播放器具体控制类
     */

    public class MusicController extends Binder {
        /**
         * @param url url地址
         */
        public void playUrl(String url) {
            try {
                mMediaPlayer.reset();
                mMediaPlayer.setDataSource(url); // 设置数据源
                mMediaPlayer.prepare(); // prepare自动播放
                mMediaPlayer.start();
                running = true;
                mTimer.schedule(mTask, 0, 1000);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        /**
         * 暂停播放
         */
        public void pause() {
            if (mMediaPlayer.isPlaying()) {
                mMediaPlayer.pause();
            }
        }

        /**
         * 继续播放
         */
        public void play() {
            mMediaPlayer.start();
        }

        public boolean isPlaying() {
            return mMediaPlayer.isPlaying();
        }

    }
}
