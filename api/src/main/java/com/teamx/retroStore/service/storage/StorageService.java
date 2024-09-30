package com.teamx.retroStore.service.storage;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dto.common.UploadFileRQ;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.common.AzureStorageService;
import com.teamx.retroStore.service.storage.support.FileUploadInitializer;
import com.teamx.retroStore.service.storage.support.image.ImageUploadInitializer;
import com.teamx.retroStore.service.storage.support.pdf.PdfuploadInitializer;
import com.teamx.retroStore.service.storage.support.video.VideouploadInitializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class StorageService implements EnvironmentAware {

    private static Logger LOG = LoggerFactory.getLogger(StorageService.class);

    @Autowired
    private AzureStorageService azureStorageService;

    private Environment environment;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public String storeFile(UploadFileRQ fileRQ) throws AppsException {

        MultipartFile file = fileRQ.getFile();
        DomainConstants.UploadFileType uploadFileType = fileRQ.getFileType();
        CredentialsDTO credentialsDTO = fileRQ.getCredentialsDTO();
        String fileName = fileRQ.getFileName();

        LOG.info("START : Storing the file: {} by user {}", file, credentialsDTO.getId());

        if (file == null || file.isEmpty()) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_CANNOT_BE_EMPTY);
        }
        String allowedFileTypeStr = this.environment.getProperty("apps.retroStore.allowed.image.extensions") + ","
                + this.environment.getProperty("apps.retroStore.allowed.video.extensions") + ","
                + this.environment.getProperty("apps.retroStore.allowed.document.extensions");

        List<String> allowedFileTypes = new ArrayList<>();

        if (!StringUtils.isEmpty(allowedFileTypeStr)) {
            allowedFileTypes = Arrays.asList(allowedFileTypeStr.split(","));
            allowedFileTypes.removeIf(String::isEmpty);
        }

        FileUploadInitializer fileUploadInitializer;
        switch (uploadFileType) {
            case SIGNATURE:
            case IMAGE:
                fileUploadInitializer = new ImageUploadInitializer(file, allowedFileTypes, credentialsDTO, fileName);
                break;
            case PDF:
                fileUploadInitializer = new PdfuploadInitializer(file, allowedFileTypes, credentialsDTO);
                break;
            case VIDEO:
            default:
                fileUploadInitializer = new VideouploadInitializer(file, allowedFileTypes, credentialsDTO);
                break;
        }

        fileUploadInitializer.setAzureStorageService(this.azureStorageService);
        fileUploadInitializer.setUploadFileType(uploadFileType);

        fileUploadInitializer.storeWithUUID();
        fileUploadInitializer.uploadFile();

        return fileUploadInitializer.getUUID();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public String storeContractorFile(MultipartFile file, DomainConstants.UploadFileType uploadFileType, String uuid) throws AppsException {
        LOG.info("START : Storing the file: {} by public contractor : {}", file.getOriginalFilename(), uuid);

        if (file == null || file.isEmpty()) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_CANNOT_BE_EMPTY);
        }
        String allowedFileTypeStr = this.environment.getProperty("apps.retroStore.allowed.image.extensions") + ","
                + this.environment.getProperty("apps.retroStore.allowed.video.extensions") + ","
                + this.environment.getProperty("apps.retroStore.allowed.document.extensions");

        List<String> allowedFileTypes = new ArrayList<>();

        if (!StringUtils.isEmpty(allowedFileTypeStr)) {
            allowedFileTypes = Arrays.asList(allowedFileTypeStr.split(","));
            allowedFileTypes.removeIf(String::isEmpty);
        }

        FileUploadInitializer fileUploadInitializer;
        switch (uploadFileType) {
            case IMAGE:
                fileUploadInitializer = new ImageUploadInitializer(file, allowedFileTypes, uuid);
                ((ImageUploadInitializer) fileUploadInitializer).setAzureStorageService(this.azureStorageService);
                ((ImageUploadInitializer) fileUploadInitializer).setUploadFileType(DomainConstants.UploadFileType.IMAGE);
                break;
            case PDF:
                fileUploadInitializer = new PdfuploadInitializer(file, allowedFileTypes, uuid);
                ((PdfuploadInitializer) fileUploadInitializer).setAzureStorageService(this.azureStorageService);
                ((PdfuploadInitializer) fileUploadInitializer).setUploadFileType(DomainConstants.UploadFileType.PDF);
                break;

            case VIDEO:
            default:
                fileUploadInitializer = new VideouploadInitializer(file, allowedFileTypes, uuid);
                ((VideouploadInitializer) fileUploadInitializer).setAzureStorageService(this.azureStorageService);
                ((VideouploadInitializer) fileUploadInitializer).setUploadFileType(DomainConstants.UploadFileType.VIDEO);
                break;
        }

        fileUploadInitializer.storeWithUUID();
        fileUploadInitializer.publicUploadFile();

        return fileUploadInitializer.getUUID();
    }

    public byte[] downloadFileFromAWS(String fileName, CredentialsDTO credentialsDTO) throws AppsException {
        byte[] fileContent = null;
        fileContent = this.azureStorageService.downloadFile(fileName, credentialsDTO);

        return fileContent;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
