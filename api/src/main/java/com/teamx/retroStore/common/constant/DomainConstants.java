package com.teamx.retroStore.common.constant;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class DomainConstants {

    public enum UserType {
        ADMIN_USER, COMPANY_USER, TERMINAL_USER;
    }

    public enum EmailSendType {
        TEXT, HTML
    }

    public enum EmailPurpose {
        OTHER, REPORT, NOTIFICATION
    }

    public enum UploadFileType {
        IMAGE,
        PDF,
        VIDEO,
        SIGNATURE
    }

    public enum PrivilegeModule {
        ADMIN, COMMON, CLIENT;
    }

    public enum FileFormat {
        JPG(".jpg"),
        PNG(".png"),
        PDF(".pdf"),
        XLSX(".xlsx");

        private String description;

        FileFormat(String description) {
            this.description = description;
        }

        public static FileFormat resolveStatus(String fileFormatStr) {
            FileFormat status = null;
            if (!StringUtils.isEmpty(fileFormatStr)) {
                status = FileFormat.valueOf(fileFormatStr.trim());
            }
            return status;
        }

        public String getDescription() {
            return description;
        }
    }

    public enum PasswordUpdateAction {
        UPDATE, RESET, FORGOT
    }
}
