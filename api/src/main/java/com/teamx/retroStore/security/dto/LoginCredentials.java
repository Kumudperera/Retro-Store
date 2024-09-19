package com.teamx.retroStore.security.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LoginCredentials implements Serializable {

    private static final long serialVersionUID = 2550353611722167268L;

    private String username;

    private String password;
}
