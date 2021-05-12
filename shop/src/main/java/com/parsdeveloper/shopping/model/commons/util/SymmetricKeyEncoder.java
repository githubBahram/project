package com.parsdeveloper.shopping.model.commons.util;

import org.hibernate.service.spi.ServiceException;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class SymmetricKeyEncoder {

    private final Object object = new Object();
    private final Cipher cipher;
    private final int length;
    private final String PAD_CHAR = "0";

    public SymmetricKeyEncoder(String algorithm, int length) {
        try {
            this.cipher = Cipher.getInstance(algorithm);
            this.length = length;
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException("error in create SymmetricKeyEncoder constructor because algorithm name isn't valid", e);
        } catch (NoSuchPaddingException e) {
            throw new IllegalStateException("error in create SymmetricKeyEncoder constructor", e);
        }
    }

    public byte[] encode(byte[] bytes, String secret) throws ServiceException {
        return toggleCoding(bytes, secret, Cipher.ENCRYPT_MODE);
    }

    public byte[] decode(byte[] bytes, String secret) throws ServiceException {
        return toggleCoding(bytes, secret, Cipher.DECRYPT_MODE);
    }

    private byte[] toggleCoding(byte[] bytes, String secret, int mode) throws ServiceException {

        SecretKey secretKey = new SecretKeySpec(correctSecret(secret, length), cipher.getAlgorithm());
        try {
            synchronized (object) {
                cipher.init(mode, secretKey);
                return cipher.doFinal(bytes);
            }
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            throw new ServiceException("error during encode or decode", e);
        }
    }

    private byte[] correctSecret(String secret, int length) {
        if (secret.length() < length) {
            int missingLength = length - secret.length();
            StringBuilder sBuilder = new StringBuilder(secret);

            for (int i = 0; i < missingLength; i++) {
                sBuilder.append(PAD_CHAR);
            }
            secret = sBuilder.toString();
        }
        return secret.substring(0, length).getBytes(StandardCharsets.UTF_8);
    }
}