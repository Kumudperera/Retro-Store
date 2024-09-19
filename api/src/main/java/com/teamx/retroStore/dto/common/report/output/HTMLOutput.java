package com.teamx.retroStore.dto.common.report.output;

import com.teamx.retroStore.common.constant.ReportConstant;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class HTMLOutput extends ReportOutput {

    private String html;

    @Override
    public void generateOutput(HttpServletResponse response, ReportConstant.ReportExportFormat exportFormat) throws IOException {

    }

    public String getHtml() {
        return html;
    }

    public void setHtml(String html) {
        this.html = html;
    }
}
