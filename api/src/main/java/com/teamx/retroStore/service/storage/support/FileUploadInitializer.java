package com.teamx.retroStore.service.storage.support;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.service.common.AzureStorageService;
import lombok.Setter;

public abstract class FileUploadInitializer {

    @Setter
    protected AzureStorageService azureStorageService;

    @Setter
    protected DomainConstants.UploadFileType uploadFileType;

    public abstract void storeWithUUID() throws AppsException;

    public abstract void uploadFile() throws AppsException;

    public abstract void publicUploadFile() throws AppsException;

    public abstract String getUUID();

}
