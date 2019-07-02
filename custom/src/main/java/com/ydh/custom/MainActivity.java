package com.ydh.custom;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ydh.custom.channel.ChannelActivity;
import com.ydh.custom.databinding.ActivityMainBinding;
import com.ydh.custom.editor.FillActivity;
import com.ydh.custom.keyboard.KeyboardActivity;
import com.ydh.custom.loading.LoadActivity;
import com.ydh.custom.spiner.SpinerActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 软键盘
        binding.button.setOnClickListener(view -> startActivity(new Intent(this, KeyboardActivity.class)));

        // 自定义填空view
        binding.button1.setOnClickListener(view -> startActivity(new Intent(this, FillActivity.class)));

        // 自定义流式布局
        binding.button2.setOnClickListener(view -> startActivity(new Intent(this, ChannelActivity.class)));

        // 自定义流式布局
        binding.button3.setOnClickListener(view -> startActivity(new Intent(this, LoadActivity.class)));

        // 自定义下拉列表
        binding.button4.setOnClickListener(view -> startActivity(new Intent(this, SpinerActivity.class)));

    }
}
