package com.ydh.okhttp;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.ydh.network.HttpHelper;
import com.ydh.network.call.HttpCallback;
import com.ydh.okhttp.bean.BeanPhoto;
import com.ydh.okhttp.bean.DeviceLoginModel;
import com.ydh.okhttp.databinding.ActivityMainBinding;
import com.ydh.okhttp.network.OkHttpNet;
import com.ydh.okhttp.util.TimeUtil;

import java.util.HashMap;

/**
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(view ->
                HttpHelper.obtain().post("http://c.3g.163.com/photo/api/set/0001%2F2250173.json", null, new HttpCallback<BeanPhoto>() {
                    @Override
                    public void onFailure(String e) {
                        Log.e("zxy", "onFailure: " + e);
                    }

                    @Override
                    public void onSuccess(BeanPhoto beanPhoto) {
                        Log.e("zxy", "onSuccess: " + beanPhoto.toString());
                    }
                }));

        binding.button2.setOnClickListener(view -> {
            if (TimeUtil.isFastDoubleClick()) {
                Log.e("zxy", "onClick: 成功");
            }
        });

        // 使用retrofit 请求
        binding.button3.setOnClickListener(view -> {
            HashMap map = new HashMap();
            map.put("deviceMac", "BO:F1:EC:32:95:9A");

            HttpHelper.obtain().post("user/login", map, new HttpCallback<DeviceLoginModel>() {
                @Override
                public void onFailure(String e) {
                    Log.e("zxy", "onFailure: " + e);
                }

                @Override
                public void onSuccess(DeviceLoginModel beanPhoto) {
                    Log.e("zxy", "onSuccess: " + beanPhoto.toString());
                }
            });
        });

        binding.okGetSey.setOnClickListener(view -> {
            OkHttpNet.getIn().synRequest();
        });

        binding.okGetAsy.setOnClickListener(view -> {
            OkHttpNet.getIn().asyRequest();
        });

        binding.okPostStr.setOnClickListener(view -> {
            OkHttpNet.getIn().postStringRequest();
        });

        binding.okPostFile.setOnClickListener(view -> {
            OkHttpNet.getIn().postFileRequest();
        });

        binding.okPostForm.setOnClickListener(view -> {
            OkHttpNet.getIn().postFormRequest();
        });

        binding.okPostBody.setOnClickListener(view -> {
            OkHttpNet.getIn().postBodyRequest();
        });

    }


}
