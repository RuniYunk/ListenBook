package com.slg.android.listenbook.net.http;

import android.text.TextUtils;
import android.util.Log;
import com.lidroid.xutils.http.RequestParams;

/**
 * User: Giotto
 * Date: 2015-03-23
 * Time: 11:16
 */
public class HttpParamBuilder {

    private static final String TAG = "HTTP PARAM";

    public static String buildUrlWithParams(String url, String[][] parameters) {
        if (parameters != null) {
            StringBuilder stringBuilder = new StringBuilder(url);
            for (String[] strs : parameters) {

                //check len
                if (strs.length != 2) {
                    Log.w(TAG, "you has passed a short wrong url parameter!");
                    continue;
                }

                //assign
                final String str1 = strs[0];
                final String str2 = strs[1];

                if (TextUtils.isEmpty(str1)) {
                    continue;
                } else {
                    if (!url.endsWith("?") && !url.contains("&")) {
                        stringBuilder.append("?");
                        stringBuilder.append(str1 + "=" + str2);
                    } else {
                        stringBuilder.append("&" + str1 + "=" + str2);
                    }
                }

            }
            //re-assign
            url = stringBuilder.toString();
        }
        return url;
    }

    public static RequestParams buildPostParams(String[][] parameters) {
        RequestParams params = new RequestParams();
        if (parameters != null) {
            for (String[] strs : parameters) {
                //check len
                if (strs.length != 2) {
                    Log.w(TAG, "you has passed a short wrong url parameter!");
                    continue;
                }

                //assign
                final String str1 = strs[0];
                final String str2 = strs[1];
                if (str1 == null || str2 == null || TextUtils.isEmpty(str1.toString())) continue;

                params.addBodyParameter(str1, str2);
            }
        }
        return params;
    }
}
