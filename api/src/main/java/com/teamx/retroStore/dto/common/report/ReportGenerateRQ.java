package com.teamx.retroStore.dto.common.report;

import com.teamx.retroStore.dto.common.APIRequestBaseDTO;

public class ReportGenerateRQ extends APIRequestBaseDTO {

    private ReportGenerateContext reportGenerateContext;

    public ReportGenerateContext getReportGenerateContext() {
        return reportGenerateContext;
    }

    public void setReportGenerateContext(ReportGenerateContext reportGenerateContext) {
        this.reportGenerateContext = reportGenerateContext;
    }

    @Override
    public String toString() {
        return "ReportGenerateRQ{" +
                "reportGenerateContext=" + reportGenerateContext +
                '}';
    }

}
