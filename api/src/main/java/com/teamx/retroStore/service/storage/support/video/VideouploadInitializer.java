package com.teamx.retroStore.service.storage.support.video;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.common.AzureStorageService;
import com.teamx.retroStore.service.storage.support.FileUploadInitializer;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class VideouploadInitializer extends FileUploadInitializer {

    private MultipartFile file;
    private List<String> allowedFileTypes;
    private CredentialsDTO credentialsDTO;
    private String savedUUID;
    private String publicUUID;
    private AzureStorageService azureStorageService;
    private DomainConstants.UploadFileType uploadFileType;

    public VideouploadInitializer(MultipartFile file, List<String> allowedFileTypes, CredentialsDTO credentialsDTO) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.credentialsDTO = credentialsDTO;
    }

    public VideouploadInitializer(MultipartFile file, List<String> allowedFileTypes, String publicUUID) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.publicUUID = publicUUID;
    }

    @Override
    public void storeWithUUID() throws AppsException {

        String fileExtension = "";
        String uuid = UUID.randomUUID().toString();
        String filename = StringUtils.cleanPath(this.file.getOriginalFilename());

        if (this.file.isEmpty()) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_EMPTY_FILE_STORAGE);
        }
        if (filename.contains("..")) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_SAVE_RELATIVE_PATH);
        }
        String[] fileNameSplit = filename.split("\\.");
        if (fileNameSplit.length > 1) {
            fileExtension = fileNameSplit[fileNameSplit.length - 1];
            this.savedUUID = uuid + "." + fileExtension;
        }
        if (!this.allowedFileTypes.isEmpty() && !this.allowedFileTypes.contains(fileExtension)) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_INVALID_FILE_EXTENSION);
        }

    }

    @Override
    public void uploadFile() throws AppsException {
        this.azureStorageService.uploadFile(this.file, this.savedUUID, this.uploadFileType, this.credentialsDTO);
    }

    @Override
    public void publicUploadFile() throws AppsException {
        this.azureStorageService.publicFileUpload(this.file, this.savedUUID, this.uploadFileType, this.publicUUID);
    }

    @Override
    public String getUUID() {
        return savedUUID;
    }

    public MultipartFile getFile() {
        return file;
    }


    public CredentialsDTO getCredentialsDTO() {
        return credentialsDTO;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    public void setAzureStorageService(AzureStorageService azureStorageService) {
        this.azureStorageService = azureStorageService;
    }

    public DomainConstants.UploadFileType getUploadFileType() {
        return uploadFileType;
    }

    public void setUploadFileType(DomainConstants.UploadFileType uploadFileType) {
        this.uploadFileType = uploadFileType;
    }
}
