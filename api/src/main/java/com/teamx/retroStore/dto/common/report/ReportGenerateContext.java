package com.teamx.retroStore.dto.common.report;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.common.constant.ReportConstant;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.security.dto.CredentialsDTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReportGenerateContext implements Serializable {

    private ReportConstant.ReportType reportType;

    private String reportTemplateType;

    private String templateName;

    private String configPath;

    private boolean dynamicReport;

    private List<String> headerList;

    private ReportConstant.ReportExportFormat reportExportFormat;

    private ReportConstant.ReportExportType reportExportType;

    private AppsConstants.ReportFileType reportFileType;

    /**
     * user for transfer report related parameters and search criteria
     */
    private ReportCriteria reportCriteria;


    private ReportSchedulingParam schedulingParam;

    private List<ReportEmailRecipientDTO> emailRecipientDTOS;

    private Map<String, Object> emailConfigParam;

    private CredentialsDTO credentialsDTO;

    private String reportFileName;

    private boolean isDataAvailable;

    private Integer outPutLength;

    private boolean replaceExistingFile;

    private List<?> dataList;

    private ReportOutput reportOutput;

    private String reportByteArrayStr;

    private Boolean isDynamicReport;

    public ReportConstant.ReportType getReportType() {
        return reportType;
    }

    public void setReportType(ReportConstant.ReportType reportType) {
        this.reportType = reportType;
    }

    public String getReportTemplateType() {
        return reportTemplateType;
    }

    public void setReportTemplateType(String reportTemplateType) {
        this.reportTemplateType = reportTemplateType;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getConfigPath() {
        return configPath;
    }

    public void setConfigPath(String configPath) {
        this.configPath = configPath;
    }

    public boolean isDynamicReport() {
        return dynamicReport;
    }

    public void setDynamicReport(boolean dynamicReport) {
        this.dynamicReport = dynamicReport;
    }

    public List<String> getHeaderList() {
        if (headerList == null) {
            headerList = new ArrayList<>();
        }
        return headerList;
    }

    public void setHeaderList(List<String> headerList) {
        this.headerList = headerList;
    }

    public ReportConstant.ReportExportFormat getReportExportFormat() {
        return reportExportFormat;
    }

    public void setReportExportFormat(ReportConstant.ReportExportFormat reportExportFormat) {
        this.reportExportFormat = reportExportFormat;
    }

    public ReportConstant.ReportExportType getReportExportType() {
        return reportExportType;
    }

    public void setReportExportType(ReportConstant.ReportExportType reportExportType) {
        this.reportExportType = reportExportType;
    }

    public AppsConstants.ReportFileType getReportFileType() {
        return reportFileType;
    }

    public void setReportFileType(AppsConstants.ReportFileType reportFileType) {
        this.reportFileType = reportFileType;
    }

    public ReportCriteria getReportCriteria() {
        if (reportCriteria == null) {
            reportCriteria = new ReportCriteria();
        }
        return reportCriteria;
    }

    public void setReportCriteria(ReportCriteria reportCriteria) {
        this.reportCriteria = reportCriteria;
    }

    public ReportSchedulingParam getSchedulingParam() {
        return schedulingParam;
    }

    public void setSchedulingParam(ReportSchedulingParam schedulingParam) {
        this.schedulingParam = schedulingParam;
    }

    public List<ReportEmailRecipientDTO> getEmailRecipientDTOS() {
        return emailRecipientDTOS;
    }

    public void setEmailRecipientDTOS(List<ReportEmailRecipientDTO> emailRecipientDTOS) {
        this.emailRecipientDTOS = emailRecipientDTOS;
    }

    public Map<String, Object> getEmailConfigParam() {
        if (emailConfigParam == null) {
            emailConfigParam = new HashMap<>();
        }
        return emailConfigParam;
    }

    public void setEmailConfigParam(Map<String, Object> emailConfigParam) {
        this.emailConfigParam = emailConfigParam;
    }

    public CredentialsDTO getCredentialsDTO() {
        return credentialsDTO;
    }

    public void setCredentialsDTO(CredentialsDTO credentialsDTO) {
        this.credentialsDTO = credentialsDTO;
    }

    public String getReportFileName() {
        return reportFileName;
    }

    public void setReportFileName(String reportFileName) {
        this.reportFileName = reportFileName;
    }

    public boolean isDataAvailable() {
        return isDataAvailable;
    }

    public void setDataAvailable(boolean dataAvailable) {
        isDataAvailable = dataAvailable;
    }

    public Integer getOutPutLength() {
        return outPutLength;
    }

    public void setOutPutLength(Integer outPutLength) {
        this.outPutLength = outPutLength;
    }

    public boolean isReplaceExistingFile() {
        return replaceExistingFile;
    }

    public void setReplaceExistingFile(boolean replaceExistingFile) {
        this.replaceExistingFile = replaceExistingFile;
    }

    public List<?> getDataList() {
        return dataList;
    }

    public void setDataList(List<?> dataList) {
        this.dataList = dataList;
    }

    public ReportOutput getReportOutput() {
        return reportOutput;
    }

    public void setReportOutput(ReportOutput reportOutput) {
        this.reportOutput = reportOutput;
    }

    public String getReportByteArrayStr() {
        return reportByteArrayStr;
    }

    public void setReportByteArrayStr(String reportByteArrayStr) {
        this.reportByteArrayStr = reportByteArrayStr;
    }

    public Boolean getDynamicReport() {
        return isDynamicReport;
    }

    public void setDynamicReport(Boolean dynamicReport) {
        isDynamicReport = dynamicReport;
    }

    @Override
    public String toString() {
        return "ReportGenerateContext{" +
                "reportType=" + reportType +
                ", reportTemplateType='" + reportTemplateType + '\'' +
                ", templateName='" + templateName + '\'' +
                ", configPath='" + configPath + '\'' +
                ", reportExportFormat=" + reportExportFormat +
                ", reportFileType=" + reportFileType +
                ", reportCriteria=" + reportCriteria +
                ", schedulingParam=" + schedulingParam +
                ", emailRecipientDTOS=" + emailRecipientDTOS +
                ", emailConfigParam=" + emailConfigParam +
                ", credentialsDTO=" + credentialsDTO +
                ", reportFileName='" + reportFileName + '\'' +
                ", isDataAvailable=" + isDataAvailable +
                ", outPutLength=" + outPutLength +
                ", replaceExistingFile=" + replaceExistingFile +
                ", reportOutput=" + reportOutput +
                '}';
    }
}
