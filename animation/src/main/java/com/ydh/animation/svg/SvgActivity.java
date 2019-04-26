package com.ydh.animation.svg;

import android.databinding.DataBindingUtil;
import android.graphics.drawable.Animatable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ydh.animation.R;
import com.ydh.animation.databinding.ActivitySvgBinding;

public class SvgActivity extends AppCompatActivity {

    ActivitySvgBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_svg);

        binding.textView2.setOnClickListener(view -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ((Animatable) binding.imageView2.getDrawable()).start();
            } else {
                binding.imageView2.setImageDrawable(
                        ContextCompat.getDrawable(this, R.drawable.ic_back));
            }
        });

    }
}
