package com.teamx.retroStore.dto.common.report;

import com.teamx.retroStore.common.constant.ReportConstant;

import java.io.Serializable;
import java.util.Arrays;

public class ReportGenerateDTO implements Serializable {

    private byte[] reportByteArray;

    private String htmlContent;

    private String fileName;

    private ReportConstant.ReportExportFormat reportExportFormat;

    public byte[] getReportByteArray() {
        return reportByteArray;
    }

    public void setReportByteArray(byte[] reportByteArray) {
        this.reportByteArray = reportByteArray;
    }

    public String getHtmlContent() {
        return htmlContent;
    }

    public void setHtmlContent(String htmlContent) {
        this.htmlContent = htmlContent;
    }

    public ReportConstant.ReportExportFormat getReportExportFormat() {
        return reportExportFormat;
    }

    public void setReportExportFormat(ReportConstant.ReportExportFormat reportExportFormat) {
        this.reportExportFormat = reportExportFormat;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String toString() {
        return "ReportGenerateDTO{" +
                "reportByteArray=" + Arrays.toString(reportByteArray) +
                ", htmlContent='" + htmlContent + '\'' +
                ", fileName='" + fileName + '\'' +
                ", reportExportFormat=" + reportExportFormat +
                '}';
    }

}
