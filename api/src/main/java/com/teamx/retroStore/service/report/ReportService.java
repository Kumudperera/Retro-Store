package com.teamx.retroStore.service.report;

import com.teamx.retroStore.dto.common.report.ReportGenerateContext;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.service.report.support.ReportDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportService.class);

    @Autowired
    private ReportEngineService reportEngineService;

    @Autowired
    private ReportDataExtractor reportDataExtractor;

    @Autowired
    private ReportAsyncService reportAsyncService;

    @Value("${apps.reporting.file.temp.path}")
    private String tempFilePath;

    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public ReportGenerateContext generateReport(ReportGenerateContext reportContext) throws AppsException, Exception {
        reportContext.setConfigPath(tempFilePath);
        List<?> reportData = reportDataExtractor.getReportData(reportContext);
        if (reportData.size() > 0) {
            reportContext = reportEngineService.generateReport(reportContext, reportData);
            reportContext.setDataAvailable(true);
        } else {
            reportContext = reportEngineService.generateReport(reportContext, reportData);
            reportContext.setDataAvailable(false);
        }
        return reportContext;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void emailReport(ReportGenerateContext reportContext) throws Exception {
        reportAsyncService.emailReport(reportContext);
    }
}
