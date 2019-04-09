package com.ydh.eventbus;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ydh.eventbus.databinding.ActivityMainBinding;
import com.ydh.eventbus.event.MessageEvent;
import com.ydh.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    private String TAG = "zxy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        //注册广播
        EventBus.getDefault().register(this);

        //普通事件
        binding.button.setOnClickListener(view -> {
            startActivity(new Intent(this, SendActivity.class));
        });
        //粘性事件
        binding.button2.setOnClickListener(view -> {
            EventBus.getDefault().postSticky(new StickyEvent("粘性事假","123"));
            startActivity(new Intent(this, SendActivity.class));
        });

    }


    /**
     * 接受数据
     * 1.注解决定接受数据的类型；（即便其中一个接受到数据，也不影响其他注解接受数据
     * 2.只有没有取消注册 EventBus 就可以接受到数据。即便当前界面没有显示
     * @param event 发送的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventMain(MessageEvent event) {
        //显示接受数据
        binding.textView.setText(event.getName());
        Log.i(TAG, "onMessageEventMain(), current thread is " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.POSTING)
    public void onMessageEventPosting(MessageEvent event) {
        Log.i(TAG, "onMessageEventPosting(), current thread is " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onMessageEventBackground(MessageEvent event) {
        Log.i(TAG, "onMessageEventBackground(), current thread is " + Thread.currentThread().getName());
    }

    @Subscribe(threadMode = ThreadMode.ASYNC)
    public void onMessageEventAsync(MessageEvent event) {
        Log.i(TAG, "onMessageEventAsync(), current thread is " + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
