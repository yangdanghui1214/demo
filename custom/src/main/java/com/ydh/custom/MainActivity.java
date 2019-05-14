package com.ydh.custom;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ydh.custom.databinding.ActivityMainBinding;
import com.ydh.custom.keyboard.KeyboardActivity;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.button.setOnClickListener(view -> startActivity(new Intent(this, KeyboardActivity.class)));
    }
}
