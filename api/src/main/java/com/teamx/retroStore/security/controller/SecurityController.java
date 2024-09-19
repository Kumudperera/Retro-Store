package com.teamx.retroStore.security.controller;

import com.teamx.retroStore.controller.BaseController;
import com.teamx.retroStore.exception.aop.ResponseExceptionHandler;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.security.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "${api.prefix.v1}/security")
public class SecurityController extends BaseController {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityController.class);

    @Autowired
    private SecurityService securityService;

    @ResponseExceptionHandler
    @RequestMapping(value = "/expire-user-cache", headers = "Accept=application/json", method = RequestMethod.POST)
    public ResponseEntity<String> expireUserCache() {

        CredentialsDTO credentialsDTO = getCredentialsDTO();

        LOG.info("START : expire cache for user : {} ", credentialsDTO.getUsername());

        try {
            this.securityService.destroyUserCache();
        } catch (Exception e) {
            LOG.warn("user cache expiration failed for user {}", credentialsDTO.getUsername(), e);
        }

        LOG.info("END : expire cache for user : {} ", credentialsDTO.getUsername());
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
