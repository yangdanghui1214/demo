package com.ydh.encryption;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;

import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String string= SecurityUtil.aesEncrypt("text123456ss", SecurityUtil.AES_KEY);
//            String string = new String(encrypt,"ISO8859-1");
            System.out.println("加密后的文本1  " + string);
            System.out.println("加密后的文本1  " + SecurityUtil.aesDecrypt(string, SecurityUtil.AES_KEY));
        } catch (Exception e) {
            e.printStackTrace();
        }

//        AESHelper aesHelper = new AESHelper("tsxxtsxxtsxxtsxx");
//
//        String encrypt = aesHelper.encrypt("text123456ss");
//        System.out.println("加密后的文本  " + encrypt);
//        System.out.println("加密后的文本  " + aesHelper.decrypt(encrypt));
//
//
//        String oldStr = "qinfens撒打发第三方";
//        System.out.println("原文：" + oldStr);
//
//        // 加密数据
//        String newStr = null;
//        try {
//            newStr = AES256Encryption.encrypt(oldStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("加密后：" + newStr);
//
//        // 解密数据
//        String str = null;
//        try {
//            str = AES256Encryption.decrypt(newStr);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        System.out.println("解密后：" + str);

    }
}
