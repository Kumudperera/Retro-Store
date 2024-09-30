package com.teamx.retroStore.common.constant;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;

public class DomainConstants {

    public enum UserType {
        ADMIN_USER, COMPANY_USER, TERMINAL_USER;
    }

    public enum StorageUnits {
        GB;
    }

    @Getter
    public enum StoragePackagePeriods {
        MONTHLY(1);

        StoragePackagePeriods(int noOfMonths) {
            this.noOfMonths = noOfMonths;
        }
        private int noOfMonths;
    }

    public enum LicenseType {
        PAID;
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
