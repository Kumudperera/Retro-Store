package com.retroStore.security.dto;

import java.io.Serializable;

public class CredentialsDTO implements Serializable {
    private Integer userID;

    private String userName;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
