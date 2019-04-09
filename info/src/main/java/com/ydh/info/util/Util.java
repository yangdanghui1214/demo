package com.ydh.info.util;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * @author 13001
 */
public class Util {

    public static String getResolutionRatio(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        int heigth = dm.heightPixels;
        int width = dm.widthPixels;
        return "屏幕的宽 ： "+width+"\n屏幕的高为 ： "+heigth;
    }

}
