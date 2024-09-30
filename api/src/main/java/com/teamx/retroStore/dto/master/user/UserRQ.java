package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.model.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.*;

@Getter
@Setter
@ToString
public class UserRQ {
    private static final long serialVersionUID = 2373460014966807565L;

    private Integer id;

    private String username;

    private DomainConstants.UserType userType;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private AppsConstants.Status status;

    private List<Integer> addedRoles;

    private List<Integer> removedRoles;

    public UserRQ() {

    }

    public UserRQ(int id, UpdateRQ updateRQ) {
        this.id = id;
        this.username = updateRQ.getUsername();
        this.userType = updateRQ.getUserType();
        this.firstName = updateRQ.getFirstName();
        this.lastName = updateRQ.getLastName();
        this.password = updateRQ.getPassword();
        this.email = updateRQ.getEmail();
        this.status = updateRQ.getStatus();
        this.addedRoles = updateRQ.getAddedRoles();
        this.removedRoles = updateRQ.getAddedRoles();
    }

    public UserRQ(CreateRQ createRQ) {
        this.username = createRQ.getUsername();
        this.userType = createRQ.getUserType();
        this.firstName = createRQ.getFirstName();
        this.lastName = createRQ.getLastName();
        this.password = createRQ.getPassword();
        this.email = createRQ.getEmail();
        this.status = createRQ.getStatus();
        this.addedRoles = createRQ.getAddedRoles();
    }

    public UserRQ(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.userType = user.getUserType();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.status = user.getStatus();
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

}
