package com.ydh.network.util;

import java.util.HashMap;

/**
 * 参数拼接类
 * @author 13001
 */
public class ParameterUtil {

    /**
     * get请求参数拼接
     *
     * @param params 参数
     * @return
     */
    public static String getParams(HashMap<String, String> params) {
        if (params == null || params.isEmpty()) {
            return "";
        }
        StringBuilder tempParams = new StringBuilder();
        int pos = 0;
        for (String key : params.keySet()) {
            if (pos > 0) {
                tempParams.append("&");
            }
            tempParams.append(String.format("%s=%s", key, params.get(key)));
            pos++;
        }
        return "?" + tempParams.toString();
    }

}
