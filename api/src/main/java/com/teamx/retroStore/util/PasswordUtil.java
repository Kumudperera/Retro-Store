package com.teamx.retroStore.util;

import com.teamx.retroStore.dto.common.PairDTO;
import com.teamx.retroStore.exception.impl.AppsCommonErrorCode;
import com.teamx.retroStore.exception.impl.AppsException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.MessageDigest;
import java.util.Base64;
import java.util.Random;

public class PasswordUtil {

    public static String generateRandomPlainPassword(Integer length) {

        final String ALPHA_NUM_SPEC = "0123456789abcdefghijklmnopqrstuvxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()_=";

        StringBuffer sb = new StringBuffer(length);
        for (int i = 0; i < length; i++) {
            int ndx = new Random().nextInt(ALPHA_NUM_SPEC.length());
            sb.append(ALPHA_NUM_SPEC.charAt(ndx));
        }

        return sb.toString();
    }

    public static PairDTO<String, String> generateRandomEncodedPassword(PasswordEncoder passwordEncoder, Integer length) throws AppsException {
        String plainPassword = generateRandomPlainPassword(length);
        String encodedPassword = generateEncodedPassword(passwordEncoder, plainPassword);
        return PairDTO.of(plainPassword, encodedPassword);
    }

    public static String generateEncodedPassword(PasswordEncoder passwordEncoder, String plainPassword) throws AppsException {
        String encodedPassword;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.reset();
            messageDigest.update(plainPassword.getBytes());
            byte[] digest = messageDigest.digest();
            String passwordToEncode = new String(Base64.getEncoder().encode(digest));

            System.out.println("Please use this password for api call: " + passwordToEncode);
            encodedPassword = passwordEncoder.encode(passwordToEncode);
        } catch (Exception e) {
            throw new AppsException(AppsCommonErrorCode.APPS_UNAUTHORISED, e);
        }

        return encodedPassword;
    }
}
