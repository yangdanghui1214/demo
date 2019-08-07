package com.ydh.encryption;

import android.util.Base64;

import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class SecurityUtil {

    public static final String AES_KEY = "tsxxtsxxtsxxtsxx";
    public static final String AES_VI = "tsxxtsxxtsxxtsxx";

    /**
     * 加密
     *
     * @param content
     * @param strKey
     * @return encrypted
     */
    public static byte[] encrypt(String content, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(AES_VI.getBytes("utf-8"));
        cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
        byte[] encrypted = cipher.doFinal(content.getBytes("utf-8"));
        return encrypted;
    }

    /**
     * 解密
     *
     * @param strKey
     * @param content
     * @return
     */
    public static String decrypt(byte[] content, String strKey) throws Exception {
        SecretKeySpec skeySpec = getKey(strKey);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec iv = new IvParameterSpec(AES_VI.getBytes("utf-8"));
        cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
        byte[] original = cipher.doFinal(content);
        String originalString = new String(original, "utf-8");
        return originalString;
    }

    private static SecretKeySpec getKey(String strKey) throws Exception {
        byte[] arrBTmp = strKey.getBytes("utf-8");
        byte[] arrB = new byte[16]; // 创建一个空的16位字节数组（默认值为0）

        for (int i = 0; i < arrBTmp.length && i < arrB.length; i++) {
            arrB[i] = arrBTmp[i];
        }

        SecretKeySpec skeySpec = new SecretKeySpec(arrB, "AES");
        return skeySpec;
    }

    /**
     * @param bytes 待编码的byte[]
     * @return 编码后的base 64 code
     * @Desc JDK1.8下提供的正式base64可用于生产环境
     * base 64 encode
     */
    public static String base64Encode(byte[] bytes) {
        // new BASE64Encoder().encode(bytes);
        //StandardCharsets.ISO_8859_1
        return Base64.encodeToString(bytes,Base64.NO_WRAP);
//        return java.util.Base64.getEncoder().encodeToString(bytes);

    }

    /**
     * base 64 decode
     *
     * @param base64Code 待解码的base 64 code
     * @return 解码后的byte[]
     */
    public static byte[] base64Decode(String base64Code) {
        //return base64Code.isEmpty() ? null : new BASE64Decoder().decodeBuffer(base64Code);
        //StandardCharsets.ISO_8859_1
        return base64Code.isEmpty() ? null : Base64.decode(base64Code,Base64.NO_WRAP);
    }

    /**
     * AES加密为 base64code
     *
     * @param content    待加密的内容
     * @param encryptKey 加密密钥
     * @return 加密后的base 64 code
     */
    public static String aesEncrypt(String content, String encryptKey) throws Exception {
        return base64Encode(encrypt(content, encryptKey));
    }

    /**
     * 将base64codeAES解密
     *
     * @param encryptStr 待解密的base64code
     * @param decryptKey 解密密钥
     * @return 解密后的string
     */
    public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
        return encryptStr.isEmpty() ? null : decrypt(base64Decode(encryptStr), decryptKey);
    }

    /**
     * md5加密 32位
     *
     * @param contentStr
     * @return
     */
    public static String md5Encode(String contentStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = contentStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];


        for (int i = 0; i < charArray.length; i++) {
            byteArray[i] = (byte) charArray[i];
        }


        byte[] md5Bytes = md5.digest(byteArray);


        StringBuffer hexValue = new StringBuffer();


        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void main(String[] args) throws Exception {
        System.out.println();
        String s = new String("1222222");
        System.out.println("原始：" + s);
        String aesString = aesEncrypt(s, "1");
        System.out.println("aes后：" + aesString);
        System.out.println("aes解密后：" + aesDecrypt(aesString, "1"));
    }

}