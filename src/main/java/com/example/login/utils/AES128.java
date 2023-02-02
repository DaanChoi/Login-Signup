package com.example.login.utils;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

public class AES128 {
    private String ips;
    private Key keySpec;

    public AES128(String key) {
        try {
            byte[] b = key.getBytes("UTF-8");
            byte[] keyBytes = new byte[16]; // AES128비트 암호화 방식에서는 16바이트(128비트) 비밀키 사용
            System.arraycopy(b, 0, keyBytes, 0, keyBytes.length);
            // 원본배열
            // 원본 배열에서 복사할 항목의 시작 인덱스
            // 새배열
            // 새 배열에서 붙여놓을 시작 인덱스
            // 복사할 개수

            SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

            this.ips = key.substring(0, 16); // 문자열 자르기(i:0~15), AES128비트 암호화 방식에서는 16바이트(128비트) 비밀키 사용
            this.keySpec = keySpec;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String encrypt(String str) {
        Cipher cipher;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes()));

            byte[] encrypted = cipher.doFinal(str.getBytes("UTF-8"));
            String Str = new String(Base64.encodeBase64(encrypted));

            return Str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String decrypt(String str) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec,
                    new IvParameterSpec(ips.getBytes("UTF-8")));

            byte[] byteStr = Base64.decodeBase64(str.getBytes());
            String Str = new String(cipher.doFinal(byteStr), "UTF-8");

            return Str;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
