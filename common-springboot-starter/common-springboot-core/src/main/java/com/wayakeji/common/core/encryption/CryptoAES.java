package com.wayakeji.common.core.encryption;

import cn.hutool.core.util.CharsetUtil;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;

import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * crypto aes 加解密
 */
public class CryptoAES {

    private static final String KEY_ALGORITHM = "AES";


    /**
     * 原文解密
     * @return
     */
    public static String decryptAES(String encodeKey,String password) {
        // 构建前端对应解密AES 因子
        AES aes = new AES(Mode.CFB, Padding.NoPadding,
                new SecretKeySpec(encodeKey.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(encodeKey.getBytes()));
        return aes.decryptStr(password);
    }

    /**
     * 加密
     * @param encodeKey
     * @param password
     * @return
     */
    public static String encryptBase64(String encodeKey,String password) {
        // 构建前端对应解密AES 因子
        AES aes = new AES(Mode.CFB, Padding.NoPadding,
                new SecretKeySpec(encodeKey.getBytes(), KEY_ALGORITHM),
                new IvParameterSpec(encodeKey.getBytes()));
        return aes.encryptBase64(password, CharsetUtil.CHARSET_UTF_8);
    }
}
