package com.teamx.retroStore.dto.notification;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PushNotificationRequestDTO implements Serializable {

    private String app_id;

    private List<String> include_player_ids;

    private Map<String, String> data;

    private Map<String, String> contents;

    private String android_sound;

    private String android_channel_id;

    private String ios_sound;

    public PushNotificationRequestDTO() {
    }

    public PushNotificationRequestDTO(PushNotificationDTO pushNotificationDTO) {
        if (pushNotificationDTO.getUserID() != null) {
            this.include_player_ids = pushNotificationDTO.getUserNotificationTokens();
        }

        this.data = pushNotificationDTO.getNotificationData();
        this.contents = pushNotificationDTO.getLanguageWiseMessages();
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public List<String> getInclude_player_ids() {
        if (include_player_ids == null) {
            this.include_player_ids = new ArrayList<>();
        }
        return include_player_ids;
    }

    public void setInclude_player_ids(List<String> include_player_ids) {
        this.include_player_ids = include_player_ids;
    }

    public void addInclude_player_id(String include_player_ids) {
        this.getInclude_player_ids().add(include_player_ids);
    }

    public Map<String, String> getData() {
        if (this.data == null) {
            this.data = new HashMap<>();
        }
        return data;
    }

    public void setData(Map<String, String> data) {
        this.data = data;
    }

    public void putDataToDataMap(String key, String value) {
        this.getData().put(key, value);
    }


    public Map<String, String> getContents() {
        if (this.contents == null) {
            this.contents = new HashMap<>();
        }
        return contents;
    }

    public void setContents(Map<String, String> contents) {
        this.contents = contents;
    }


    public void putContentToContentMap(String key, String value) {
        this.getContents().put(key, value);
    }

    public String getAndroid_sound() {
        return android_sound;
    }

    public void setAndroid_sound(String android_sound) {
        this.android_sound = android_sound;
    }

    public String getAndroid_channel_id() {
        return android_channel_id;
    }

    public void setAndroid_channel_id(String android_channel_id) {
        this.android_channel_id = android_channel_id;
    }

    public String getIos_sound() {
        return ios_sound;
    }

    public void setIos_sound(String ios_sound) {
        this.ios_sound = ios_sound;
    }

    @Override
    public String toString() {
        return "PushNotificationRequestDTO{" +
                "app_id='" + app_id + '\'' +
                ", include_player_ids=" + include_player_ids +
                ", data=" + data +
                ", contents=" + contents +
                ", android_sound='" + android_sound + '\'' +
                ", android_channel_id='" + android_channel_id + '\'' +
                ", ios_sound='" + ios_sound + '\'' +
                '}';
    }
}
