package com.teamx.retroStore.dto.common.report.output;

import com.teamx.retroStore.common.constant.ReportConstant;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

public class XLSOutput extends ReportOutput {

    private byte[] xls;

    @Override
    public void generateOutput(HttpServletResponse response, ReportConstant.ReportExportFormat exportFormat) throws IOException {
        response.addHeader(CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".xlsx");
        response.setContentType("vnd.ms-excel");
        OutputStream outStream = response.getOutputStream();
        outStream.write(xls);
        outStream.close();
    }

    public byte[] getXls() {
        return xls;
    }

    public void setXls(byte[] xls) {
        this.xls = xls;
    }
}
