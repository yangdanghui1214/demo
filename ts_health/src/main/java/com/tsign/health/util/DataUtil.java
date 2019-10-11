package com.tsign.health.util;

import java.text.SimpleDateFormat;

public class DataUtil {

    public static String formatDate(long time) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(time);
    }

}
