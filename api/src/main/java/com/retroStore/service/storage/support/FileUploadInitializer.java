package com.retroStore.service.storage.support;

import com.retroStore.exception.impl.AppsException;

public abstract class FileUploadInitializer {

    public abstract void storeWithUUID() throws AppsException;

    public abstract void uploadFile() throws AppsException;

    public abstract void publicUploadFile() throws AppsException;

    public abstract String getUUID();
}
