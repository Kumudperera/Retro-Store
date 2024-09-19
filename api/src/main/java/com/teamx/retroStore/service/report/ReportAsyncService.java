package com.teamx.retroStore.service.report;

import com.teamx.retroStore.dto.common.report.ReportGenerateContext;
import com.teamx.retroStore.service.report.support.ReportDataExtractor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReportAsyncService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportAsyncService.class);

    private ReportEngineService reportEngineService;

    @Autowired
    private ReportDataExtractor reportDataExtractor;


    @Value("${apps.reporting.file.temp.path}")
    private String tempFilePath;

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void emailReport(ReportGenerateContext reportContext) throws Exception {
        LOG.info("START : Email report {}", reportContext);
        reportContext.setConfigPath(tempFilePath);
        List<?> reportData = reportDataExtractor.getReportData(reportContext);
        reportEngineService.emailReport(reportContext, reportData);
        LOG.info("END : Email report {}", reportContext);
    }
}
