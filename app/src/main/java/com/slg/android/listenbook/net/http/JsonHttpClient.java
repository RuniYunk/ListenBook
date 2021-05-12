package com.slg.android.listenbook.net.http;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.slg.android.listenbook.common.DataStore;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 10:44
 */
public class JsonHttpClient {


    public void doGet(String url, String[][] parameters, final JsonCallBack callBack, final int requestCode) {
        HttpUtils httpUtils = DataStore.getInstance().getHttpUtils();
        url = HttpParamBuilder.buildUrlWithParams(url, parameters);
        httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (callBack != null) {
                    callBack.onSuccess(responseInfo, requestCode);
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (callBack != null) {
                    callBack.onFailure(error, msg, requestCode);
                }
            }

            @Override
            public void onStart() {
                if (callBack != null) {
                    callBack.onStart(requestCode);
                }
            }

            @Override
            public void onCancelled() {
                if (callBack != null) {
                    callBack.onCancelled(requestCode);
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (callBack != null) {
                    callBack.onLoading(total, current, isUploading, requestCode);
                }
            }
        });
    }

    public void doPost(String url, String[][] parameters, final JsonCallBack callBack, final int requestCode) {
        HttpUtils httpUtils = DataStore.getInstance().getHttpUtils();
        RequestParams params = HttpParamBuilder.buildPostParams(parameters);
        httpUtils.send(HttpRequest.HttpMethod.POST, url, params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                if (callBack != null) {
                    callBack.onSuccess(responseInfo, requestCode);
                }
            }

            @Override
            public void onFailure(HttpException error, String msg) {
                if (callBack != null) {
                    callBack.onFailure(error, msg, requestCode);
                }
            }

            @Override
            public void onStart() {
                if (callBack != null) {
                    callBack.onStart(requestCode);
                }
            }

            @Override
            public void onCancelled() {
                if (callBack != null) {
                    callBack.onCancelled(requestCode);
                }
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                if (callBack != null) {
                    callBack.onLoading(total, current, isUploading, requestCode);
                }
            }
        });
    }
}
