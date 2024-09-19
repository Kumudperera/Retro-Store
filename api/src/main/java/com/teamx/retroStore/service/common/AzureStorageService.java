package com.teamx.retroStore.service.common;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

@Service
public class AzureStorageService {
    private static final Logger LOG = LoggerFactory.getLogger(AzureStorageService.class);

    private BlobServiceClient blobServiceClient;

    private BlobContainerClient blobContainerClient;

    @Value("${apps.azure.storage.container.name}")
    private String containerName;

    @Value("${apps.azure.storage.connection.string}")
    private String connectionString;

    @Value("${apps.azure.endpoint.url}")
    private String endpointUrl;

    @PostConstruct
    private void initializeAzureClient() {
        BlobServiceClient serviceClient = new BlobServiceClientBuilder()
                .connectionString(connectionString).buildClient();
        this.blobServiceClient = serviceClient;

        this.blobContainerClient = serviceClient.getBlobContainerClient(containerName);
    }

    public String uploadFile(MultipartFile multipartFile, String fileName, DomainConstants.UploadFileType uploadFileType, CredentialsDTO credentialsDTO) throws AppsException {
        LOG.info("START : Azure file upload : {} by user : {}", fileName, credentialsDTO.getID());
        String fileUrl = "";
        String filePath = "";
        try {
            File file = new File(multipartFile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(file);
            FileInputStream fis = new FileInputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            switch (uploadFileType) {
                case IMAGE:
                    filePath = "images/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case SIGNATURE:
                    filePath = "signatures/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case PDF:
                    filePath = "pdf/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case VIDEO:
                    filePath = "video/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
            }

            BlobClient blob = blobContainerClient.getBlobClient(filePath);
            blob.upload(fis, false);
            file.delete();
        } catch (Exception e) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_UPLOAD_FAILED);
        }

        LOG.info("END : Azure file upload : {} by user : {}", fileName, credentialsDTO.getID());
        return fileUrl;
    }

    public String publicFileUpload(MultipartFile multipartFile, String fileName, DomainConstants.UploadFileType uploadFileType, String uuid) throws AppsException {
        LOG.info("START : Azure public file upload : {} by public : {}", fileName, uuid);
        String fileUrl = "";
        String filePath = "";
        try {
            File file = new File(multipartFile.getOriginalFilename());
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(multipartFile.getBytes());
            fos.close();

            switch (uploadFileType) {
                case IMAGE:
                    filePath = "publicUploads/images/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case SIGNATURE:
                    filePath = "publicUploads/signatures/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case PDF:
                    filePath = "publicUploads/pdf/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;
                case VIDEO:
                    filePath = "publicUploads/video/".concat(fileName);
                    fileUrl = endpointUrl.concat("/").concat(this.containerName).concat(filePath);
                    break;

            }
            file.delete();
        } catch (Exception e) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_UPLOAD_FAILED);
        }

        LOG.info("END : Azure public file upload : {} by public : {}", fileName, uuid);
        return fileUrl;
    }
    
    public byte[] downloadFile(String key, CredentialsDTO credentialsDTO) throws AppsException {
        LOG.info("START : Azure file download : {} by user : {}", key, credentialsDTO.getID());
        byte[] bytes;
        try {
            BlobClient client = blobContainerClient.getBlobClient(key);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            client.downloadStream(outputStream);
            bytes = outputStream.toByteArray();
        } catch (Exception e) {
            LOG.error("Error : Azure file download : {} by user : {} failed", key, credentialsDTO.getID(), e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_DOWNLOAD_FAILED, e);
        }
        LOG.info("END : Azure file download : {} by user : {}", key, credentialsDTO.getID());

        return bytes;
    }
}
