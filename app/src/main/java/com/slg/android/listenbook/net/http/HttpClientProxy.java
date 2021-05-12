package com.slg.android.listenbook.net.http;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 10:42
 */
public class HttpClientProxy {
    private String mUrl;

    public HttpClientProxy(String url) {
        mUrl = url;
    }

    public void doGet(String[][] parameters, JsonCallBack callBack, int requestCode) {
        new JsonHttpClient().doGet(mUrl, parameters, callBack, requestCode);
    }

    public void doPost(String[][] parameters, JsonCallBack callBack, int requestCode) {
        new JsonHttpClient().doPost(mUrl, parameters, callBack, requestCode);
    }

}
