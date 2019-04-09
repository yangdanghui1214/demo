package com.ydh.live_data;

import android.arch.lifecycle.Observer;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ydh.live_data.databinding.ActivityMainBinding;
import com.ydh.live_data.utils.LiveDataBusBeta;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        LiveDataBusBeta.getInstance().with("key_main",String.class).observe(this, s -> {
            Toast.makeText(this,"界面1："+s,Toast.LENGTH_LONG).show();;
            Log.e("zxy", "onCreate: 界面1："+s );
        });

        binding.button.setOnClickListener(view -> {
            startActivity(new Intent(this,Main2Activity.class));
        });
        binding.button2.setOnClickListener(view -> {
            LiveDataBusBeta.getInstance().with("key_main2",String.class)
                    .setValue("activity one");
        });
        binding.button3.setOnClickListener(view -> {
            LiveDataBusBeta.getInstance().with("key_main",String.class)
                    .setValue("activity one");
        });

    }
}
