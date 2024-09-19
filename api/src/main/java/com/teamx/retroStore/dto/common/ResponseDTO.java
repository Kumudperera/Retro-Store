package com.teamx.retroStore.dto.common;

import com.teamx.retroStore.exception.impl.AppsErrorMessage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.teamx.retroStore.common.constant.AppsConstants.ResponseStatus;

public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = -1202969352494493961L;

    private List<AppsErrorMessage> appsErrorMessages = new ArrayList<>();
    private T result;
    private ResponseStatus status;

    public ResponseDTO() {
    }

    public List<AppsErrorMessage> getAppsErrorMessages() {
        return appsErrorMessages;
    }

    public void setAppsErrorMessages(List<AppsErrorMessage> appsErrorMessages) {
        this.appsErrorMessages = appsErrorMessages;
    }

    public void addError(String errorCode) {
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode));
    }

    public void addError(String errorCode, String errorMessage) {
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode, errorMessage));
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ResponseStatus getStatus() {
        return status;
    }

    public void setStatus(ResponseStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ResponseDTO{");
        sb.append("status=").append(status);
        sb.append(", appsErrorMessages=").append(appsErrorMessages);
        sb.append(", result=").append(result);
        sb.append('}');
        return sb.toString();
    }
}
