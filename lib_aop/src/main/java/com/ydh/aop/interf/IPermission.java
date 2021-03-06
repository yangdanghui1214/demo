package com.ydh.aop.interf;

import java.util.List;

/**
 * Created by mq on 2018/3/6 下午2:52
 * mqcoder90@gmail.com
 */

public interface IPermission {

    /**
     * 同意权限
     */
    void permissionGranted();

    /**
     * 拒绝权限并且选中不再提示
     *
     * @param requestCode
     * @param denyList
     */
    void permissionDenied(int requestCode, List<String> denyList);

    /**
     * 取消权限
     *
     * @param requestCode
     */
    void permissionCanceled(int requestCode);
}
