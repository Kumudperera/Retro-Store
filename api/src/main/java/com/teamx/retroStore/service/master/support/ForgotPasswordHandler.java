package com.teamx.retroStore.service.master.support;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dao.master.user.jdbc.UserJDBCDao;
import com.teamx.retroStore.dto.common.PairDTO;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.dto.master.user.PasswordUpdateDTO;
import com.teamx.retroStore.dto.master.user.PasswordUpdateRQ;
import com.teamx.retroStore.dto.master.user.UserDTO;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import com.teamx.retroStore.service.master.UserService;
import com.teamx.retroStore.service.notification.support.EmailService;
import com.teamx.retroStore.util.CalendarUtil;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

public class ForgotPasswordHandler extends ChangePasswordHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ForgotPasswordHandler.class);

    @Getter
    private PasswordUpdateRQ passwordUpdateRQ;

    private Date date;

    @Setter
    private UserDao userDao;

    @Setter
    private UserJDBCDao userJDBCDao;

    @Setter
    private SecurityService securityService;

    @Setter
    @Getter
    private User user;

    @Setter
    private UserDTO userDTO;

    @Getter
    private PairDTO<String, String> passwords;

    @Setter
    private EmailService emailService;

    @Setter
    private Integer passwordResetLinkExpireTimeIn;

    private String uuid;

    private Date expireTime;

    @Setter
    private String baseBEURL;

    @Setter
    private String apiPrefix;

    @Setter
    private UserService userService;

    @Setter
    private String storageBaseURL;

    public ForgotPasswordHandler(PasswordUpdateRQ passwordUpdateRQ) {
        this.passwordUpdateRQ = passwordUpdateRQ;
        this.date = new Date();
    }

    public ForgotPasswordHandler() {
    }

    @Override
    public void validateUser() throws AppsException {
        LOG.info("START : validate user : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());

        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();
        Integer userID = this.userJDBCDao.getUserIDFromEmail(passwordUpdateDTO.getEmail());

        if (userID == null) {
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_USER_DOES_NOT_EXIST);
        } else {
            this.user = userDao.getReferenceById(userID);
        }

        LOG.info("END : validate user : {}", this.passwordUpdateRQ.getCredentialsDTO().getID());
    }

    @Override
    public void updatePassword() {
        LOG.info("START: User password update : {}", this.passwordUpdateRQ);
        PasswordUpdateDTO passwordUpdateDTO = this.passwordUpdateRQ.getPasswordUpdateDTO();

        this.user = userDao.getReferenceById(this.user.getID());
        this.expireTime = CalendarUtil.addMinutes(this.date, passwordResetLinkExpireTimeIn);
        this.uuid = UUID.randomUUID().toString();

        this.user.setLinkExpireTime(this.expireTime);
        //this.user.setForgetPasswordUUID(this.uuid);
        this.user.setModifiedBy(passwordUpdateDTO.getID());
        this.user.setModifiedDate(new Date());

        this.user = this.userDao.saveAndFlush(this.user);
        this.userDTO = new UserDTO(this.user);
        LOG.info("END: User password update : {}", passwordUpdateRQ);
    }

    @Override
    public void notifyUser() throws AppsException {
        String url = this.createEmailLink();

        EmailSendRequest emailSendRequest = new EmailSendRequest();
        emailSendRequest.setTemplateName("forget_password");
        emailSendRequest.setToAddresses(Collections.singletonList(this.user.getEmail()));
        emailSendRequest.setSubject("Forget Password Confirmation");
        emailSendRequest.setSendType(DomainConstants.EmailSendType.HTML);
        emailSendRequest.setPurpose(DomainConstants.EmailPurpose.NOTIFICATION);

        String brandImageUrl = this.storageBaseURL + "/company-logo.png";
        String lockImageUrl = this.storageBaseURL + "/image-1.png";

        Map<String, Object> params = new HashMap<>();
        params.put("resetPageUrl", url);
        params.put("brandImage", brandImageUrl);
        params.put("lockImage", lockImageUrl);
        emailSendRequest.setParams(params);

        CredentialsDTO credentialsDTO = new CredentialsDTO();
        credentialsDTO.setID(1);
        credentialsDTO.setUsername("SYSTEM");

        this.emailService.sendMailAsync(emailSendRequest, credentialsDTO);
    }

    public String createEmailLink() throws AppsException {
        String query = "id=" + this.uuid;
        String data = this.user.getEmail() + this.uuid + CalendarUtil.getDefaultFormattedDateTime(this.expireTime);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        data = encoder.encode(data);
        return baseBEURL + "user-validation" + "?" + query + "&data=" + data;
    }

    @Override
    public void expireUserCache() {
        this.securityService.destroyUserCache();
    }

    @Override
    public UserDTO getUserDTO() {
        return this.userDTO;
    }

}
