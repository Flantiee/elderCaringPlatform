package com.yiyang.manager.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class AESUtil {
    private static final byte[] SECRET_KEY = "7975922666f6eb02".getBytes(StandardCharsets.UTF_8);

    public static byte[] encrypt(byte[] plaintext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY, "AES");
        // 算法/模式/补码方式
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        return cipher.doFinal(plaintext);
    }

    public static byte[] decrypt(byte[] ciphertext) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKeySpec = new SecretKeySpec(SECRET_KEY, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        return cipher.doFinal(ciphertext);
    }

    public static byte[] encryptId(long id) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        byte[] plaintext = new byte[8];
        for(int i = 0;i < 8; ++i) {
            plaintext[i] = (byte) ((id >> (7 - i) * 8) & 0xFF);
        }
        return encrypt(plaintext);
    }

    public static long decryptId(byte[] ciphertext) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        long id = 0;
        byte[] plaintext = decrypt(ciphertext);
        for(int i = 0;i < 8; ++i) {
            id |= (long) (plaintext[i] & 0xFF) << (7 -i) * 8;
        }
        return id;
    }
}
