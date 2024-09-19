package com.teamx.retroStore.dto.master.notification;

import com.teamx.retroStore.dto.common.APIRequestBaseDTO;

public class EmailSendWrapperRQ extends APIRequestBaseDTO {

    private EmailSendRequest emailSendRequest;

    public EmailSendWrapperRQ() {
    }

    public EmailSendWrapperRQ(EmailSendRequest emailSendRequest) {
        this.emailSendRequest = emailSendRequest;
    }

    public EmailSendRequest getEmailSendRequest() {
        if (emailSendRequest == null) {
            emailSendRequest = new EmailSendRequest();
        }
        return emailSendRequest;
    }

    public void setEmailSendRequest(EmailSendRequest emailSendRequest) {
        this.emailSendRequest = emailSendRequest;
    }


    @Override
    public String toString() {
        return "EmailSendWrapperRQ{" +
                "emailSendRequest=" + emailSendRequest +
                '}';
    }
}
