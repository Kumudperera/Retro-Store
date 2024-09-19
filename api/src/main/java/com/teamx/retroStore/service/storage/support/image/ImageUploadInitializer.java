package com.teamx.retroStore.service.storage.support.image;

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

public class ImageUploadInitializer extends FileUploadInitializer {

    private MultipartFile file;
    private List<String> allowedFileTypes;
    private CredentialsDTO credentialsDTO;
    private String savedUUID;
    private String publicUUID;
    private AzureStorageService azureStorageService;
    private DomainConstants.UploadFileType uploadFileType;

    private String fileName;

    public ImageUploadInitializer(MultipartFile file, List<String> allowedFileTypes, CredentialsDTO credentialsDTO, String fileName) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.credentialsDTO = credentialsDTO;
        this.fileName = fileName;
    }

    public ImageUploadInitializer(MultipartFile file, List<String> allowedFileTypes, String uuid) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.publicUUID = uuid;
    }

    @Override
    public void storeWithUUID() throws AppsException {

        String uuid = UUID.randomUUID().toString();
        String filename = StringUtils.cleanPath(this.file.getOriginalFilename());

        if (this.file.isEmpty()) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_EMPTY_FILE_STORAGE);
        }
        if (filename.contains("..")) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_SAVE_RELATIVE_PATH);
        }
        String fileFormat = DomainConstants.FileFormat.JPG.getDescription();

        if (this.fileName != null) {
            String[] parts = this.fileName.split("\\.");
            if (parts.length > 0) {
                this.savedUUID = parts[0] + fileFormat;
            } else {
                this.savedUUID = uuid + fileFormat;
            }
        } else {
            this.savedUUID = uuid + fileFormat;
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

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public void setAllowedFileTypes(List<String> allowedFileTypes) {
        this.allowedFileTypes = allowedFileTypes;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    public void setAzureStorageService(AzureStorageService azureStorageService) {
        this.azureStorageService = azureStorageService;
    }

    public void setUploadFileType(DomainConstants.UploadFileType uploadFileType) {
        this.uploadFileType = uploadFileType;
    }


}
