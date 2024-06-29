package com.retroStore.exception;

import com.retroStore.common.constant.AppsConstants;
import com.retroStore.exception.impl.AppsErrorMessage;

import java.util.List;

public interface BaseException {

    List<AppsErrorMessage> getAppsErrorMessages();

    int getHttpStatus();

    AppsConstants.ResponseStatus getResponseStatus();

    Boolean containsErrorCode(String errorCode);

}