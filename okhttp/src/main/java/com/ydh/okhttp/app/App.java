package com.ydh.okhttp.app;

import android.app.Application;

import com.ydh.network.HttpHelper;
import com.ydh.network.common.NetworkCommon;
import com.ydh.network.okHttp.OkHttpProcessor;
import com.ydh.network.retrofit.RetrofitProcessor;

/**
 * @author 13001
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        NetworkCommon.baseUrl = "https://checkout.tsignsv.cn/aiKindergartenTerminal/";
//        HttpHelper.init(OkHttpProcessor.getInstance());
        HttpHelper.init(RetrofitProcessor.getInstance());
    }
}
