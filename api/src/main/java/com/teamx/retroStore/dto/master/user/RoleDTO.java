package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.model.user.Privilege;
import com.teamx.retroStore.model.user.Role;
import lombok.Getter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RoleDTO implements Serializable {

    @Getter
    private Integer ID;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private AppsConstants.Status status;

    private List<Integer> privileges;

    private List<Integer> addedPrivileges;

    private List<Integer> deletedPrivileges;

    public RoleDTO() {
    }

    public RoleDTO(Integer ID, String name, AppsConstants.Status status) {
        this.ID = ID;
        this.name = name;
        this.status = status;
    }

    public RoleDTO(Role role) {
        this.ID = role.getID();
        this.name = role.getName();
        this.description = role.getDescription();
        this.status = role.getStatus();

        for (Privilege privilege : role.getPrivileges()) {
            this.getPrivileges().add(privilege.getID());
        }
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    public List<Integer> getPrivileges() {
        if (privileges == null) {
            privileges = new ArrayList<>();
        }
        return privileges;
    }

    public void setPrivileges(List<Integer> privileges) {
        this.privileges = privileges;
    }

    public List<Integer> getAddedPrivileges() {
        if (addedPrivileges == null) {
            this.addedPrivileges = new ArrayList<>();
        }
        return addedPrivileges;
    }

    public void setAddedPrivileges(List<Integer> addedPrivileges) {
        this.addedPrivileges = addedPrivileges;
    }

    public List<Integer> getDeletedPrivileges() {
        if (deletedPrivileges == null) {
            deletedPrivileges = new ArrayList<>();
        }
        return deletedPrivileges;
    }

    public void setDeletedPrivileges(List<Integer> deletedPrivileges) {
        this.deletedPrivileges = deletedPrivileges;
    }

    @Override
    public String toString() {
        return "RoleDTO{" +
                "roleID=" + ID +
                ", roleName='" + name + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
