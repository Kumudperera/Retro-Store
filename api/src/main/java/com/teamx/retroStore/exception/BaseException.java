package com.teamx.retroStore.exception;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.exception.impl.AppsErrorMessage;

import java.util.List;

public interface BaseException {

    List<AppsErrorMessage> getAppsErrorMessages();

    int getHttpStatus();

    AppsConstants.ResponseStatus getResponseStatus();

    Boolean containsErrorCode(String errorCode);

}