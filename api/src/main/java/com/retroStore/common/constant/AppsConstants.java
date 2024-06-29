package com.retroStore.common.constant;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

public class AppsConstants {

    public enum ResponseStatus {
        SUCCESS, FAILED;

        public static ResponseStatus resolveStatus(String statusStr) {
            ResponseStatus matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = ResponseStatus.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }
    }

    public enum YesNo {

        Y("Yes", true, 1), N("No", false, 0);

        private String strVal;

        private Boolean boolVal;

        private Integer intVal;

        YesNo(String strVal, Boolean boolVal, Integer intVal) {
            this.strVal = strVal;
            this.boolVal = boolVal;
            this.intVal = intVal;
        }

        public static YesNo valueOf(Boolean boolVal) {
            YesNo matchingStatus = null;
            for (YesNo yesno : YesNo.values()) {
                if (yesno.getBoolVal().equals(boolVal)) {
                    matchingStatus = yesno;
                    break;
                }
            }
            return matchingStatus;
        }

        public static Map<String, String> getYesNoMap() {
            Map<String, String> map = new LinkedHashMap<String, String>();
            for (YesNo yesNo : YesNo.values()) {
                map.put(yesNo.toString(), yesNo.getStrVal());
            }
            return map;
        }

        public static YesNo resolveYesNo(String yesNoStr) {
            YesNo matchingYesNo = null;
            if (!StringUtils.isEmpty(yesNoStr)) {
                matchingYesNo = YesNo.valueOf(yesNoStr.trim());
            }
            return matchingYesNo;
        }

        public String getStrVal() {
            return strVal;
        }

        public Boolean getBoolVal() {
            return boolVal;
        }

        public Integer getIntVal() {
            return intVal;
        }
    }

    public enum Status {
        ACT("Active"), INA("Inactive");

        private String description;

        Status(String description) {
            this.description = description;
        }

        public static Status resolveStatus(String statusStr) {
            Status matchingStatus = null;
            if (!StringUtils.isEmpty(statusStr)) {
                matchingStatus = Status.valueOf(statusStr.trim());
            }
            return matchingStatus;
        }

        public static Map<String, String> getStatusMap() {
            Map<String, String> result = new HashMap<>();
            Arrays.asList(Status.values()).forEach(status -> result.put(status.toString(), status.getDescription()));
            return result;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum AuthorizationError {
        USER_UNAUTHORIZED("User is unauthorized for action");

        private String description;

        AuthorizationError(String description) {
            this.description = description;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PasswordUpdateAction {
        UPDATE, RESET, FORGET
    }

    public enum YesNoNA {

        YES("Yes", true, 1), NO("No", false, 0), NA("Na", false, 0);

        private String strVal;

        private Boolean boolVal;

        private Integer intVal;

        YesNoNA(String strVal, Boolean boolVal, Integer intVal) {
            this.strVal = strVal;
            this.boolVal = boolVal;
            this.intVal = intVal;
        }

        public static YesNoNA valueOf(Boolean boolVal) {
            YesNoNA matchingStatus = null;
            for (YesNoNA yesnoNa : YesNoNA.values()) {
                if (yesnoNa.getBoolVal().equals(boolVal)) {
                    matchingStatus = yesnoNa;
                    break;
                }
            }
            return matchingStatus;
        }

        public static Map<String, String> getYesNoMap() {
            Map<String, String> map = new LinkedHashMap<String, String>();
            for (YesNo yesNo : YesNo.values()) {
                map.put(yesNo.toString(), yesNo.getStrVal());
            }
            return map;
        }

        public static YesNoNA resolveYesNo(String yesNoStr) {
            YesNoNA matchingYesNo = null;
            if (!StringUtils.isEmpty(yesNoStr)) {
                matchingYesNo = YesNoNA.valueOf(yesNoStr.trim());
            }
            return matchingYesNo;
        }

        public String getStrVal() {
            return strVal;
        }

        public Boolean getBoolVal() {
            return boolVal;
        }

        public Integer getIntVal() {
            return intVal;
        }
    }
}
