package com.ydh.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ydh.network.HttpHelper;
import com.ydh.network.call.HttpCallback;
import com.ydh.okhttp.bean.BeanPhoto;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HttpHelper.obtain().post("http://c.3g.163.com/photo/api/set/0001%2F2250173.json", null, new HttpCallback<BeanPhoto>() {
                    @Override
                    public void onFailure(String e) {
                        Log.e("zxy", "onFailure: " + e);
                    }

                    @Override
                    public void onSuccess(BeanPhoto beanPhoto) {
                        Log.e("zxy", "onSuccess: " + beanPhoto.toString());
                    }
                });
            }
        });

    }


}
