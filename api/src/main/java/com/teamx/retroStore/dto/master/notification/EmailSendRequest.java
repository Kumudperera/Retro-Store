package com.teamx.retroStore.dto.master.notification;

import com.teamx.retroStore.common.constant.DomainConstants;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EmailSendRequest implements Serializable {

    private String from;

    private String clientName;

    private List<String> toAddresses;

    private List<String> ccAddresses;

    private List<String> bccAddresses;

    private String subject;

    private Map<String, Object> params;

    private Map<String, Object> additionalParams;

    private DomainConstants.EmailSendType sendType;

    private String templateName;

    private String attachmentFileName;

    private String attachmentPath;

    private Map<String, byte[]> attachmentMap;

    private String textContent;

    private DomainConstants.EmailPurpose purpose;

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public List<String> getToAddresses() {
        if (toAddresses == null) {
            toAddresses = new ArrayList<>();
        }
        return toAddresses;
    }

    public void setToAddresses(List<String> toAddresses) {
        this.toAddresses = toAddresses;
    }

    public void addToAddress(String toAddress) {
        if (StringUtils.isNotBlank(toAddress)) {
            getToAddresses().add(toAddress);
        }
    }

    public List<String> getCcAddresses() {
        if (ccAddresses == null) {
            ccAddresses = new ArrayList<>();
        }
        return ccAddresses;
    }

    public void setCcAddresses(List<String> ccAddresses) {
        this.ccAddresses = ccAddresses;
    }

    public void addCcAddress(String ccAddress) {
        if (StringUtils.isNotBlank(ccAddress)) {
            getCcAddresses().add(ccAddress);
        }
    }

    public List<String> getBccAddresses() {
        if (bccAddresses == null) {
            bccAddresses = new ArrayList<>();
        }
        return bccAddresses;
    }

    public void setBccAddresses(List<String> bccAddresses) {
        this.bccAddresses = bccAddresses;
    }

    public void addBcAddress(String bcAddress) {
        if (StringUtils.isNotBlank(bcAddress)) {
            getBccAddresses().add(bcAddress);
        }
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public Map<String, Object> getAdditionalParams() {
        if (additionalParams == null) {
            additionalParams = new HashMap<>();
        }
        return additionalParams;
    }

    public void setAdditionalParams(Map<String, Object> additionalParams) {
        this.additionalParams = additionalParams;
    }

    public DomainConstants.EmailSendType getSendType() {
        return sendType;
    }

    public void setSendType(DomainConstants.EmailSendType sendType) {
        this.sendType = sendType;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getAttachmentFileName() {
        return attachmentFileName;
    }

    public void setAttachmentFileName(String attachmentFileName) {
        this.attachmentFileName = attachmentFileName;
    }

    public String getAttachmentPath() {
        return attachmentPath;
    }

    public void setAttachmentPath(String attachmentPath) {
        this.attachmentPath = attachmentPath;
    }

    public Map<String, byte[]> getAttachmentMap() {
        if (attachmentMap == null) {
            attachmentMap = new HashMap<>();
        }
        return attachmentMap;
    }

    public void setAttachmentMap(Map<String, byte[]> attachmentMap) {
        this.attachmentMap = attachmentMap;
    }

    public void addAttachmentIntoMap(String attachmentFileName, byte[] attachmentContent) {
        this.getAttachmentMap().put(attachmentFileName, attachmentContent);
    }

    public boolean hasAttachment() {
        return StringUtils.isNotEmpty(attachmentFileName) && StringUtils.isNotEmpty(attachmentPath);
    }

    public boolean hasAttachmentMap() {
        return !this.getAttachmentMap().isEmpty();
    }

    public String getTextContent() {
        return textContent;
    }

    public void setTextContent(String textContent) {
        this.textContent = textContent;
    }

    public DomainConstants.EmailPurpose getPurpose() {
        return purpose;
    }

    public void setPurpose(DomainConstants.EmailPurpose purpose) {
        this.purpose = purpose;
    }

    @Override
    public String toString() {
        return "EmailSendRequest{" +
                "from='" + from + '\'' +
                ", clientName='" + clientName + '\'' +
                ", toAddresses=" + toAddresses +
                ", ccAddresses=" + ccAddresses +
                ", bccAddresses=" + bccAddresses +
                ", subject='" + subject + '\'' +
                ", templateName='" + templateName + '\'' +
                '}';
    }
}
