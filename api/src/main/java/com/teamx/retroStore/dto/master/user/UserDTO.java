package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dto.common.UserTrackableDTO;
import com.teamx.retroStore.model.user.Role;
import com.teamx.retroStore.model.user.User;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class UserDTO extends UserTrackableDTO {

    private static final long serialVersionUID = -2812615800057620985L;

    private Integer ID;

    private String username;

    private DomainConstants.UserType userType;

    private String nic;

    private String firstName;

    private String lastName;

    private String displayName;

    private String password;

    private String email;

    private String dobStr;

    private String epfNo;

    private String address;

    private String contactNo;

    private String mobileNo;

    private Integer superiorID;

    private Integer defaultLocationID;

    private Integer departmentID;

    private String imagePath;

    private Date lastLoginDate;

    private Date lastLogoutDate;

    private AppsConstants.Status status;

    private List<Integer> roles;

    private List<Integer> addedRoles;

    private List<Integer> removedRoles;

    private SortedSet<String> privileges;

    private List<Integer> addedLocations;

    private List<Integer> removedLocations;

    private List<Integer> rolesIDs;

    @Getter
    private boolean notifyPasswordGeneration;

    public UserDTO(UserRQ userRQ) {
        this.ID = userRQ.getID();
        this.username = userRQ.getUsername();
        this.password = userRQ.getPassword();
        this.firstName = userRQ.getFirstName();
        this.lastName = userRQ.getLastName();
        this.email = userRQ.getEmail();
        this.status = userRQ.getStatus();
        this.addedRoles = userRQ.getAddedRoles();
        this.removedRoles = userRQ.getRemovedRoles();
    }

    public UserDTO() {
        this.notifyPasswordGeneration = true;
    }

    public UserDTO(User user) {
        this(user, null);
    }

    public UserDTO(User user, UserLoadOptionDTO loadOptionDTO) {
        this.ID = user.getID();
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.status = user.getStatus();
        this.notifyPasswordGeneration = true;

        if (loadOptionDTO != null) {
            if (loadOptionDTO.isLoadRoleIDs()) {
                for (Role role : user.getRoles()) {
                    this.getRolesIDs().add(role.getID());
                }
            }
        }
    }

    public List<Integer> getRoles() {
        if (roles == null) {
            roles = new ArrayList<>();
        }
        return roles;
    }

    public List<Integer> getAddedRoles() {
        if (addedRoles == null) {
            addedRoles = new ArrayList<>();
        }
        return addedRoles;
    }

    public List<Integer> getRemovedRoles() {
        if (removedRoles == null) {
            removedRoles = new ArrayList<>();
        }
        return removedRoles;
    }

    public SortedSet<String> getPrivileges() {
        if (privileges == null) {
            privileges = new TreeSet<>();
        }
        return privileges;
    }

    public List<Integer> getAddedLocations() {
        if (addedLocations == null) {
            addedLocations = new ArrayList<>();
        }
        return addedLocations;
    }

    public List<Integer> getRemovedLocations() {
        if (removedLocations == null) {
            removedLocations = new ArrayList<>();
        }
        return removedLocations;
    }

    public List<Integer> getRolesIDs() {
        if (rolesIDs == null) {
            rolesIDs = new ArrayList<>();
        }
        return rolesIDs;
    }

    public void addPrivilege(String privilegeCode) {
        if (privileges == null) {
            privileges = new TreeSet<>();
        }
        privileges.add(privilegeCode);
    }
}
