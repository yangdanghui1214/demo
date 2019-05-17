package com.ydh.custom.keyboard;

import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.ydh.custom.R;
import com.ydh.custom.databinding.ActivityKeyboardBinding;

/**
 * 软键盘UI
 * @author 13001
 */
public class KeyboardActivity extends AppCompatActivity implements KeyboardViewKt.KeyboardListener, KeyboardRoundViewKt.KeyboardListener {

    ActivityKeyboardBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this,R.layout.activity_keyboard);

//        binding.key.initView("呼叫",this);

        binding.keyRound.initView(this);

    }

    /**
     * @param string 添加内容
     */
    @Override
    public void append(String string) {
        binding.editText.setText(binding.editText.getText()+string);
    }

    /**
     *
     */
    @Override
    public void launch() {
        String content=  binding.editText.getText().toString();
        binding.editText.setText("");
        if (TextUtils.isEmpty(content)){
            Toast.makeText(this,"请添加内容",Toast.LENGTH_LONG).show();
        }else {
            Toast.makeText(this,content,Toast.LENGTH_LONG).show();
        }
    }

    /**
     *
     */
    @Override
    public void cancel() {
        String content=  binding.editText.getText().toString();
        if (TextUtils.isEmpty(content)||content.length()==1){
            binding.editText.setText("");
        }else {
            binding.editText.setText(content.substring(0,content.length()-1));
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
