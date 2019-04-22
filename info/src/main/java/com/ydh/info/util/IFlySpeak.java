package com.ydh.info.util;

import android.content.Context;
import android.os.RemoteException;
import android.util.Log;

import com.iflytek.speech.ErrorCode;
import com.iflytek.speech.ISpeechModule;
import com.iflytek.speech.InitListener;
import com.iflytek.speech.SpeechConstant;
import com.iflytek.speech.SpeechSynthesizer;
import com.iflytek.speech.SpeechUtility;
import com.iflytek.speech.SynthesizerListener;

import java.util.LinkedList;
import java.util.Queue;

import static com.iflytek.speech.SpeechSynthesizer.CLOUD_TTS_ROLE_XIAOYAN;
import static com.iflytek.speech.SpeechSynthesizer.CLOUD_TTS_ROLE_XIAOYU;
import static com.iflytek.speech.SpeechSynthesizer.LOCAL_TTS_ROLE_XIAOYAN;
import static com.iflytek.speech.SpeechSynthesizer.TTS_ENGINE_TYPE_CLOUD;
import static com.iflytek.speech.SpeechSynthesizer.TTS_ENGINE_TYPE_LOCAL;

/**
 * @author haru
 * @date 2017/8/8
 */
public class IFlySpeak {

    private final static String TAG = IFlySpeak.class.getSimpleName();
    private SpeechSynthesizer mTts;
    private static IFlySpeak singleton;

    private boolean isFirst = true;
    private boolean isSecond = false;
    private Queue<String> queue = new LinkedList<>();
    public final static int SEX_FEMALE = 0;
    public final static int SEX_MALE = 1;
    private static int mSex = 0;

    private IFlySpeak() {

    }

    public static IFlySpeak getInstance() {
        if (singleton == null) {
            singleton = new IFlySpeak();
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

    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(ISpeechModule arg0, int code) {
            if (code == ErrorCode.SUCCESS) {

            }
        }
    };
    private SynthesizerListener mTtsListener = new SynthesizerListener.Stub() {
        @Override
        public void onBufferProgress(int progress) throws RemoteException {
            Log.e(TAG, "onBufferProgress():");
        }

        @Override
        public void onCompleted(int code) throws RemoteException {
            Log.e(TAG, "onCompleted code:" + code);
            if (code == ErrorCode.SUCCESS) {
                if (!queue.isEmpty()) {
                    playVoiceSex(mSex, queue.poll());
                } else {
                    isFirst = true;
                }
            }
        }

        @Override
        public void onSpeakBegin() throws RemoteException {
            Log.d(TAG, "onSpeakBegin():");
        }

        @Override
        public void onSpeakPaused() throws RemoteException {
            Log.d(TAG, "onSpeakPaused():");
        }

        @Override
        public void onSpeakProgress(int progress) throws RemoteException {
            Log.d(TAG, "onSpeakProgress():");
        }

        @Override
        public void onSpeakResumed() throws RemoteException {
            Log.d(TAG, "onSpeakResumed():");
        }
    };

    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param content
     */
    public void speakTxtQueue(String content, int sex) {
        if (singleton != null) {
            queue.offer(content);
            if (isFirst) {
                playVoiceSex(sex, queue.poll());
                if (isSecond) {
                    isFirst = !isFirst;
                } else {
                    isSecond = true;
                }
            }
        }
    }

    public void playVoiceSex(int sex, String msg) {
        if (SEX_FEMALE == sex) {
            playVoiceFemale(msg);
        } else if (SEX_MALE == sex) {
            playVoiceMale(msg);
        } else {
            playVoice(msg);
        }
    }


    private void playVoice(String msg) {
        Log.e(TAG, "speakTxt() " + msg);
        singleton.mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_LOCAL);
        singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, LOCAL_TTS_ROLE_XIAOYAN);
        singleton.mTts.setParameter(SpeechSynthesizer.SPEED, "40");
        singleton.mTts.setParameter(SpeechSynthesizer.PITCH, "50");
        singleton.mTts.startSpeaking(msg, mTtsListener);
    }


    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param str
     */
    private void playVoiceFemale(String str) {
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_CLOUD);
        singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, CLOUD_TTS_ROLE_XIAOYAN);
        mTts.setParameter(SpeechSynthesizer.SPEED, "40");
        mTts.setParameter(SpeechSynthesizer.PITCH, "50");
        singleton.mTts.startSpeaking(str, mTtsListener);
    }

    /**
     * 语音播报，支持文字转语音，目前没有做传入文字长度限制
     *
     * @param str
     */
    private void playVoiceMale(String str) {
        mTts.setParameter(SpeechConstant.ENGINE_TYPE, TTS_ENGINE_TYPE_CLOUD);
        singleton.mTts.setParameter(SpeechSynthesizer.VOICE_NAME, CLOUD_TTS_ROLE_XIAOYU);
        mTts.setParameter(SpeechSynthesizer.SPEED, "40");
        mTts.setParameter(SpeechSynthesizer.PITCH, "50");
        singleton.mTts.startSpeaking(str, mTtsListener);
    }

}
