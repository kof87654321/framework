package com.zl.common.util.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.util.Base64;

/**
 * 加解密工具类
 * 
 * @author zhangxianjun
 * @version $Id: DesUtils.java, v 0.1 2015年6月15日 下午7:48:59 zhangxianjun Exp $
 */
public class DesUtils {
    /**
     * 加密算法的名称
     */
    private static String      Algorithm   = "DESede";
    /**
     * 默认的Key键
     */
    public static final String Default_Key = "ABCD14CJO5F68DY69EQ5IWBYA3F2DESJ";

    /**
     * 加密字符串
     * 
     * @param value 待加密的字串
     * @return 加密后的字串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException,
                                                    NoSuchPaddingException, IllegalBlockSizeException,
                                                    BadPaddingException, UnsupportedEncodingException {
        return encryptString(Default_Key, value);
    }

    /**
     * 加密字符串
     * 
     * @param key 加密使用的Key
     * @param value 待加密的字串
     * @return 加密后的字串
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String encryptString(String key, String value) throws NoSuchAlgorithmException,
                                                                NoSuchPaddingException, InvalidKeyException,
                                                                IllegalBlockSizeException, BadPaddingException,
                                                                UnsupportedEncodingException {
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
            new IllegalArgumentException("待加密字串不允许为空");
        // 密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            bytes = value.getBytes();// 待解密的字串
            desCipher.init(Cipher.ENCRYPT_MODE, deskey);// 初始化密码器，用密钥deskey,进入解密模式
            bytesCipher = desCipher.doFinal(bytes);
            return Base64.encodeBase64String(bytesCipher);// Base64.encodeBase64String(bytesCipher).trim();
        } finally {
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
    }

    /**
     * 解密字符串
     * 
     * @param value 待解密的字串
     * @return 解码后的字串
     * @throws InvalidKeyException
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    public static String decryptString(String value) throws InvalidKeyException, NoSuchAlgorithmException,
                                                    NoSuchPaddingException, IllegalBlockSizeException,
                                                    BadPaddingException, UnsupportedEncodingException {
        if (StringUtils.isNotBlank(value)) {
            return decryptString(Default_Key, value);
        } else
            return "";
    }

    /**
     * 解密字符串
     * 
     * @param key 解码使用的Key
     * @param value 待解密的字串
     * @return 解码后的字串
     * @throws NoSuchAlgorithmException
     * @throws NoSuchPaddingException
     * @throws InvalidKeyException
     * @throws IllegalBlockSizeException
     * @throws BadPaddingException
     * @throws UnsupportedEncodingException
     * @throws InvalidAlgorithmParameterException
     */
    @SuppressWarnings("deprecation")
    public static String decryptString(String key, String value) throws NoSuchAlgorithmException,
                                                                NoSuchPaddingException, InvalidKeyException,
                                                                IllegalBlockSizeException, BadPaddingException,
                                                                UnsupportedEncodingException {
        byte[] bytesKey = null, bytes = null, bytesCipher = null;
        SecretKey deskey = null;
        if (value == null)
            new IllegalArgumentException("待解密字串不允许为空");
        // 密码器
        Cipher desCipher = Cipher.getInstance(Algorithm);
        try {
            bytesKey = Base64.decodeBase64(key);
            deskey = new SecretKeySpec(bytesKey, Algorithm);
            try {
                bytes = Base64.decodeBase64(value);
            } catch (Exception e) {
                bytes = null;
            }
            desCipher.init(Cipher.DECRYPT_MODE, deskey);// 初始化密码器，用密钥deskey,进入解密模式
            bytesCipher = desCipher.doFinal(bytes);
            return (new String(bytesCipher, "UTF-8"));
        } finally {
            bytesKey = null;
            bytes = null;
            bytesCipher = null;
        }
    }
}
