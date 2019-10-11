package com.tsign.health.constant;

/**
 * @author xhj
 */
public class AttendanceConstant {

    public static String serNum = "serNum";

    public static String serReqNum = "serReqNum";

    public static String initTotal = "initTotal";

    public static boolean WhetherAttendNeedUpload = true;

    public static boolean WhetherOssNeedUpload = true;

    public static int NETWORK_REQUEST_TIME = 10;

    public static int THREAD_REMOVE_NETWORK_REQUEST_TIME = 10;

    /**
     * 旷目天眼罪犯上传重复code
     * 2019-01-16 为11122
     * 2019-01-30 调整为90011
     * 2019-2/1服务器并未改掉先恢复之前的
     */
    public static final int ERROR_90011 = 90011;

    private static boolean debug = false;

    private static String HOST;

    private static String URL;

    private static String HYDROLOGY_HOST;

    private static String HYDROLOGY_URL;

    private static String accessToken;

    private static String DEVICE_ID;

    public static boolean simulationOver = false;

    public static boolean getDebug() {
        return debug;
    }

    public static void setDebug(boolean debug) {
        AttendanceConstant.debug = debug;
    }

    public static String getDeviceId() {
        if (DEVICE_ID == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.DEVICE_ID" +
                    " 赋值唯一识别号，可以为设备deviceId");
        }
        return DEVICE_ID;
    }

    public static void setDeviceId(String deviceId) {
        DEVICE_ID = deviceId;
    }

    public static String getHOST() {
        if (HOST == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.HOST" + " 赋值");
        } else if (!HOST.startsWith("http")) {
            throw new IllegalArgumentException("请对 AttendanceConstant.HOST" + " 必须为http或https开头");
        }
        return HOST;
    }

    public static void setHOST(String HOST) {
        AttendanceConstant.HOST = HOST;
    }

    public static String getURL() {
        if (URL == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.URL" + " 赋值");
        }
        return URL;
    }

    public static void setURL(String URL) {
        AttendanceConstant.URL = URL;
    }

    public static String getHydrologyHost() {
        if (HYDROLOGY_HOST == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.HYDROLOGY_HOST" + " 赋值");
        } else if (!HYDROLOGY_HOST.startsWith("http")) {
            throw new IllegalArgumentException("请对 AttendanceConstant.HYDROLOGY_HOST" + " 必须为http或https开头");
        }
        return HYDROLOGY_HOST;
    }

    public static void setHydrologyHost(String HYDROLOGY_HOST) {
        AttendanceConstant.HYDROLOGY_HOST = HYDROLOGY_HOST;
    }

    public static String getHydrologyUrl() {
        if (HYDROLOGY_URL == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.HYDROLOGY_URL" + " 赋值");
        }
        return HYDROLOGY_URL;
    }

    public static void setHydrologyUrl(String HYDROLOGY_URL) {
        AttendanceConstant.HYDROLOGY_URL = HYDROLOGY_URL;
    }

    public static String getAccessToken() {
        if (accessToken == null) {
            throw new IllegalArgumentException("请对 AttendanceConstant.accessToken" + " 赋值");
        }
        return accessToken;
    }

    public static void setAccessToken(String accessToken) {
        AttendanceConstant.accessToken = accessToken;
    }

    public static int getNetworkRequestTime() {
        return NETWORK_REQUEST_TIME;
    }

    public static void setNetworkRequestTime(int networkRequestTime) {
        NETWORK_REQUEST_TIME = networkRequestTime;
    }

}
