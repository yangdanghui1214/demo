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

        ZincView view = new ZincView(this);
        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(view, "zinc", 0f, 2f, 5f);
        // 时长
        objectAnimator.setDuration(2000);
        // 插值器
        objectAnimator.setInterpolator(new TimeInterpolator() {
            @Override
            public float getInterpolation(float input) {
                return input;
            }
        });
        // 估值器
        objectAnimator.setEvaluator(new FloatEvaluator());
        // 更新回调
        objectAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedValue().getClass());
                Log.i(TAG, "onAnimationUpdate: " + animation.getAnimatedValue());
            }
        });
        // 生命周期监听器
        objectAnimator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                Log.i(TAG, "onAnimationStart: ");
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.i(TAG, "onAnimationEnd: ");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
                Log.i(TAG, "onAnimationCancel: ");
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                Log.i(TAG, "onAnimationRepeat: ");
            }
        });
        // 开启
        objectAnimator.start();

    }

    public static class ZincView extends View {
        public ZincView(Context context) {
            super(context);
        }

        public void setZinc(float value) {
            Log.i(TAG, "setZinc: " + value);
        }
    }

}
