package com.teamx.retroStore.dto.common.report.output;

import com.teamx.retroStore.common.constant.ReportConstant;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;

@Getter
@Setter
public abstract class ReportOutput {

    protected static final String CONTENT_DISPOSITION = "Content-Disposition";

    protected String fileName;

    public abstract void generateOutput(HttpServletResponse response, ReportConstant.ReportExportFormat exportFormat) throws IOException;

}
