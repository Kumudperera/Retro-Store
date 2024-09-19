package com.teamx.retroStore.dto.common;

import java.io.Serializable;
import java.util.Base64;

public class FileDTO implements Serializable {

    String fileName;

    String attachmentReference;

    Boolean isPublicFile;

    String base64String;

    byte[] byteArray;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getBase64String() {
        return base64String;
    }

    public void setBase64String(String base64String) {
        this.base64String = base64String;
    }

    public void setBase64StringFromBytes(byte[] bytes) {
        this.base64String = Base64.getEncoder().encodeToString(bytes);
    }

    public byte[] getByteArray() {
        return byteArray;
    }

    public void setByteArray(byte[] byteArray) {
        this.byteArray = byteArray;
    }

    public String getAttachmentReference() {
        return attachmentReference;
    }

    public void setAttachmentReference(String attachmentReference) {
        this.attachmentReference = attachmentReference;
    }

    public Boolean getIsPublicFile() {
        return isPublicFile;
    }

    public void setIsPublicFile(Boolean isPublicFile) {
        this.isPublicFile = isPublicFile;
    }
}
