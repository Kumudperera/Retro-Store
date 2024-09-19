package com.teamx.retroStore.controller;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.model.RetroStoreUserDetails;
import com.teamx.retroStore.util.MainUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

    protected CredentialsDTO getCredentialsDTO() throws AccessDeniedException {

        CredentialsDTO credentialsDTO = new CredentialsDTO();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || authentication.getPrincipal().equals("anonymousUser")) {
            throw new AccessDeniedException(AppsConstants.AuthorizationError.USER_UNAUTHORIZED.getDescription());
        }

        RetroStoreUserDetails userDetails = (RetroStoreUserDetails) authentication.getPrincipal();

        credentialsDTO.setID(userDetails.getUserID());
        credentialsDTO.setUsername(userDetails.getUsername());
        credentialsDTO.setRequestIpAddress(MainUtil.retrieveIPAddress(request));
        credentialsDTO.setAuthorities(userDetails.getAuthorities());

        return credentialsDTO;
    }

    protected boolean isAuthorized(String privilegeCode) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        RetroStoreUserDetails retroStoreUserDetails = (RetroStoreUserDetails) authentication.getPrincipal();

        return retroStoreUserDetails.getPrivileges().contains(privilegeCode);
    }
}
