package com.retroStore.service.storage.support.pdf;

import com.retroStore.common.constant.DomainConstants;
import com.retroStore.exception.impl.AppsException;
import com.retroStore.exception.impl.RetroStoreErrorCode;
import com.retroStore.security.dto.CredentialsDTO;
import com.retroStore.service.common.AzureStorageService;
import com.retroStore.service.storage.support.FileUploadInitializer;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

public class PdfuploadInitializer extends FileUploadInitializer {

    private MultipartFile file;
    private List<String> allowedFileTypes;
    private CredentialsDTO credentialsDTO;
    private String savedUUID;
    private AzureStorageService azureStorageService;
    private DomainConstants.UploadFileType uploadFileType;
    private String publicUUID;

    public PdfuploadInitializer(MultipartFile file, List<String> allowedFileTypes, CredentialsDTO credentialsDTO) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.credentialsDTO = credentialsDTO;
    }

    public PdfuploadInitializer(MultipartFile file, List<String> allowedFileTypes, String uuid) {
        this.file = file;
        this.allowedFileTypes = allowedFileTypes;
        this.publicUUID = uuid;
    }

    @Override
    public void storeWithUUID() throws AppsException {

        String fileExtension = "";
        String uuid = UUID.randomUUID().toString();
        String filename = StringUtils.cleanPath(this.file.getOriginalFilename());

        if (this.file.isEmpty()) {
            throw new AppsException(RetroStoreErrorCode.APPS_EXCEPTION_EMPTY_FILE_STORAGE);
        }
        if (filename.contains("..")) {
            throw new AppsException(RetroStoreErrorCode.APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_SAVE_RELATIVE_PATH);
        }
        fileExtension = DomainConstants.FileFormat.PDF.getDescription();
        this.savedUUID = uuid + fileExtension;
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

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    public void setAwsStorageService(AzureStorageService azureStorageService) {
        this.azureStorageService = azureStorageService;
    }

    public void setUploadFileType(DomainConstants.UploadFileType uploadFileType) {
        this.uploadFileType = uploadFileType;
    }
}
