package com.ydh.info.util;

import android.content.Context;
import android.os.RemoteException;

import com.iflytek.speech.ErrorCode;
import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechSynthesizer;
import com.iflytek.speech.SpeechUtility;
import com.iflytek.speech.SynthesizerListener;

import static com.iflytek.speech.SpeechSynthesizer.CLOUD_TTS_ROLE_XIAOYAN;
import static com.iflytek.speech.SpeechSynthesizer.CLOUD_TTS_ROLE_XIAOYU;
import static com.iflytek.speech.SpeechSynthesizer.LOCAL_TTS_ROLE_XIAOYAN;
import static com.iflytek.speech.SpeechSynthesizer.TTS_ENGINE_TYPE_CLOUD;
import static com.iflytek.speech.SpeechSynthesizer.TTS_ENGINE_TYPE_LOCAL;

/**
 * @author haru
 * @date 2017/8/8
 */

public class IFlyTek {

    public SpeechSynthesizer mTts;
    private static IFlyTek singleton;

    private IFlyTek() {

    }

    public static IFlyTek getInstance() {
        if (singleton == null) {
            singleton = new IFlyTek();
        }
        return singleton;
    }

    /**
     * 初始化语音模块
     *
     * @param mCxt
     */
    public void initTTS(Context mCxt) {
        SpeechUtility.getUtility(mCxt).setAppid("4d6774d0");
        if (mTts == null) {
            mTts = new SpeechSynthesizer(mCxt, mTtsInitListener);
        }

        mTts.setParameter(SpeechSynthesizer.VOICE_NAME, "xiaoyan");
        mTts.setParameter(SpeechSynthesizer.SPEED, "40");
        mTts.setParameter(SpeechSynthesizer.PITCH, "50");
    }

    public InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(ISpeechModule arg0, int code) {
            if (code == ErrorCode.SUCCESS) {

            }
        }
    };
    public SynthesizerListener mTtsListener = new SynthesizerListener.Stub() {
        @Override
        public void onBufferProgress(int progress) throws RemoteException {

        }

        @Override
        public void onCompleted(int code) throws RemoteException {

        }

        @Override
        public void onSpeakBegin() throws RemoteException {

        }

        @Override
        public void onSpeakPaused() throws RemoteException {

        }

        @Override
        public void onSpeakProgress(int progress) throws RemoteException {

        }

        @Override
        public void onSpeakResumed() throws RemoteException {

        }
    };

    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param str
     */
    public void speakTxt(String str) {
        if (singleton != null) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_LOCAL);
            singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, LOCAL_TTS_ROLE_XIAOYAN);
            mTts.setParameter(SpeechSynthesizer.SPEED, "40");
            mTts.setParameter(SpeechSynthesizer.PITCH, "50");
            singleton.mTts.startSpeaking(str, mTtsListener);
        }
    }

    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param str
     */
    public void speakTxtFemale(String str) {
        if (singleton != null) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_CLOUD);
            singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, CLOUD_TTS_ROLE_XIAOYAN);
            mTts.setParameter(SpeechSynthesizer.SPEED, "40");
            mTts.setParameter(SpeechSynthesizer.PITCH, "50");
            singleton.mTts.startSpeaking(str, mTtsListener);
        }
    }

    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param str
     */
    public void speakTxtMale(String str) {
        if (singleton != null) {
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_CLOUD);
            singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, CLOUD_TTS_ROLE_XIAOYU);
            mTts.setParameter(SpeechSynthesizer.SPEED, "40");
            mTts.setParameter(SpeechSynthesizer.PITCH, "50");
            singleton.mTts.startSpeaking(str, mTtsListener);
        }
    }

}
