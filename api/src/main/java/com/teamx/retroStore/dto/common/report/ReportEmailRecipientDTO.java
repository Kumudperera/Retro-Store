package com.teamx.retroStore.dto.common.report;

import java.io.Serializable;

public class ReportEmailRecipientDTO implements Serializable {

    private String firstName;

    private String lastName;

    private String email;

    private String ccEmail;

    private String bccEmail;

    private boolean attachAsZip;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCcEmail() {
        return ccEmail;
    }

    public void setCcEmail(String ccEmail) {
        this.ccEmail = ccEmail;
    }

    public String getBccEmail() {
        return bccEmail;
    }

    public void setBccEmail(String bccEmail) {
        this.bccEmail = bccEmail;
    }

    public boolean isAttachAsZip() {
        return attachAsZip;
    }

    public void setAttachAsZip(boolean attachAsZip) {
        this.attachAsZip = attachAsZip;
    }

    @Override
    public String toString() {
        return "ReportEmailRecipientDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", ccEmail='" + ccEmail + '\'' +
                ", bccEmail='" + bccEmail + '\'' +
                ", attachAsZip=" + attachAsZip +
                '}';
    }
}
