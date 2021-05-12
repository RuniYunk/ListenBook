package com.slg.android.listenbook.net.http;

import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 10:46
 */
public interface JsonCallBack {
    void onSuccess(ResponseInfo<String> responseInfo, int requestCode);

    void onFailure(HttpException error, String msg, int requestCode);

    void onStart(int requestCode);

    void onCancelled(int requestCode);

    void onLoading(long total, long current, boolean isUploading, int requestCode);

}
