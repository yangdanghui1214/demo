package com.ydh.eventbus;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ydh.eventbus.databinding.ActivitySendBinding;
import com.ydh.eventbus.event.MessageEvent;
import com.ydh.eventbus.event.StickyEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class SendActivity extends AppCompatActivity {

    ActivitySendBinding binding;

    boolean stater = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_send);

        // 注册
        // 1.EventBus 只可以注册一次，多次注册会崩溃
        // 2.EventBus 只有在当前界面注册过一次就可以接受到数据（包括粘性事件）；不能注册第二次 请参照第一条
//        EventBus.getDefault().register(this);

        //普通事件
        binding.btSend1.setOnClickListener(view -> {
            EventBus.getDefault().post(new MessageEvent("tsh", "123"));
        });
        //粘性事件 在点击事件后注册 EventBus 才可以接受到数据
        binding.btSend2.setOnClickListener(view -> {
            if (stater) {
                stater = false;
                EventBus.getDefault().register(this);
            }
        });

    }

    /**
     * 接受粘性事件
     *
     * @param event 发送的数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void stickyEventMain(StickyEvent event) {
        binding.tvSend.setText(event.getName());
        Log.i("zxy", "onMessageEventMain(), current thread is " + Thread.currentThread().getName());
    }

    /**
     * 接受到数据
     * 1.当前界面发送数据时在本界面注册的情况下是可以接受到数据的；并且在其他界面也是可以接受到数据（注：只要注册了 EventBus 都可以接受到数据
     * 2.当前界面没有注册 EventBus 的情况下是接受不到数据的
     *
     * @param event 数据
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void messageEventMain(MessageEvent event) {
        //显示接受数据
        binding.tvSend.setText(event.getName());
        finish();
        Log.e("zxy", "onMessageEventMain(), current thread is " + Thread.currentThread().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }
}
