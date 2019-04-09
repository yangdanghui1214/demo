package com.mt.aop;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.ts.aop.annotation.Async;
import com.ts.aop.annotation.LogMethod;
import com.ts.aop.annotation.Main;

/**
 * @author 13001
 */
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        ARouter.getInstance().inject(this);

        findViewById(R.id.button3).setOnClickListener(view -> log(2, 7, "log 打印"));

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            //            @SingleClick(request = R.id.button5, timeout = 1000)
            @Override
            public void onClick(View view) {
//                log(4,5,"按钮5");
                Log.e("zxy", "onClick: 按钮5");
            }
        });
        findViewById(R.id.button6).setOnClickListener(new View.OnClickListener() {
            //            @SingleClick(request = R.id.button6, timeout = 1000)
            @Override
            public void onClick(View view) {
//                log(4,6,"按钮6");
                Log.e("zxy", "onClick: 按钮6");
            }
        });

    }

    @Async
    public void readFile(View view) {
        Log.e("zxy", "readFile: 读取文件  " + Thread.currentThread().toString());
        showToast("读取成功");
    }

    @Async
    public void writeFile(View view) {
        Log.e("zxy", "readFile: 写入文件  " + Thread.currentThread().toString());
        showToast("写入成功");
    }

    /**
     * @param string 播报内容
     */
    @Main
    public void showToast(String string) {
        Toast.makeText(this, string + Thread.currentThread().toString(), Toast.LENGTH_LONG).show();
    }

    @LogMethod(value = Log.ERROR,perform = false)
    public int log(int a, int b, String str) {
        int c = a + b;

        return c;
    }

}
