package com.teamx.retroStore.dto.master.user;


import com.teamx.retroStore.security.dto.CredentialsDTO;

public class PasswordUpdateRQ {

    private PasswordUpdateDTO passwordUpdateDTO;

    private CredentialsDTO credentialsDTO;

    public PasswordUpdateDTO getPasswordUpdateDTO() {
        return passwordUpdateDTO;
    }

    public void setPasswordUpdateDTO(PasswordUpdateDTO passwordUpdateDTO) {
        this.passwordUpdateDTO = passwordUpdateDTO;
    }

    public CredentialsDTO getCredentialsDTO() {
        return credentialsDTO;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    @Override
    public String toString() {
        return "PasswordUpdateRQ{" +
                "passwordUpdateDTO=" + passwordUpdateDTO +
                '}';
    }
}
