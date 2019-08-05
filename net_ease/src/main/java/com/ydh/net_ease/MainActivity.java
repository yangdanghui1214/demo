package com.ydh.net_ease;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ydh.net_ease.listener.NetChangeObserve;
import com.ydh.net_ease.manager.NetworkManager;
import com.ydh.net_ease.type.NetType;

public class MainActivity extends AppCompatActivity implements NetChangeObserve {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        NetworkManager.getDefault().init(getApplication());
        NetworkManager.getDefault().setListener(this);

    }

    @Override
    public void onConnect(NetType netType) {
        Log.e("zxy", "onConnect: 连接超时  "+netType.name() );
    }

    @Override
    public void onDisConnect() {
        Log.e("zxy", "onConnect: 没有网络" );
    }
}
