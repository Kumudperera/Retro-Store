package com.teamx.retroStore.dto.master.notification;

import com.teamx.retroStore.common.constant.DomainConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
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

    public List<String> getToAddresses() {
        if (toAddresses == null) {
            toAddresses = new ArrayList<>();
        }
        return toAddresses;
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

    public void addBcAddress(String bcAddress) {
        if (StringUtils.isNotBlank(bcAddress)) {
            getBccAddresses().add(bcAddress);
        }
    }

    public Map<String, Object> getParams() {
        if (params == null) {
            params = new HashMap<>();
        }
        return params;
    }

    public Map<String, Object> getAdditionalParams() {
        if (additionalParams == null) {
            additionalParams = new HashMap<>();
        }
        return additionalParams;
    }

    public Map<String, byte[]> getAttachmentMap() {
        if (attachmentMap == null) {
            attachmentMap = new HashMap<>();
        }
        return attachmentMap;
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

}
