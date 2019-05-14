package com.ydh.custom.keyboard;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ydh.custom.R;

/**
 * 自定义view
 *
 * @author 13001
 */
public class KeyboardView extends LinearLayout {

    String[][] str = new String[][]{{"1", "2", "3"}, {"4", "5", "6"}, {"7", "8", "9"}, {"清除", "0", "确认"}};
    float height;

    public KeyboardView(Context context) {
        this(context, null);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public KeyboardView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    /**
     * 界面初始化
     * @param type 界面初始化类型
     * @param keyboardListener 按键监听
     */
    public void initView(String type, KeyboardListener keyboardListener) {

        if (type.equals("呼叫")){
           str[ str.length-1][ str.length-2]=type;
        }

        this.keyboardListener=keyboardListener;

        post(this::initView);

    }

    private void initView(){

        height = (float) (getMeasuredHeight() / 4 * 0.2);

        removeAllViews();

        LinearLayout.LayoutParams linearParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        LinearLayout mVerticalLaout = new LinearLayout(getContext());
        mVerticalLaout.setOrientation(LinearLayout.VERTICAL);
        mVerticalLaout.setLayoutParams(linearParams);

        LinearLayout.LayoutParams linearParam = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1.0f);

        for (String[] strings : str) {
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            linearLayout.setLayoutParams(linearParam);
            for (String name : strings) {
                linearLayout.addView(getTextView(name), linearParam);
            }
            mVerticalLaout.addView(linearLayout);
        }

        addView(mVerticalLaout);
    }

    /**
     * 控件按钮
     *
     * @return 按钮的textView
     */
    private TextView getTextView(String name) {
        TextView textView = (TextView) LayoutInflater.from(getContext()).inflate(R.layout.view_keyboard,null);
        textView.setText(name);
        textView.setTextSize(height);
        textView.setOnClickListener(view ->{
            switch (name) {
                case "清除":
                    keyboardListener.cancel();
                    break;
                case "确认":
                    keyboardListener.launch();
                    break;
                case "呼叫":
                    keyboardListener.launch();
                    break;
                default:
                    keyboardListener.append(name);
                    break;
            }
        });
        return textView;
    }

    KeyboardListener keyboardListener=null;

    /**
     * 按键监听
     */
    interface KeyboardListener{

        /**
         * 按键返回
         * @param string 添加内容
         */
        void append(String string);

        /**
         * 具体操作
         */
        void launch();

        /**
         *  清除操作
         */
        void cancel();

    }

}
