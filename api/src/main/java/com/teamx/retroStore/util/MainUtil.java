package com.teamx.retroStore.util;

import com.teamx.retroStore.common.constant.DomainConstants;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class MainUtil {

    public static String retrieveIPAddress(HttpServletRequest request) {
        String ipAddress = request.getHeader("X-Forward-For");
        if (StringUtils.isEmpty(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return !StringUtils.isEmpty(ipAddress) ? ipAddress : null;
    }

    public static boolean isValidEmail(String emailAddress) {
        String regexPattern = "^(.+)@(\\S+)$";
        return patternMatches(emailAddress, regexPattern);
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

}
