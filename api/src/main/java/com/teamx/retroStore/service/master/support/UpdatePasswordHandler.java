package com.teamx.retroStore.service.master.support;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.dto.master.user.PasswordUpdateDTO;
import com.teamx.retroStore.dto.master.user.PasswordUpdateRQ;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import com.teamx.retroStore.service.notification.support.EmailService;
import com.teamx.retroStore.util.PasswordUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class UpdatePasswordHandler extends ChangePasswordHandler {
    private static final Logger LOG = LoggerFactory.getLogger(UpdatePasswordHandler.class);
    private PasswordUpdateRQ passwordUpdateRQ;

    private UserDao userDao;

    private SecurityService securityService;

    private UserDTO userDTO;

    private EmailService emailService;

    private User user;

    public UpdatePasswordHandler(PasswordUpdateRQ passwordUpdateRQ) {
        this.passwordUpdateRQ = passwordUpdateRQ;
    }

    public UpdatePasswordHandler() {
    }

    @Override
    public void validateUser() throws AppsException {
        LOG.info("START : validate user existing password : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());
        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();
        User user = this.userDao.getReferenceById(passwordUpdateDTO.getID());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String oldPassword = passwordUpdateDTO.getOldPassword();
        String existingPassword = user.getPassword();
        if (passwordEncoder.matches(oldPassword, existingPassword)) {
            passwordUpdateDTO.setNewPassword(this.generateEncodedPassword(this.passwordUpdateRQ.getPasswordUpdateDTO().getNewPassword()));
        } else {
            LOG.error("ERROR : password mismatch : {} : {}", passwordUpdateDTO, this.passwordUpdateRQ.getCredentialsDTO().getID());
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_CURRENT_PASSWORD_MISMATCH);
        }
        LOG.info("END : validate user existing password : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());
    }

    @Override
    public void updatePassword() {
        LOG.info("START: User password update : {}", this.passwordUpdateRQ);
        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();

        this.user = userDao.getReferenceById(passwordUpdateDTO.getID());

        this.user.setPassword(passwordUpdateDTO.getNewPassword());
        this.user.setModifiedBy(passwordUpdateDTO.getID());
        this.user.setModifiedDate(new Date());

        this.user = this.userDao.saveAndFlush(this.user);
        this.userDTO = new UserDTO(this.user);
        LOG.info("END: User password update : {}", passwordUpdateRQ);
    }

    @Override
    public void notifyUser() throws AppsException {
        EmailSendRequest emailSendRequest = new EmailSendRequest();
        emailSendRequest.setTemplateName("password_change_notification");
        emailSendRequest.setToAddresses(Collections.singletonList(this.user.getEmail()));
        emailSendRequest.setSubject("RETRO_STORE Credentials");
        emailSendRequest.setSendType(DomainConstants.EmailSendType.HTML);
        emailSendRequest.setPurpose(DomainConstants.EmailPurpose.NOTIFICATION);

        Map<String, Object> params = new HashMap<>();
        params.put("username", this.user.getUsername());
        emailSendRequest.setParams(params);

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setID(1);
        credentialsDTO.setUsername("SYSTEM");

        this.emailService.sendMailAsync(emailSendRequest, credentialsDTO);
    }

    @Override
    public void expireUserCache() {

        this.securityService.destroyUserCache();
    }

    @Override
    public UserDTO getUserDTO() {
        return this.userDTO;
    }

    public String generateEncodedPassword(String plainPassword) throws AppsException {
        return PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), plainPassword);
    }

    public void setPasswordUpdateRQ(PasswordUpdateRQ passwordUpdateRQ) {
        this.passwordUpdateRQ = passwordUpdateRQ;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public PasswordUpdateRQ getPasswordUpdateRQ() {
        return passwordUpdateRQ;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }
}
