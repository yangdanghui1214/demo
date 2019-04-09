package com.ydh.network;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.ydh.lib_retrofit3.RetrofitUtils;
import com.ydh.lib_retrofit3.lintener.CallBackLintener;
import com.ydh.network.databinding.ActivityMainBinding;
import com.ydh.network.model.DeviceLoginModel;

import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_main);


        HashMap map = new HashMap();
        map.put("deviceMac", "BO:F1:EC:32:95:9A");
//        map.put("page","1");
//        map.put("limit","10000");
//        map.put("updateTime","");

//        NetworkCommon.tokenName = "accessToken";
//        NetworkCommon.token = "v7UsbYiyCn+DPFf7PI57ewklpD79FdDr7MaB5IgY6aS69Lp0lIhJ+A7P65cZIcnp7JAqdu6Txtl4S3tYLfwdHU4hAgJhQil/MzE2L8F75T8=";
//        NetworkCommon.tokenCode = 8888;

//        NetworkUtils.getNetwork().post("user/login",map, new CallBack<DeviceLoginModel>() {
//            @Override
//            public void onSubscribe(DeviceLoginModel deviceLoginModel) {
//                Log.e("zxy", "onFailure:deviceLoginModel --  " + deviceLoginModel.toString());
//            }
//
//            @Override
//            public void onFailure(AiThrowable e) {
//                Log.e("zxy", "onFailure:失败");
//            }
//        });


        binding.bt.setOnClickListener(view -> {
//            RetrofitUtils.getInstance().post("user/login", map, new CallBackLintener() {
//                @Override
//                public void onSubscribe(String t) {
//                    DeviceLoginModel model=new Gson().fromJson(t,DeviceLoginModel.class);
//                    Log.e("zxy", "onSubscribe: "+  model.toString() );
//                }
//
//                @Override
//                public void onFailure(String e) {
//                    Log.e("zxy", "onFailure: "+e );
//                }
//            });
            Observable post = RetrofitUtils.getInstance().post("user/login", map, DeviceLoginModel.class);
            post.subscribe(new Observer<DeviceLoginModel>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(DeviceLoginModel deviceLoginModel) {
                    Log.e("zxy", "onNext: "+deviceLoginModel.toString() );
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("zxy", "onNext: "+e.getMessage() );
                }

                @Override
                public void onComplete() {

                }
            });
        });

    }
}
