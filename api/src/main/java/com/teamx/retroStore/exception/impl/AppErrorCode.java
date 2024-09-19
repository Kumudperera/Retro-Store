package com.teamx.retroStore.exception.impl;

public abstract class AppErrorCode {

    public static final String APPS_INVALID_REQUEST = "apps.invalid.request";
    public static final String APPS_INVALID_ARGUMENTS = "apps.invalid.arguments";

    public static final String APPS_EXCEPTION_USER_ALREADY_EXISTS = "apps.exception.user.already.exist";
    public static final String APPS_EXCEPTION_USER_DOES_NOT_EXIST = "apps.exception.user.does.not.exist";
    public static final String APPS_EXCEPTION_EMAIL_ALREADY_EXISTS = "apps.exception.email.already.exist";
    public static final String APPS_EXCEPTION_ROLE_ALREADY_EXISTS = "apps.exception.role.already.exist";
    public static final String APPS_EXCEPTION_FILE_UPLOAD_FAILED = "apps.exception.file.upload.failed";
    public static final String APPS_EXCEPTION_FILE_DOWNLOAD_FAILED = "apps.exception.file.download.failed";
    public static final String APPS_EXCEPTION_EMPTY_FILE_STORAGE = "apps.exception.empty.file.storage";
    public static final String APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_SAVE_RELATIVE_PATH = "apps.exception.file.storage.failed.to.save.relative.path";
    public static final String APPS_EXCEPTION_INVALID_FILE_EXTENSION = "apps.exception.invalid.file.extension";

    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_FAILED = "apps.exception.system.email.failed";
    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_FAILED_MAILBOX_FULL = "apps.exception.system.email.failed.mailbox.full";
    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_TO_ADDRESS_NOT_FOUND = "apps.exception.system.email.to.address.not.found";

    public static final String APPS_EXCEPTION_FILE_CANNOT_BE_EMPTY = "apps.exception.file.cannot.be.empty";

    public static final String APPS_EXCEPTION_CURRENT_PASSWORD_MISMATCH = "apps.exception.current.password.mismatch";

    public static final String APPS_EXCEPTION_FORGET_PASSWORD_USER_CANNOT_FIND = "apps.exception.forget.password.no.user.found";
    public static final String APPS_EXCEPTION_FORGET_PASSWORD_LINK_EXPIRED = "apps.exception.forget.password.link.expired";
    public static final String APPS_EXCEPTION_REPORT_TEMPLATE_FILE_NOT_FOUND = "apps.exception.report.template.file.not.found";

    public static final String APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR = "apps.report.engine.jasper.internal.error";

    public static final String APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_READ = "apps.exception.file.storage.failed.to.read.file";

}
