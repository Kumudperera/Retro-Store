package com.teamx.retroStore.model.audit;

import com.teamx.retroStore.common.audit.constants.AuditConstants;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.io.Serializable;
import java.util.Date;


@Entity
@Table(name = "T_SYSTEM_AUDIT")
public class SystemAudit implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "SYS_AUDIT_ID")
    private Integer systemAuditID;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "TIMESTAMP")
    private Date timeStamp;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYS_AUDIT_ACTION_CODE")
    private AuditConstants.SystemAuditActionCode systemAuditActionCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYS_AUDIT_CATEGORY")
    private AuditConstants.SystemAuditCategory systemAuditCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYS_AUDIT_SUB_CATEGORY")
    private AuditConstants.SystemAuditSubCategory systemAuditSubCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "SYS_AUDIT_TEMPLATE_CODE")
    private AuditConstants.SystemAuditTemplate systemAuditTemplateCode;

    @Column(name = "PREVIOUS_CONTENT")
    private String previousContent;

    @Column(name = "UPDATED_CONTENT")
    private String updatedContent;

    @Column(name = "SUMMARY")
    private String summary;

    @Column(name = "USER_ID")
    private Integer userID;

    @Column(name = "USERNAME")
    private String userName;

    @Column(name = "PREVIOUS_CONTENT_FULL")
    private byte[] previousContentFull;

    @Column(name = "UPDATED_CONTENT_FULL")
    private byte[] updatedContentFull;

    @Column(name = "SUMMARY_FULL")
    private byte[] summaryFull;

    @Column(name = "REFERENCE_ID")
    private Integer referenceID;

    @Transient
    private String keywordSearchContent;

    public Integer getSystemAuditID() {
        return systemAuditID;
    }

    public void setSystemAuditID(Integer systemAuditID) {
        this.systemAuditID = systemAuditID;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public AuditConstants.SystemAuditActionCode getSystemAuditActionCode() {
        return systemAuditActionCode;
    }

    public void setSystemAuditActionCode(AuditConstants.SystemAuditActionCode systemAuditActionCode) {
        this.systemAuditActionCode = systemAuditActionCode;
    }

    public AuditConstants.SystemAuditCategory getSystemAuditCategory() {
        return systemAuditCategory;
    }

    public void setSystemAuditCategory(AuditConstants.SystemAuditCategory systemAuditCategory) {
        this.systemAuditCategory = systemAuditCategory;
    }

    public AuditConstants.SystemAuditSubCategory getSystemAuditSubCategory() {
        return systemAuditSubCategory;
    }

    public void setSystemAuditSubCategory(AuditConstants.SystemAuditSubCategory systemAuditSubCategory) {
        this.systemAuditSubCategory = systemAuditSubCategory;
    }

    public AuditConstants.SystemAuditTemplate getSystemAuditTemplateCode() {
        return systemAuditTemplateCode;
    }

    public void setSystemAuditTemplateCode(AuditConstants.SystemAuditTemplate systemAuditTemplateCode) {
        this.systemAuditTemplateCode = systemAuditTemplateCode;
    }

    public String getPreviousContent() {
        return previousContent;
    }

    public void setPreviousContent(String previousContent) {
        this.previousContent = previousContent;
    }

    public String getUpdatedContent() {
        return updatedContent;
    }

    public void setUpdatedContent(String updatedContent) {
        this.updatedContent = updatedContent;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

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

    public byte[] getPreviousContentFull() {
        return previousContentFull;
    }

    public void setPreviousContentFull(byte[] previousContentFull) {
        this.previousContentFull = previousContentFull;
    }

    public byte[] getUpdatedContentFull() {
        return updatedContentFull;
    }

    public void setUpdatedContentFull(byte[] updatedContentFull) {
        this.updatedContentFull = updatedContentFull;
    }

    public byte[] getSummaryFull() {
        return summaryFull;
    }

    public void setSummaryFull(byte[] summaryFull) {
        this.summaryFull = summaryFull;
    }

    public Integer getReferenceID() {
        return referenceID;
    }

    public void setReferenceID(Integer referenceID) {
        this.referenceID = referenceID;
    }

    public String getKeywordSearchContent() {
        return keywordSearchContent;
    }

    public void setKeywordSearchContent(String keywordSearchContent) {
        this.keywordSearchContent = keywordSearchContent;
    }
}
