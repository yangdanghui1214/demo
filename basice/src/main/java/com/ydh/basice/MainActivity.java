package com.ydh.basice;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tsign.ts.download.view.UpdateSoftwareView;


public class MainActivity extends AppCompatActivity {

//    private String url = "https://file.aitshan.cn/QSCode/20190928/zgnNAIJK_SMT3_0.0.8_aiKindergarten_3399.apk";
        private String url = "https://checkout.tsignsv.cn/aipad/uploadFile/apk/20190918/ZNMJ_YS30_2.12.6_aiGate_3288.apk";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        UpdateSoftwareView softwareView = findViewById(R.id.download_apk);
        softwareView.setTitle("下载apk");
        softwareView.downloadApk(url);

    }

}