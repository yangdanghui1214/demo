package com.ydh.info;

import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ydh.info.camera.CameraInfoAty;
import com.ydh.info.databinding.ActivityMainBinding;
import com.ydh.info.util.NetStateReceiver;

import static com.ydh.info.util.Util.getMac;
import static com.ydh.info.util.Util.getResolutionRatio;

/**
 * 主界面
 *
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(view -> {
            startActivity(new Intent(this, SpeakAty.class));
        });
        binding.button2.setOnClickListener(view -> {
            startActivity(new Intent(this, CameraInfoAty.class));
        });

        String string = getResolutionRatio(this) + "\nMac: " + getMac(this);
        binding.textView.setText(string);

        NetStateReceiver hsReceiver = new NetStateReceiver(this, binding.ivWifi);
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        filter.addAction(WifiManager.RSSI_CHANGED_ACTION);
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);
        filter.addAction(WifiManager.WIFI_STATE_CHANGED_ACTION);
        registerReceiver(hsReceiver, filter);

    }
}
