package com.ydh.info;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ydh.info.camera.CameraInfoAty;
import com.ydh.info.databinding.ActivityMainBinding;

import static com.ydh.info.util.Util.getMac;
import static com.ydh.info.util.Util.getResolutionRatio;

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

    }
}
