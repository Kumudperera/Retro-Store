package com.teamx.retroStore.security.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
public class CredentialsDTO implements Serializable {

    private static final long serialVersionUID = 8757355855204209313L;

    private Integer id;

    private String username;

    private String requestIpAddress;

    private Collection<? extends GrantedAuthority> authorities;
}
