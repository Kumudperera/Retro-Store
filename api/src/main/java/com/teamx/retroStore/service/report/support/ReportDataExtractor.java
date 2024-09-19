package com.teamx.retroStore.service.report.support;

import com.teamx.retroStore.dto.common.report.ReportGenerateContext;
import com.teamx.retroStore.exception.impl.AppsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ReportDataExtractor {

    private static final Logger LOG = LoggerFactory.getLogger(ReportDataExtractor.class);

    public List<?> getReportData(ReportGenerateContext reportContext) throws AppsException {
        List<?> result = null;

        switch (reportContext.getReportType()) {
        }

        return result;
    }
}
