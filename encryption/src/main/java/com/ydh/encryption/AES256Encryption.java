package com.ydh.encryption;

import android.util.Base64;


import java.security.Key;
import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;


/**
 * 密码加密解密工具类
 * 设置登录及钱包支付密码要用
 * 采用的是Base64+AES加密方式
 * Constant.RES_KEY 和服务器上的一样,要和服务器做交互  key 是base64后的 加密后的串也是base64后的
 */
public class AES256Encryption {

    /**
     * 密钥算法
     * java6支持56位密钥，bouncycastle支持64位
     */
    private static final String KEY_ALGORITHM = "AES";

    /**
     * 加密/解密算法/工作模式/填充方式
     * JAVA6 支持PKCS5PADDING填充方式
     * Bouncy castle支持PKCS7Padding填充方式
     */
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS7Padding";
    private static final String AES_KEY = "tsxxtsxxtsxxtsxx";
    private static final String AES_VI = "tsxxtsxxtsxxtsxx";

    /**
     * 转换密钥
     *
     * @param key 二进制密钥
     * @return Key 密钥
     */
    private static Key toKey(byte[] key) throws Exception {

        // 实例化DES密钥
        // 生成密钥
        SecretKey secretKey = new SecretKeySpec(key, KEY_ALGORITHM);
        return secretKey;
    }

    /**
     * 加密数据
     *
     * @param data 待加密数据
     * @param key  密钥
     * @return byte[] 加密后的数据
     */
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {

        // 还原密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM, "BC");
        // 初始化，设置为加密模式
        cipher.init(Cipher.ENCRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }

    /**
     * 解密数据
     *
     * @param data 待解密数据
     * @param key  密钥
     * @return byte[] 解密后的数据
     */
    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {

        // 欢迎密钥
        Key k = toKey(key);
        /**
         * 实例化
         * 使用 PKCS7PADDING 填充方式，按如下方式实现,就是调用bouncycastle组件实现
         * Cipher.getInstance(CIPHER_ALGORITHM,"BC")
         */
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        // 初始化，设置为解密模式
        cipher.init(Cipher.DECRYPT_MODE, k);
        // 执行操作
        return cipher.doFinal(data);
    }


    /**
     * 解密
     *
     * @param newStr base64后的 ，从服务器获取解密成字符串
     * @return
     * @author Guocg
     */
    public static String decrypt(String newStr) throws Exception {

        return new String(
                AES256Encryption.decrypt(Base64.decode(newStr, Base64.DEFAULT), Base64.decode(AES_KEY.getBytes(), Base64.DEFAULT)),
                "utf-8");
    }

    /**
     * 加密
     *
     * @param oldStr，本地加密上传
     * @return 密文
     * @author Guocg
     */
    public static String encrypt(String oldStr) throws Exception {

        return Base64
                .encodeToString(AES256Encryption.encrypt(oldStr.getBytes(), Base64.decode(AES_KEY.getBytes(), Base64.DEFAULT)), Base64.DEFAULT);
    }

}

