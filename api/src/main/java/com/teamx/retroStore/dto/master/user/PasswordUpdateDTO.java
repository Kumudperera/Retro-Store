package com.teamx.retroStore.dto.master.user;



import com.teamx.retroStore.common.constant.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@ToString
public class PasswordUpdateDTO implements Serializable {

    private static final long serialVersionUID = -1962640883938445528L;

    private Integer ID;

    private DomainConstants.PasswordUpdateAction action;

    private String email;

    private String oldPassword;

    private String newPassword;
}
