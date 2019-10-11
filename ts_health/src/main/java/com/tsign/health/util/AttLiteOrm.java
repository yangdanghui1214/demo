package com.tsign.health.util;

import com.litesuits.orm.LiteOrm;

/**
 * @author xhj
 * @date 2018/11/10
 */

public class AttLiteOrm {

    private static LiteOrm attLiteOrm;

    public static LiteOrm getAttLiteOrm() {
        if (attLiteOrm == null) {
            throw new IllegalArgumentException("请在应用中设置liteOrm\nAttLiteOrm.setAttLiteOrm(liteOrm)");
        } else {
            return attLiteOrm;
        }
    }

    public static void setAttLiteOrm(LiteOrm attLiteOrm) {
        AttLiteOrm.attLiteOrm = attLiteOrm;
    }
}
