package com.teamx.retroStore.service.master.support;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.config.AppsProperties;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dto.common.PairDTO;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.dto.master.user.PasswordUpdateDTO;
import com.teamx.retroStore.dto.master.user.PasswordUpdateRQ;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.exception.impl.AppsException;
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

public class ResetPasswordHandler extends ChangePasswordHandler {

    private static final Logger LOG = LoggerFactory.getLogger(ResetPasswordHandler.class);
    private PasswordUpdateRQ passwordUpdateRQ;

    private UserDao userDao;

    private SecurityService securityService;

    private User user;

    private UserDTO userDTO;

    private PairDTO<String, String> passwords;

    private EmailService emailService;

    private AppsProperties appsProperties;

    public ResetPasswordHandler(PasswordUpdateRQ passwordUpdateRQ) {
        this.passwordUpdateRQ = passwordUpdateRQ;
    }

    public ResetPasswordHandler() {
    }

    @Override
    public void validateUser() throws AppsException {
        LOG.info("START : validate user : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());

        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.passwords = PasswordUtil.generateRandomEncodedPassword(passwordEncoder, 8);
        passwordUpdateDTO.setNewPassword(passwords.getRight());

        LOG.info("END : validate user : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());
    }

    @Override
    public void updatePassword() {
        LOG.info("START: User password update : {}", this.passwordUpdateRQ);
        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();

        this.user = userDao.getReferenceById(passwordUpdateDTO.getID());

        this.user.setPassword(passwordUpdateDTO.getNewPassword());
        this.user.setModifiedBy(passwordUpdateDTO.getID());
        //this.user.setIsTemporaryPassword(AppsConstants.YesNo.Y);
        this.user.setModifiedDate(new Date());

        this.user = this.userDao.saveAndFlush(this.user);
        this.userDTO = new UserDTO(this.user);
        LOG.info("END: User password update : {}", passwordUpdateRQ);
    }

    @Override
    public void notifyUser() throws AppsException {
        LOG.info("START: Notify user password update : {}", passwordUpdateRQ);
        EmailSendRequest emailSendRequest = new EmailSendRequest();
        emailSendRequest.setTemplateName("credentials_template");
        emailSendRequest.setToAddresses(Collections.singletonList(this.user.getEmail()));
        emailSendRequest.setSubject("RETRO_STORE Credentials");
        emailSendRequest.setSendType(DomainConstants.EmailSendType.HTML);
        emailSendRequest.setPurpose(DomainConstants.EmailPurpose.NOTIFICATION);

        Map<String, Object> params = new HashMap<>();
        params.put("webUrl", appsProperties.getRetroStoreWebURL());
        params.put("username", this.user.getUsername());
        params.put("password", this.passwords.getLeft());
        emailSendRequest.setParams(params);

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setID(1);
        credentialsDTO.setUsername("SYSTEM");

        this.emailService.sendMailAsync(emailSendRequest, credentialsDTO);
        LOG.info("END: Notify user password update : {}", passwordUpdateRQ);
    }

    @Override
    public void expireUserCache() {
        this.securityService.destroyUserCache();
    }

    @Override
    public UserDTO getUserDTO() {
        return this.userDTO;
    }

    public PasswordUpdateRQ getPasswordUpdateRQ() {
        return passwordUpdateRQ;
    }

    public void setPasswordUpdateRQ(PasswordUpdateRQ passwordUpdateRQ) {
        this.passwordUpdateRQ = passwordUpdateRQ;
    }

    public UserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public SecurityService getSecurityService() {
        return securityService;
    }

    public void setSecurityService(SecurityService securityService) {
        this.securityService = securityService;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public PairDTO<String, String> getPasswords() {
        return passwords;
    }

    public void setPasswords(PairDTO<String, String> passwords) {
        this.passwords = passwords;
    }

    public EmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void setRetroStoreProperties(AppsProperties appsProperties) {
        this.appsProperties = appsProperties;
    }
}
