package com.ydh.info;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.ydh.info.databinding.AtySpeakBinding;
import com.ydh.info.util.IFlyTek;

/**
 * 语音播报界面
 * @author 13001
 */
public class SpeakAty extends AppCompatActivity {

    AtySpeakBinding binding;
    private IFlyTek iFlyTek;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.aty_speak);

        iFlyTek = IFlyTek.getInstance();
        iFlyTek.initTTS(this);

        binding.spleakBt.setOnClickListener(view -> {
            String str = binding.speakEt.getText().toString();
            if (TextUtils.isEmpty(str)) {
                str = "没有内容";
            }
            iFlyTek.speakTxt(str);
        });

    }
}
