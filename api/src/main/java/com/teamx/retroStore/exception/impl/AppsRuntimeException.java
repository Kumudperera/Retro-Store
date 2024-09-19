package com.teamx.retroStore.exception.impl;

import com.teamx.retroStore.exception.BaseException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static com.teamx.retroStore.common.constant.AppsConstants.ResponseStatus;

public class AppsRuntimeException extends RuntimeException implements Serializable, BaseException {
    private static final long serialVersionUID = -681310959579726395L;

    private final int INTERNAL_SERVER_ERROR = 500;
    private final int BAD_REQUEST = 400;
    private final int HTTP_OK = 200;

    private List<AppsErrorMessage> appsErrorMessages = new ArrayList<>();
    private int httpStatus = HTTP_OK;
    private ResponseStatus responseStatus = ResponseStatus.FAILED;

    public AppsRuntimeException() {
    }

    public AppsRuntimeException(Throwable cause, String errorCode, String errorMessage, ResponseStatus responseStatus) {
        super(errorCode, cause);
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode, errorMessage));
        this.httpStatus = httpStatus;
        this.responseStatus = responseStatus;
    }

    public AppsRuntimeException(String errorCode, String errorMessage, ResponseStatus responseStatus) {
        super(errorCode);
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode, errorMessage));
        this.responseStatus = responseStatus;
    }

    public AppsRuntimeException(String errorCode, ResponseStatus responseStatus) {
        super(errorCode);
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode));
        this.responseStatus = responseStatus;
    }

    public AppsRuntimeException(String errorCode) {
        super(errorCode);
        this.appsErrorMessages.add(new AppsErrorMessage(errorCode));
        this.responseStatus = ResponseStatus.FAILED;
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

    @Override
    public int getHttpStatus() {
        return httpStatus;
    }

    public void markExceptionAsInternalServerError() {
        this.httpStatus = this.INTERNAL_SERVER_ERROR;
    }

    @Override
    public ResponseStatus getResponseStatus() {
        return responseStatus;
    }


    public void setResponseStatus(ResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    public void setBadRequest() {
        this.httpStatus = BAD_REQUEST;
    }

    @Override
    public Boolean containsErrorCode(String errorCode) {
        for (AppsErrorMessage appsErrorMessage : appsErrorMessages) {
            if (appsErrorMessage.getErrorCode() == errorCode) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AppsRuntimeException{");
        sb.append("appsErrorMessages=").append(appsErrorMessages);
        sb.append(", httpStatus=").append(httpStatus);
        sb.append(", responseStatus=").append(responseStatus);
        sb.append('}');
        return sb.toString();
    }
}
