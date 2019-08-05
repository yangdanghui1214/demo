package com.ydh.network;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ydh.lib_retrofit3.RetrofitUtils;
import com.ydh.lib_retrofit3.exception.AiThrowable;
import com.ydh.lib_retrofit3.retrofit.subscriber.BaseObserver;
import com.ydh.network.databinding.ActivityMainBinding;
import com.ydh.network.model.DeviceLoginModel;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    private String token = "";
    private BaseApiService repo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);


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
                    Log.e("zxy", "onNext: " + deviceLoginModel.toString());
                }

                @Override
                public void onError(Throwable e) {
                    Log.e("zxy", "onNext: " + e.getMessage());
                }

                @Override
                public void onComplete() {

                }
            });
        });

    }


    /**
     * 请求访问quest和response拦截器
     */
    private static Interceptor mLogInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long startTime = System.currentTimeMillis();
            okhttp3.Response response = chain.proceed(chain.request());
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            Log.e("zxy", "----amos------Request Start----------------");
            Log.e("zxy", "amos_| " + request.toString());
            Log.e("zxy", "amos_| Response:" + content);
            Log.e("zxy", "----amos------Request End:" + duration + "毫秒----------");
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    };

    class RequestHeader implements Interceptor {

        @Override
        public Response intercept(@NonNull Chain chain) {
            //原始请求
            Request originalRequest = chain.request();
            Response response = null;

            Log.e("zxy", "intercept: token  " + token);
            // 添加请求头accessToken
            Request addTokenRequest = originalRequest.newBuilder()
                    .addHeader("accessToken", token)
                    .build();

            try {
                response = chain.proceed(addTokenRequest);
                if (response.code() == 200) {
                    MediaType mediaType = response.body().contentType();

                    //切记response.body只能调用一次,因为第二次调用的时候直接抛异常, 详细的可以查看源码
                    String data = response.body().string();
                    JsonObject jsonObject = (JsonObject) new JsonParser().parse(data);
                    int code = jsonObject.get("code").getAsInt();
                    //这个是与后台约定好的token失效的code
                    if (code == 8888) {
                        Request addTokenRequest2 = originalRequest.newBuilder()
                                .addHeader("accessToken", token)
                                .build();
                        return chain.proceed(addTokenRequest2);
                    }
                    //因为调用response.body().string, response就会为null
                    return response.newBuilder().body(ResponseBody.create(mediaType, data)).build();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return response;
        }
    }
}
