package com.teamx.retroStore.dto.common;

import com.teamx.retroStore.security.dto.CredentialsDTO;

import java.io.Serializable;
import java.util.Date;

public class APIRequestBaseDTO implements Serializable {

    private CredentialsDTO credentialsDTO;

    private Date date;

    public CredentialsDTO getCredentialsDTO() {
        return credentialsDTO;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "APIRequestBaseDTO{" +
                "credentialsDTO=" + credentialsDTO +
                ", date=" + date +
                '}';
    }
}
