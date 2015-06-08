package com.zl.common.util.token;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.MessageFormat;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.zl.common.util.encrypt.DesUtils;

public class TokenUtils {
    private static final Logger LOGGER      = LoggerFactory.getLogger(TokenUtils.class);

    private static final String ENCRYPT_KEY = "";

    public static String getToken(Long userId, String password, Date lastLoginTime) {
        String message = MessageFormat.format("{0}_{1}_{2}", String.valueOf(userId), password,
            String.valueOf(lastLoginTime.getTime()));
        String token = null;
        try {
            token = DesUtils.encryptString(ENCRYPT_KEY, message);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | UnsupportedEncodingException e) {
            LOGGER.error("get token error!", e);
        }
        return token;
    }

    public boolean checkToken(Long userId, String password, String token) {
        token = StringUtils.replace(token, " ", "+");
        String tokenSource = null;
        try {
            tokenSource = DesUtils.decryptString(ENCRYPT_KEY, token);
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException
                | BadPaddingException | UnsupportedEncodingException e) {
            return false;
        }
        String[] tokens = StringUtils.split(tokenSource, "_");
        return userId.equals(tokens[0]) && password.equals(tokens[1]);
    }
}
