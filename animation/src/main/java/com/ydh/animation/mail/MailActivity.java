package com.ydh.animation.mail;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.ydh.animation.R;

import java.util.List;

public class MailActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout rlOne;
    private LinearLayout llTwo;
    private ImageView ivUp;
    private ImageView ivCenter;
    private ImageView ivOff;
    private RelativeLayout rlThree;
    private ImageView ivEnvelope;
    private FrameLayout flPaper;
    private Button btnStart;
    /**自定义动画
     */
    private MyAnimation animation, animation2, animation3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail);
        init();
        iniv();
    }

    private void init() {
        rlOne = findViewById(R.id.rl_one);
        llTwo = findViewById(R.id.ll_two);
        rlThree = findViewById(R.id.rl_three);
        ivUp = findViewById(R.id.iv_up);
        ivCenter = findViewById(R.id.iv_center);
        ivOff = findViewById(R.id.iv_off);
        ivEnvelope = findViewById(R.id.iv_envelope);
        flPaper = findViewById(R.id.fl_paper);
        btnStart = findViewById(R.id.btn_start);
        btnStart.setOnClickListener(this);

    }

    private void iniv() {
        //三种不同的动画，并设置了透明度
        animation = new MyAnimation(1, 1, (float) 0.3);
        animation2 = new MyAnimation(2, 1, (float) 0.5);
        animation3 = new MyAnimation(3, 1, (float) 0.3);
        animation.setDuration(1000);
        animation.setFillAfter(true);
        animation2.setDuration(1000);
        animation2.setFillAfter(true);
        animation3.setDuration(1000);
        animation3.setFillAfter(true);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                llTwo.setVisibility(View.GONE);
                rlThree.setVisibility(View.VISIBLE);

                ObjectAnimator translation =   ObjectAnimator.ofFloat(ivEnvelope, "translationY", 0, -1800f);
                translation.setDuration(1500);
                translation.start();
                ObjectAnimator translationY =   ObjectAnimator.ofFloat(flPaper, "translationY", 0, -1800f);
                translationY.setDuration(1100);
                translationY.start();

                translation.addListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animator animation) {

                        rlOne.setVisibility(View.GONE);
                        rlThree.setVisibility(View.GONE);
                        //延迟两秒显示原来界面
                        new Handler().postDelayed(() -> rlOne.setVisibility(View.VISIBLE), 2000);
                    }

                    @Override
                    public void onAnimationCancel(Animator animation) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animation) {

                    }
                });
            }

            @Override
            public void onAnimationRepeat(Animation animation) {


            }
        });
    }

    @Override
    public void onClick(View v) {


//        List<ImagePiece> list = ImageSplitter.split(rlOne, 3);
//        ivUp.setImageBitmap(list.get(0).bitmap);
//        ivCenter.setImageBitmap(list.get(1).bitmap);
//        ivOff.setImageBitmap(list.get(2).bitmap);


        llTwo.setVisibility(View.VISIBLE);

        RotateAnimation rotateAnimation=new  RotateAnimation(90f, 0f,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 1f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);

        //开始动画
        ivUp.startAnimation(rotateAnimation);
        ivCenter.startAnimation(animation);
        ivOff.startAnimation(animation3);


    }
}
