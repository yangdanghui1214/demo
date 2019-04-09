package com.ydh.okhttp.app;

import android.app.Application;

import com.ydh.network.HttpHelper;
import com.ydh.network.okHttp.OkHttpProcessor;

/**
 * @author 13001
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        HttpHelper.init(OkHttpProcessor.getInstance());
    }
}
