package com.ydh.network.call;

import android.text.TextUtils;

import java.io.IOException;
import java.net.ConnectException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * 接口请求回调同一处理
 *
 * @author 13001
 */
public class TsCallback implements Callback {

    private ICallback callback = null;

    public TsCallback(ICallback callback) {
        this.callback = callback;
    }

    @Override
    public void onFailure(Call call, IOException e) {
        if (callback == null) {
            return;
        }
        onFailures(e);
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (callback == null) {
            return;
        }
        onSuccess(response);
    }

    /**
     * 成功回调处理
     *
     * @param response 请求结果
     */
    private void onSuccess(Response response) {
        if (response.isSuccessful()) {
            String str = null;
            try {
                str = response.body().string();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (TextUtils.isEmpty(str)) {
                onFailures("接口访问失败");
            } else {
                callback.onSuccess(str);
            }
        } else {
            onFailures("接口访问失败");
        }
    }

    /**
     * 错误信息处理
     *
     * @param error
     */
    private void onFailures(String error) {
        callback.onFailure(error);
    }

    /**
     * 错误信息处理
     *
     * @param e
     */
    private void onFailures(IOException e) {
        if (e instanceof ConnectException) {
            callback.onFailure("接口请求失败，请检查网络连接");
        } else {
            callback.onFailure("接口访问失败");
        }
    }

}
