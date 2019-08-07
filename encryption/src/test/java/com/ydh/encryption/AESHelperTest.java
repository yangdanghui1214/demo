package com.ydh.encryption;

import org.junit.Test;

import static org.junit.Assert.*;

public class AESHelperTest {

    @Test
    public void encrypt() {

        AESHelper aesHelper=new AESHelper("tsxxtsxxtsxxtsxx");

        System.out.println("加密后的文本" +aesHelper.encrypt("123456"));

    }
}