package com.ydh.animation;

import android.animation.Animator;
import android.animation.FloatEvaluator;
import android.animation.ObjectAnimator;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ydh.animation.databinding.ActivityMainBinding;
import com.ydh.animation.databinding.ActivitySvgBinding;
import com.ydh.animation.mail.MailActivity;
import com.ydh.animation.property.PropertyRotateRvActivity;
import com.ydh.animation.svg.SvgActivity;

/**
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {

    private static final String TAG = "SimpleAnimationActivity";

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // 跳转SVG 界面
        binding.button.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, SvgActivity.class)));

        binding.button1.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, PropertyRotateRvActivity.class)));
        // 信封动效
        binding.button2.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, MailActivity.class)));



    }


}
