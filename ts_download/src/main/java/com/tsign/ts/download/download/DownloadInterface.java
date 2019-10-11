package com.tsign.ts.download.download;

/**
 * APP 下载回调
 *
 * @author 13001
 */
public interface DownloadInterface {

    void updateStart();

    void updateProgress(int progress,int  decimal);

    void updateError(UpdateType type);

    void updateDismiss();

    void updateInstall();

}
