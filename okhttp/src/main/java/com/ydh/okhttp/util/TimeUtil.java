package com.ydh.okhttp.util;

/**
 * 连续点击四判断
 *
 * @author 13001
 */
public class TimeUtil {

    /**
     * 点击次数
     */
    private static int onPress = 3;
    private static int onPressAlready = 0;

    /**
     * 间隔时间
     */
    private static long interval = 1000;
    private static long lastTime = 0;

    /**
     * 快速点击3次修改是否使用人脸
     *
     * @return true:是，false:不是
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeInterval = Math.abs(time - lastTime);
        lastTime = time;
        if (timeInterval < interval) {
            onPressAlready += 1;
            if (onPressAlready >= onPress) {
                onPressAlready = 0;
                return true;
            } else {
                return false;
            }
        } else {
            onPressAlready = 0;
            return false;
        }
    }

    /**
     * 两次点击按钮之间的点击间隔不能少于400毫秒
     */
    private static final int MIN_CLICK_DELAY_TIME = 400;
    private static final int MIN_CLICK_DELAY_TIME_NOTICE = 800;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    public static boolean isFastClickNotice() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME_NOTICE) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}
