package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class PrivilegeDTO implements Serializable {

    private Integer ID;

    private String code;

    private String name;

    private String description;

    private String category;

    private DomainConstants.PrivilegeModule module;

    private AppsConstants.Status status;
}
