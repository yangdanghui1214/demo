package com.ydh.custom.editor;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.ydh.custom.R;

/**
 * 填空输入输入
 *
 * @author 13001
 */
public class FillActivity extends AppCompatActivity {
    FillTextView fillText;
    TextView tvFills;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fill);

        fillText = findViewById(R.id.fillText);
        tvFills = findViewById(R.id.tv_fills);
    }

    public void clickBtn(View view) {
        String t = "";
        for (String text : fillText.getFillTexts()) {
            t += text;
            t += ",";
        }
        tvFills.setText(t.subSequence(0, t.length() - 1));
    }
}
