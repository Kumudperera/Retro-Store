package com.teamx.retroStore.common.audit.constants;

import org.apache.commons.lang3.StringUtils;

public class AuditConstants {

    public static final Integer AUDIT_CONTENT_MAX_LENGTH = 2500;

    public enum SystemAuditCategory {
        SETTING("SETTING");


        private String description;

        SystemAuditCategory(String description) {
            this.description = description;
        }

        public static SystemAuditCategory resolveSysAuditCategory(String sysAuditCatStr) {
            SystemAuditCategory systemAuditCategory = null;
            if (!StringUtils.isEmpty(sysAuditCatStr)) {
                systemAuditCategory = SystemAuditCategory.valueOf(sysAuditCatStr.trim());
            }
            return systemAuditCategory;
        }

        public String getDescription() {
            return description;
        }

    }

    public enum SystemAuditActionCode {
        ADD, EDIT, STATUS_UPDATED, DELETE, LOGIN, LOGOUT
    }

    public enum EntityFetchType {
        LAZY, EAGER
    }


    public enum SystemAuditSubCategory {

        USER("User");

        private String value;

        private SystemAuditSubCategory(String val) {
            value = val;
        }

        public static SystemAuditSubCategory resolveSysAuditSubCategory(String sysAuditSubCatStr) {
            SystemAuditSubCategory systemAuditSubCategory = null;
            if (!StringUtils.isEmpty(sysAuditSubCatStr)) {
                systemAuditSubCategory = SystemAuditSubCategory.valueOf(sysAuditSubCatStr.trim());
            }
            return systemAuditSubCategory;
        }

        public String getValue() {
            return value;
        }
    }

    public enum SystemAuditTemplate {

        USER_ADD("User Add", SystemAuditActionCode.ADD, SystemAuditCategory.SETTING, SystemAuditSubCategory.USER),
        USER_EDIT("User Add", SystemAuditActionCode.EDIT, SystemAuditCategory.SETTING, SystemAuditSubCategory.USER);

        private String value;
        private SystemAuditActionCode systemAuditActionCode;
        private SystemAuditCategory systemAuditCategory;
        private SystemAuditSubCategory systemAuditSubCategory;

        SystemAuditTemplate(String val,
                            SystemAuditActionCode actionCode,
                            SystemAuditCategory category,
                            SystemAuditSubCategory subCategory) {
            systemAuditActionCode = actionCode;
            systemAuditCategory = category;
            systemAuditSubCategory = subCategory;
            value = val;
        }

        public static SystemAuditTemplate resolveSysAuditTemplate(String sysAuditTemplateStr) {
            SystemAuditTemplate systemAuditTemplate = null;
            if (!StringUtils.isEmpty(sysAuditTemplateStr)) {
                systemAuditTemplate = SystemAuditTemplate.valueOf(sysAuditTemplateStr.trim());
            }
            return systemAuditTemplate;
        }

        public String getValue() {
            return value;
        }

        public SystemAuditActionCode getSystemAuditActionCode() {
            return systemAuditActionCode;
        }

        public SystemAuditCategory getSystemAuditCategory() {
            return systemAuditCategory;
        }

        public SystemAuditSubCategory getSystemAuditSubCategory() {
            return systemAuditSubCategory;
        }
    }
}
