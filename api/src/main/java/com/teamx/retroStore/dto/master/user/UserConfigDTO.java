package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ToString
public class UserConfigDTO implements Serializable {

    @Getter
    @Setter
    private Integer userID;

    @Getter
    @Setter
    private AppsConstants.Status status;

    @Setter
    private List<RoleDTO> roles;

    public List<RoleDTO> getRoles() {
        if (this.roles == null) {
            this.roles = new ArrayList<>();
        }
        return roles;
    }
}
