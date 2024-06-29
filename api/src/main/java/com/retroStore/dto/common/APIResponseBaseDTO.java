package com.retroStore.dto.common;

import com.retroStore.common.constant.AppsConstants;
import com.retroStore.exception.impl.AppsErrorMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class APIResponseBaseDTO implements Serializable {

    private List<AppsErrorMessage> appsErrorMessages = new ArrayList<>();

    private String message;

    private AppsConstants.ResponseStatus status;

    private boolean success;

    public List<AppsErrorMessage> getAppsErrorMessages() {
        if (appsErrorMessages == null) {
            appsErrorMessages = new ArrayList<>();
        }
        return appsErrorMessages;
    }

    public void setAppsErrorMessages(List<AppsErrorMessage> appsErrorMessages) {
        this.appsErrorMessages = appsErrorMessages;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public AppsConstants.ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.ResponseStatus status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    @Override
    public String toString() {
        return "APIResponseBaseDTO{" +
                "appsErrorMessages=" + appsErrorMessages +
                ", message='" + message + '\'' +
                ", status=" + status +
                ", success=" + success +
                '}';
    }
}
