package com.teamx.retroStore.service.master.support;

import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.security.service.SecurityService;
import com.teamx.retroStore.service.master.UserService;
import com.teamx.retroStore.service.notification.support.EmailService;

public abstract class ChangePasswordHandler {

    private UserDao userDao;
    private SecurityService securityService;

    private EmailService emailService;

    public abstract void validateUser() throws AppsException;

    public abstract void updatePassword();

    public abstract void notifyUser() throws AppsException;

    public abstract void expireUserCache();

    public abstract UserDTO getUserDTO();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
