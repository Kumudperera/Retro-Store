package com.teamx.retroStore.dto.common;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class UploadFileRQ implements Serializable {

    private MultipartFile file;
    private DomainConstants.UploadFileType fileType;
    private String fileName;

    private CredentialsDTO credentialsDTO;

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public DomainConstants.UploadFileType getFileType() {
        return fileType;
    }

    public void setFileType(DomainConstants.UploadFileType fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public CredentialsDTO getCredentialsDTO() {
        return credentialsDTO;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }
}
