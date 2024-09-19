package com.teamx.retroStore.dto.common;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
public class UserTrackableDTO implements Serializable {

    private String createdUserName;

    private Integer createdBy;

    private String modifiedUserName;

    private Integer modifiedBy;

    private Date createdDate;

    private String createdDateStr;

    private Date modifiedDate;

    private String modifiedDateStr;

}
