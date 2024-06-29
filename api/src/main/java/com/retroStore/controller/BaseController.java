package com.retroStore.controller;

import com.retroStore.security.dto.CredentialsDTO;

public class BaseController {
    protected CredentialsDTO getCredentialsDTO() {

        CredentialsDTO credentialsDTO = new CredentialsDTO();

        return credentialsDTO;
    }
}
