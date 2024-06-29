package com.retroStore.exception.impl;

public abstract class RetroStoreErrorCode {


    public static final String APPS_INVALID_REQUEST = "apps.invalid.request";
    public static final String APPS_INVALID_ARGUMENTS = "apps.invalid.arguments";

    public static final String APPS_EXCEPTION_USER_ALREADY_EXISTS = "apps.exception.user.already.exist";
    public static final String APPS_EXCEPTION_USER_DOES_NOT_EXIST = "apps.exception.user.does.not.exist";
    public static final String APPS_EXCEPTION_EMAIL_ALREADY_EXISTS = "apps.exception.email.already.exist";
    public static final String APPS_EXCEPTION_PASSWORD_MISMATCH = "apps.exception.password.mismatch";
    public static final String APPS_EXCEPTION_ROLE_ALREADY_EXISTS = "apps.exception.role.already.exist";
    public static final String APPS_EXCEPTION_ROLE_COMPANY_NOT_FOUND = "apps.exception.role.company.not.found";
    public static final String APPS_EXCEPTION_LOCATION_HAS_ALLOCATED_USERS = "apps.exception.location.has.allocated.users";
    public static final String APPS_EXCEPTION_USER_GROUP_HAS_ALLOCATED_USERS = "apps.exception.user.group.has.allocated.users";

    public static final String APPS_EXCEPTION_FILE_UPLOAD_FAILED = "apps.exception.file.upload.failed";
    public static final String APPS_EXCEPTION_FILE_DOWNLOAD_FAILED = "apps.exception.file.download.failed";
    public static final String APPS_EXCEPTION_EMPTY_FILE_STORAGE = "apps.exception.empty.file.storage";
    public static final String APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_SAVE_RELATIVE_PATH = "apps.exception.file.storage.failed.to.save.relative.path";
    public static final String APPS_EXCEPTION_INVALID_FILE_EXTENSION = "apps.exception.invalid.file.extension";

    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_FAILED = "apps.exception.system.email.failed";
    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_FAILED_MAILBOX_FULL = "apps.exception.system.email.failed.mailbox.full";
    public static final String APPS_EXCEPTION_SYSTEM_EMAIL_TO_ADDRESS_NOT_FOUND = "apps.exception.system.email.to.address.not.found";

    public static final String APPS_EXCEPTION_COMPLIANCE_CODE_ALREADY_EXISTS = "apps.exception.compliance.code.already.exists";
    public static final String APPS_EXCEPTION_COMPLIANCE_MODULE_NEEDED = "apps.exception.compliance.module.needed";
    public static final String APPS_EXCEPTION_ATTACHMENT_UPLOAD_FAILED = "apps.exception.attachment.upload.failed";

    public static final String APPS_EXCEPTION_INVALID_STATUS_TRANSITION = "apps.exception.invalid.status.transition";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_COMPANY_NOT_FOUND = "apps.exception.roadmap.module.company.not.found";
    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_NOT_FOUND = "apps.exception.company.roadmap.not.found";
    public static final String APPS_EXCEPTION_IN_PROGRESS_COMPANY_ROADMAP_NOT_FOUND = "apps.exception.in.progress.company.roadmap.not.found";
    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_STATUS_NOT_FOUND = "apps.exception.company.roadmap.status.not.found";

    public static final String APPS_EXCEPTION_FILE_CANNOT_BE_EMPTY = "apps.exception.file.cannot.be.empty";

    public static final String APPS_EXCEPTION_ROADMAP_COMPANY_NOT_FOUND = "apps.exception.roadmap.company.not.found";
    public static final String APPS_EXCEPTION_ROADMAP_COMPANY_USER_NOT_FOUND = "apps.exception.roadmap.company.user.not.found";

    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_INVALID_FILE_TYPE = "apps.exception.user.bulk.upload.invalid.file.type";
    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_INVALID_FILE_HEADERS = "apps.exception.user.bulk.upload.invalid.file.headers";
    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_INVALID_FILE = "Invalid file. Please check and upload the correct template.";
    public static final String APPS_EXCEPTION_USER_INVALID_EMAIL = "apps.exception.user.email.invalid";
    public static final String APPS_EXCEPTION_USER_NULL_FIRSTNAME = "apps.exception.user.firstname.null";
    public static final String APPS_EXCEPTION_USER_NULL_LASTNAME = "apps.exception.user.lastname.null";
    public static final String APPS_EXCEPTION_USER_NULL_USERNAME = "apps.exception.user.username.null";
    public static final String APPS_EXCEPTION_USER_NULL_USERTYPE = "apps.exception.user.usertype.null";
    public static final String APPS_EXCEPTION_USER_INVALID_STATUS = "apps.exception.user.invalid.status";

    public static final String APPS_EXCEPTION_COMPANY_EMPTY_DEFAULT_COMPANY_LOCATION = "apps.exception.company.empty.default.company.location";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_DOES_NOT_EXIST = "apps.exception.roadmap.module.does.not.exist";
    public static final String APPS_EXCEPTION_ROADMAP_MODULE_QUESTION_DOES_NOT_EXIST = "apps.exception.roadmap.module.question.does.not.exist";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_DOCUMENT_NOT_FOUND = "apps.exception.roadmap.module.document.not.found";
    public static final String APPS_EXCEPTION_DEFAULT_ROADMAP_MODULE_DOCUMENT_NOT_FOUND = "apps.exception.default.roadmap.module.document.not.found";
    public static final String APPS_EXCEPTION_ACTIVE_ROADMAP_MODULE_DOCUMENT_NOT_FOUND = "apps.exception.active.roadmap.module.document.not.found";
    public static final String APPS_EXCEPTION_CURRENT_PASSWORD_MISMATCH = "apps.exception.current.password.mismatch";

    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_MODULE_USER_STATUS_NOT_FOUND = "apps.exception.company.roadmap.module.user.status.not.found";

    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_MODULE_NOT_FOUND = "apps.exception.company.roadmap.module.not.found";

    public static final String APPS_EXCEPTION_FORGET_PASSWORD_USER_CANNOT_FIND = "apps.exception.forget.password.no.user.found";
    public static final String APPS_EXCEPTION_FORGET_PASSWORD_LINK_EXPIRED = "apps.exception.forget.password.link.expired";
    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_EMPTY_DATA = "apps.exception.user.bulk.upload.empty.data";
    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_CONTAINS_EXTRA_DATA = "apps.exception.user.bulk.upload.contains.extra.data";
    public static final String APPS_EXCEPTION_USER_BULK_UPLOAD_CONTAINS_EMPTY_FIELDS = "apps.exception.user.bulk.upload.contains.empty.fields";

    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_MODULE_QUESTION_USER_ANSWER_WRONG = "apps.exception.company.roadmap.module.question.user.answer.wrong";

    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_MODULE_DOCUMENT_ID_NOT_FOUND = "apps.exception.company.roadmap.module.document.id.not.found";
    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_MODULE_DOCUMENT_NOT_FOUND = "apps.exception.company.roadmap.module.document.not.found";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_DOCUMENTS_MAX_NUM_OF_VERSIONS_EXCEEDED = "apps.exception.roadmap.module.document.versions.max.num.of.versions.exceeded";
    public static final String APPS_EXCEPTION_COMPANY_ROADMAP_IN_PROGRESS_ALREADY_EXISTS = "apps.exception.company.roadmap.in.progress.already.exists";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_NAME_ALREADY_EXISTS = "apps.exception.roadmap.module.name.already.exists";

    public static final String APPS_EXCEPTION_ROADMAP_MODULE_DOCUMENT_NAME_ALREADY_EXISTS = "apps.exception.roadmap.module.document.name.already.exists";

    public static final String APPS_EXCEPTION_COMPANY_USER_GROUP_COMPANY_NOT_FOUND = "apps.exception.company.user.group.not.found";

    public static final String APPS_EXCEPTION_LR_CATEGORY_ALREADY_EXISTS_WITH_CATEGORY_CODE = "apps.exception.lr.category.already.exists.with.category.code";
    public static final String APPS_EXCEPTION_LR_CATEGORY_NOT_FOUND = "apps.exception.lr.category.not.found";
    public static final String APPS_EXCEPTION_LR_DOCUMENT_SAVE_FAILED = "apps.exception.lr.document.save.failed";

    public static final String APPS_EXCEPTION_ACTIONS_OR_INVESTIGATIONS_IN_PROGRESS = "apps.exception.actions_or.investigations.in.progress";
    public static final String APPS_EXCEPTION_COMPANY_CONTRACTOR_NOT_FOUND = "apps.exception.company.contractor.not.found";
    public static final String APPS_COMPANY_CONTRACTOR_LINK_EXPIRED = "apps.exception.company.contractor.link.expired";
    public static final String APPS_COMPANY_CONTRACTOR_TOKEN_FAILED = "apps.exception.company.contractor.token.failed";
    public static final String APPS_COMPANY_CONTRACTOR_ALREADY_REGISTERED = "apps.exception.company.contractor.already.registered";
    public static final String APPS_COMPANY_CONTRACTOR_EMAIL_EXISTS = "apps.exception.company.contractor.email.exists";
    public static final String APPS_COMPANY_CONTRACTOR_EXTERNAL_ID_EXISTS = "apps.exception.company.contractor.external.id.exists";

    public static final String APPS_EXCEPTION_CUSTOM_DOCUMENTS_NOT_FOUND = "apps.exception.custom.documents.not.found";

    public static final String APPS_EXCEPTION_INVESTIGATION_IS_IN_PROGRESS = "apps.exception.investigation.is.in.progress";

    public static final String APPS_EXCEPTION_OBJECTIVE_REGISTRATION_RECORD_NOT_FOUND = "apps.exception.objective.registration.record.not.found";
    public static final String APPS_EXCEPTION_OBJECTIVE_REGISTRATION_INVALID_DURATION = "apps.exception.objective.registration.invalid.duration";
    public static final String APPS_EXCEPTION_OBJECTIVE_REGISTRATION_INVALID_FREQUENCY = "apps.exception.objective.registration.invalid.frequency";

    public static final String APPS_EXCEPTION_CANNOT_UPDATE_INCIDENT = "apps.exception.cannot.update.incident";

    public static final String APPS_EXCEPTION_REGISTERS_COMPANY_NOT_FOUND = "apps.exception.register.company.not.found";
    public static final String APPS_EXCEPTION_NOTIFY_CONFIG_COMPANY_NOT_FOUND = "apps.exception.notify.config.company.not.found";
    public static final String APPS_EXCEPTION_RISK_ASSESSMENT_COMPANY_NOT_FOUND = "apps.exception.risk.assessment.company.not.found";
    public static final String APPS_EXCEPTION_COMPANY_LOCATION_COMPANY_NOT_FOUND = "apps.exception.company.location.company.not.found";

    public static final String APPS_EXCEPTION_PLANT_RISK_ASSESSMENT_ACCESSED_ITEM_NOT_FOUND = "apps.exception.plant.risk.assessment.accessed.item.not.found";
    public static final String APPS_EXCEPTION_PLANT_RISK_ASSESSMENT_LOCATION_NOT_FOUND = "apps.exception.plant.risk.assessment.location.not.found";

    public static final String APPS_EXCEPTION_CHEMICAL_NAME_ALREADY_EXISTS = "apps.exception.chemical.name.already.exists";

    public static final String APPS_EXCEPTION_ACTIONS_OR_ASSESSMENT_IN_PROGRESS = "apps.exception.actions_or.assessment.in.progress";

    public static final String APPS_EXCEPTION_SWMS_STATUS_UPDATE_ERROR = "apps.exception.swms.status.update.error";

    public static final String APPS_EXCEPTION_INVALID_ACTIVITY_LOG_STATUS = "apps.exception.invalid.activity.log.status";
    public static final String APPS_EXCEPTION_REPORT_TEMPLATE_FILE_NOT_FOUND = "apps.exception.report.template.file.not.found";

    public static final String APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR = "apps.report.engine.jasper.internal.error";

    public static final String APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_READ = "apps.exception.file.storage.failed.to.read.file";

}
