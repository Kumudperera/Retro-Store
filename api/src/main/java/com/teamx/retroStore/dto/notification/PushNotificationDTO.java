package com.teamx.retroStore.dto.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushNotificationDTO implements Serializable {

    private Integer userID;

    private List<String> userNotificationTokens;

    private Map<String, String> notificationData;

    private Map<String, String> languageWiseMessages;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    public List<String> getUserNotificationTokens() {
        if (userNotificationTokens == null) {
            userNotificationTokens = new ArrayList<>();
        }
        return userNotificationTokens;
    }

    public void setUserNotificationTokens(List<String> userNotificationTokens) {
        this.userNotificationTokens = userNotificationTokens;
    }

    public void addUserNotificationToken(String token) {
        this.getUserNotificationTokens().add(token);
    }

    public Map<String, String> getNotificationData() {
        if (notificationData == null) {
            notificationData = new HashMap<>();
        }
        return notificationData;
    }

    public void setNotificationData(Map<String, String> notificationData) {
        this.notificationData = notificationData;
    }

    public void addNotificationData(String key, String value) {
        this.getNotificationData().put(key, value);
    }

    public Map<String, String> getLanguageWiseMessages() {
        if (this.languageWiseMessages == null) {
            this.languageWiseMessages = new HashMap<>();
        }
        return languageWiseMessages;
    }

    public void setLanguageWiseMessages(Map<String, String> languageWiseMessages) {
        this.languageWiseMessages = languageWiseMessages;
    }

    public void addLanguageWiseMessages(String lang, String message) {
        this.getLanguageWiseMessages().put(lang, message);
    }

    @Override
    public String toString() {
        return "PushNotificationDTO{" +
                "userID=" + userID +
                ", userNotificationTokens=" + userNotificationTokens +
                ", notificationData=" + notificationData +
                ", languageWiseMessages=" + languageWiseMessages +
                '}';
    }
}
