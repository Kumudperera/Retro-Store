package com.teamx.retroStore.dto.common.report.output;

import com.teamx.retroStore.common.constant.ReportConstant;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class CSVOutput extends ReportOutput {

    private String csv;

    @Override
    public void generateOutput(HttpServletResponse response, ReportConstant.ReportExportFormat exportFormat) throws IOException {
        response.addHeader(CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".csv");
        response.setContentType("csv");
        response.getWriter().write(csv);
    }

    public String getCsv() {
        return csv;
    }

    public void setCsv(String csv) {
        this.csv = csv;
    }
}
