package com.ydh.network.app;

import android.app.Application;

import com.ydh.lib_retrofit3.RetrofitUtils;
import com.ydh.lib_retrofit3.common.NetCommon;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
//        NetworkCommon.baseUrl = "https://checkout.tsignsv.cn/aiKindergartenTerminal/";
//        NetworkUtils.getInstance(RetrofitUtil.getInstance());
        NetCommon.baseUrl = "https://checkout.tsignsv.cn/aiKindergartenTerminal/";
        RetrofitUtils.getInstance();
    }

}
