package com.teamx.retroStore.security.service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.teamx.retroStore.common.constant.CachingConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.config.AppsProperties;
import com.teamx.retroStore.dto.common.PairDTO;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.security.dao.jdbc.UserSecurityJDBCDao;
import com.teamx.retroStore.security.dto.AuthDTO;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.notification.support.EmailService;
import com.teamx.retroStore.util.PasswordUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class SecurityService {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityService.class);

    @Autowired
    private UserSecurityJDBCDao userSecurityJDBCDao;

    @Autowired
    private AppsProperties appsProperties;

    @Autowired
    private EmailService emailService;

    @Transactional(propagation = Propagation.SUPPORTS)
    @Cacheable(CachingConstants.USERS_CACHE_KEY)
    public AuthDTO getUserByUsername(String username) throws AppsException {
        return this.userSecurityJDBCDao.getUserByUsername(username);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void destroyUserCache() {
        HazelcastInstance hazelcastInstance = Hazelcast
                .getHazelcastInstanceByName(CachingConstants.CACHE_INSTANCE_NAME);
        hazelcastInstance.getMap(CachingConstants.USERS_CACHE_KEY).destroy();
        LOG.info("------->>> User cache destroyed ---------->>");
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public String generatePassword(String username, String sendToEmail, boolean sendMailNotification)
            throws AppsException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        PairDTO<String, String> passPair = PasswordUtil.generateRandomEncodedPassword(passwordEncoder,
                Integer.parseInt(appsProperties.getUserGeneratedPasswordLength()));

        if (StringUtils.isNotBlank(sendToEmail) && StringUtils.isNotBlank(username) && sendMailNotification) {
            EmailSendRequest emailSendRequest = new EmailSendRequest();
            emailSendRequest.setTemplateName("credentials_template");
            emailSendRequest.setToAddresses(Collections.singletonList(sendToEmail));
            emailSendRequest.setSubject("RETRO_STORE Credentials");
            emailSendRequest.setSendType(DomainConstants.EmailSendType.HTML);
            emailSendRequest.setPurpose(DomainConstants.EmailPurpose.NOTIFICATION);

            Map<String, Object> params = new HashMap<>();
            params.put("webUrl", appsProperties.getRetroStoreWebURL());
            params.put("username", username);
            params.put("password", passPair.getLeft());
            emailSendRequest.setParams(params);

            CredentialsDTO credentialsDTO = new CredentialsDTO();
            credentialsDTO.setID(1);
            credentialsDTO.setUsername("SYSTEM");

            this.emailService.sendMailAsync(emailSendRequest, credentialsDTO);
        }
        return passPair.getRight();
    }
}