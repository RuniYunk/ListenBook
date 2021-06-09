package com.slg.android.listenbook.common.utils;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 18:07
 */
public class TimeUtils {

    public static String formatToMinute(long mill) {
        //获得总秒数
        long totalSeconds = mill / 1000;
        //获得显示的秒数
        long minute = totalSeconds / 60;
        //获得显示的分钟数
        long seconds = totalSeconds % 60;
        StringBuilder sb = new StringBuilder();
        if (minute < 10) {
            sb.append("0" + minute);
        } else {
            sb.append(minute);
        }
        sb.append(":");
        if (seconds < 10) {
            sb.append("0" + seconds);
        } else {
            sb.append(seconds);
        }
        return sb.toString();
    }
}
