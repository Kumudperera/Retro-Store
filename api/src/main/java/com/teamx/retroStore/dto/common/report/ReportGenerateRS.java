package com.teamx.retroStore.dto.common.report;

import com.teamx.retroStore.dto.common.APIResponseBaseDTO;

public class ReportGenerateRS extends APIResponseBaseDTO {

    private ReportGenerateDTO reportGenerateDTO;

    public ReportGenerateDTO getReportGenerateDTO() {
        return reportGenerateDTO;
    }

    public void setReportGenerateDTO(ReportGenerateDTO reportGenerateDTO) {
        this.reportGenerateDTO = reportGenerateDTO;
    }

    @Override
    public String toString() {
        return "ReportGenerateRS{" +
                "reportGenerateDTO=" + reportGenerateDTO +
                '}';
    }

}
