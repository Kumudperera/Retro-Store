package com.teamx.retroStore.dto.common.report.output;

import com.teamx.retroStore.common.constant.ReportConstant;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.OutputStream;

public class PDFOutput extends ReportOutput {

    private byte[] pdf;

    @Override
    public void generateOutput(HttpServletResponse response, ReportConstant.ReportExportFormat exportFormat) throws IOException {
        if (exportFormat.equals(ReportConstant.ReportExportFormat.PDF_SHOW)) {
            response.addHeader(CONTENT_DISPOSITION, "inline;filename=" + fileName + ".pdf");
        } else if (exportFormat.equals(ReportConstant.ReportExportFormat.PDF_DOWNLOAD)) {
            response.addHeader(CONTENT_DISPOSITION, "attachment;filename=" + fileName + ".pdf");
        }
        response.setContentType("pdf");
        OutputStream outStream = response.getOutputStream();
        outStream.write(pdf);
        outStream.close();
    }

    public byte[] getPdf() {
        return pdf;
    }

    public void setPdf(byte[] pdf) {
        this.pdf = pdf;
    }
}
