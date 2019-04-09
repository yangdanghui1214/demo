package com.ydh.live_data;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.ydh.live_data.databinding.ActivityMain2Binding;
import com.ydh.live_data.utils.LiveDataBusBeta;

public class Main2Activity extends AppCompatActivity {

    ActivityMain2Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main2);

        LiveDataBusBeta.getInstance().with("key_main2",String.class).observe(this, s -> {
            Toast.makeText(this,"界面2："+s,Toast.LENGTH_LONG).show();;
            Log.e("zxy", "onCreate: 界面2："+s );
        });

        binding.button4.setOnClickListener(view -> {
            LiveDataBusBeta.getInstance().with("key_main2",String.class)
                    .setValue("主线程发送数据");
        });
        binding.button5.setOnClickListener(view -> {
            new Thread(() -> {
                LiveDataBusBeta.getInstance().with("key_main2",String.class)
                        .setValue("子线程发送数据");
            }).start();
        });
        binding.button6.setOnClickListener(view -> {
            LiveDataBusBeta.getInstance().with("key_main",String.class)
                    .setValue("界面2的数据");
        });
        binding.button7.setOnClickListener(view -> {
            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                LiveDataBusBeta.getInstance().with("key_main2",String.class)
                        .setValue("子线程发送数据");
            }).start();
        });

    }
}
