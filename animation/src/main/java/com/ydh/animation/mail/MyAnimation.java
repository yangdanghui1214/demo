package com.ydh.animation.mail;

import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class MyAnimation extends Animation {

    private int width;
    private int height;
    private int mType;
    private float mFromAlpha;
    private float mToAlpha;


    public MyAnimation(int type,float fromAlpha, float toAlpha){
        mType=type;
        mFromAlpha = fromAlpha;
        mToAlpha = toAlpha;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        this.height = height;
        this.width = width;
    }


    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        super.applyTransformation(interpolatedTime, t);
        Matrix matrix = t.getMatrix();
        final float alpha = mFromAlpha;
        t.setAlpha(alpha + ((mToAlpha - alpha) * interpolatedTime));
        float[] src =new float[8];
        float[] dst =new float[8];

        //上半部分
        if(1==mType) {
            src[0]=0;
            src[1]=0;
            src[2]=width;
            src[3]=0;
            src[4]=width;
            src[5]=height;
            src[6]=0;
            src[7]=height;

            dst[0]=-350 * interpolatedTime;
            dst[1]= 350 * interpolatedTime;
            dst[2]= width + 350 * interpolatedTime;
            dst[3]=350 * interpolatedTime;
            dst[4]=width - 104 * interpolatedTime;
            dst[5]=height;
            dst[6]=104 * interpolatedTime;
            dst[7]=height;
            matrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        } else if(3==mType) {
            //下半部分
            src[0]=0;
            src[1]=0;
            src[2]=width;
            src[3]=0;
            src[4]=width;
            src[5]=height;
            src[6]=0;
            src[7]=height;

            dst[0]=104 * interpolatedTime;
            dst[1]=0;
            dst[2]=width - 104 * interpolatedTime;
            dst[3]=0;
            dst[4]=width +350 * interpolatedTime;
            dst[5]=height-350*interpolatedTime;
            dst[6]=-350 * interpolatedTime;
            dst[7]=height-350*interpolatedTime;
            matrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
        }else if(3==mType){
            //缩放
            matrix.setScale((float) (1-(0.2*interpolatedTime)), 1,width/2,height/2);
        }
    }
}

