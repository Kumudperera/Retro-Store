package com.teamx.retroStore.service.master;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.config.AppsProperties;
import com.teamx.retroStore.dao.master.user.RoleDao;
import com.teamx.retroStore.dao.master.user.UserDao;
import com.teamx.retroStore.dao.master.user.jdbc.UserJDBCDao;
import com.teamx.retroStore.dto.common.Page;
import com.teamx.retroStore.dto.master.user.*;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import com.teamx.retroStore.service.master.support.ChangePasswordHandler;
import com.teamx.retroStore.service.master.support.ForgotPasswordHandler;
import com.teamx.retroStore.service.master.support.ResetPasswordHandler;
import com.teamx.retroStore.service.master.support.UpdatePasswordHandler;
import com.teamx.retroStore.service.notification.support.EmailService;
import com.teamx.retroStore.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class UserService {

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserJDBCDao userJDBCDao;

    @Autowired
    private EmailService emailService;

    @Autowired
    private AppsProperties appsProperties;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public UserDTO saveOrUpdateUser(UserRQ userRQ, CredentialsDTO credentialsDTO) throws AppsException {
        LOG.info("START : Add or update User : {} : {}", userRQ, credentialsDTO.getID());

        Date date = new Date();
        boolean isNewUser = userRQ.getID() == null;

        User user;

        if (!isNewUser) {
            user = this.userDao.getReferenceById(userRQ.getID());
            user.setModifiedBy(credentialsDTO.getID());
            user.setModifiedDate(date);
        } else {
            user = new User();
            user.setCreatedBy(credentialsDTO.getID());
            user.setCreatedDate(date);
        }
        if (isNewUser || !userRQ.getUsername().equals(user.getUsername())) {
            if (this.userDao.findByUsernameIgnoreCase(userRQ.getUsername()) != null) {
                LOG.error("ERROR : User Already exists : {} : {}", userRQ, credentialsDTO.getID());
                throw new AppsException(AppErrorCode.APPS_EXCEPTION_USER_ALREADY_EXISTS);
            }
        }
        if (isNewUser || !userRQ.getEmail().equals(user.getEmail())) {
            if (this.userDao.findByEmailIgnoreCase(userRQ.getEmail()) != null) {
                LOG.error("ERROR : User email already exists : {} : {}, ", userRQ, credentialsDTO.getID());
                throw new AppsException(AppErrorCode.APPS_EXCEPTION_EMAIL_ALREADY_EXISTS);
            }
        }

        user.setUserType(userRQ.getUserType());
        user.setUsername(userRQ.getUsername());
        user.setFirstName(userRQ.getFirstName());
        user.setLastName(userRQ.getLastName());
        user.setEmail(userRQ.getEmail());
        user.setStatus(userRQ.getStatus());

        if (!userRQ.getRemovedRoles().isEmpty()) {
            user.removeRoles(this.roleDao.findAllById(userRQ.getRemovedRoles()));
        }
        if (!userRQ.getAddedRoles().isEmpty()) {
            user.getRoles().addAll(this.roleDao.findAllById(userRQ.getAddedRoles()));
        }

        if (isNewUser) {
            if (StringUtils.isBlank(userRQ.getPassword())) {
                user.setPassword(this.securityService.generatePassword(userRQ.getUsername(), userRQ.getEmail(),true));
            } else {
                user.setPassword(PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), userRQ.getPassword()));
            }
        }

        this.securityService.destroyUserCache();
        user = this.userDao.saveAndFlush(user);

        LOG.info("END : Add User : {} : {}", userRQ, credentialsDTO.getID());
        UserLoadOptionDTO loadOptionDTO = new UserLoadOptionDTO();
        loadOptionDTO.loadRoleIDs();
        return new UserDTO(user, loadOptionDTO);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public Page<UserDTO> getPagedUsers(UserSearchRQ searchRQ) {
        return userJDBCDao.getPagedUsers(searchRQ);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO getUserUpdateDTO(Integer roleID) throws AppsException {

        User user = this.userDao.getReferenceById(roleID);
        UserLoadOptionDTO loadOptionDTO = new UserLoadOptionDTO();
        loadOptionDTO.loadRoleIDs();
        return new UserDTO(user, loadOptionDTO);
    }

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AppsException.class)
    public UserDTO updateUserProfileData(UserProfileUpdateRQ userDTO) {
        User user = this.userDao.getReferenceById(userDTO.getUserID());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setEmail(userDTO.getEmail());
        user.setUsername(userDTO.getUserName());

        user = this.userDao.saveAndFlush(user);
        return new UserDTO(user);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String generateEncodedPassword(String plainPassword) throws AppsException {
        return PasswordUtil.generateEncodedPassword(new BCryptPasswordEncoder(), plainPassword);
    }

    /*@Transactional(propagation = Propagation.REQUIRED)
    public UserDTO validateReaderResetPassword(UserValidateRQ userValidateRQ) throws AppsException {
        Date date = new Date();
        User user = this.userDao.getUserByForgetPasswordUUID(userValidateRQ.getUuid());
        if (user == null) {
            LOG.error("No user for given email address {}", userValidateRQ.getUuid());
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FORGET_PASSWORD_USER_CANNOT_FIND);
        }
        if (!CalendarUtil.isBeforeOrEqual(date, user.getLinkExpireTime())) {
            LOG.error("Password reset link expired ");
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FORGET_PASSWORD_LINK_EXPIRED);
        }

        String data = user.getEmail() + userValidateRQ.getUuid() + CalendarUtil.getDefaultFormattedDateTime(user.getLinkExpireTime());
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(data, userValidateRQ.getValidateToken())) {

            user.setPassword(this.generateEncodedPassword(userValidateRQ.getPassword()));
            user.setIsTemporaryPassword(AppsConstants.YesNo.N);
            user.setModifiedDate(date);
            user.setModifiedBy(1);

            user = this.userDao.saveAndFlush(user);
        }

        return new UserDTO(user);
    }*/


    @Transactional(propagation = Propagation.REQUIRED)
    public UserDTO updateUserPassword(PasswordUpdateRQ passwordUpdateRQ) throws AppsException {

        PasswordUpdateDTO passwordUpdateDTO = passwordUpdateRQ.getPasswordUpdateDTO();
        LOG.info("START : update user password : {} by {}", passwordUpdateDTO, passwordUpdateRQ.getCredentialsDTO().getID());
        DomainConstants.PasswordUpdateAction action = passwordUpdateDTO.getAction();

        ChangePasswordHandler changePasswordHandler;
        switch (action) {
            case UPDATE:
                changePasswordHandler = new UpdatePasswordHandler(passwordUpdateRQ);
                break;
            case RESET:
                changePasswordHandler = new ResetPasswordHandler(passwordUpdateRQ);
                ((ResetPasswordHandler) changePasswordHandler).setRetroStoreProperties(this.appsProperties);
                break;
            case FORGOT:
            default:
                changePasswordHandler = new ForgotPasswordHandler(passwordUpdateRQ);
                ((ForgotPasswordHandler) changePasswordHandler).setUserJDBCDao(this.userJDBCDao);
                ((ForgotPasswordHandler) changePasswordHandler).setPasswordResetLinkExpireTimeIn(this.appsProperties.getPasswordResetLinkExpireTimeIn());
                ((ForgotPasswordHandler) changePasswordHandler).setBaseBEURL(this.appsProperties.getBaseBEURL());
                ((ForgotPasswordHandler) changePasswordHandler).setApiPrefix(this.appsProperties.getApiPrefix());
                ((ForgotPasswordHandler) changePasswordHandler).setStorageBaseURL(this.appsProperties.getStorageBaseURL());
                ((ForgotPasswordHandler) changePasswordHandler).setUserService(this);

        }

        changePasswordHandler.setUserDao(this.userDao);
        changePasswordHandler.setSecurityService(this.securityService);
        changePasswordHandler.setEmailService(this.emailService);

        changePasswordHandler.validateUser();
        changePasswordHandler.updatePassword();
        changePasswordHandler.expireUserCache();
        changePasswordHandler.notifyUser();
        UserDTO userDTO = changePasswordHandler.getUserDTO();
        LOG.info("END : update user password : {} by {}", passwordUpdateRQ.getPasswordUpdateDTO(), passwordUpdateRQ.getCredentialsDTO().getID());

        return userDTO;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public boolean isUserExists(String email) {
        return userDao.existsByEmail(email);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public List<String> getUserTokenListByUserIDs(List<Integer> userIDs) {
        return userJDBCDao.getUsersNotificationTokens(userIDs);
    }
}
