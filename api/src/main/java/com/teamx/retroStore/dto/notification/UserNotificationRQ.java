package com.teamx.retroStore.dto.notification;

import java.io.Serializable;

public class UserNotificationRQ implements Serializable {
    private Integer userID;
    private String notificationToken;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public String getNotificationToken() {
        return notificationToken;
    }

    public void setNotificationToken(String notificationToken) {
        this.notificationToken = notificationToken;
    }

    @Override
    public String toString() {
        return "UserNotificationRQ{" +
                "userID=" + userID +
                ", notificationToken='" + notificationToken + '\'' +
                '}';
    }
}
