package com.teamx.retroStore.util;

import com.google.common.collect.Maps;
import com.teamx.retroStore.common.constant.ReportConstant;
import com.teamx.retroStore.dto.common.report.ReportGenerateContext;
import com.teamx.retroStore.dto.common.report.output.CSVOutput;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.report.exporter.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ReportExporter {

    private static final Logger LOG = LoggerFactory.getLogger(ReportExporter.class);

    private String reportTemplatePath;

    private ReportGenerateContext reportContext;

    public ReportExporter(ReportGenerateContext reportContext) {
        this.reportContext = reportContext;
    }

    public ReportOutput buildReportOutput() throws Exception {

        LOG.info("Report Exporter : Report Build Started ");
        Map<String, Object> parameters = Maps.newHashMap();
        final ReportConstant.ReportExportFormat outType = reportContext.getReportExportFormat();
        final String reportType = reportContext.getReportTemplateType();

        String pathToLogo = null;
        File file;
        JasperReport jasperReport;


        if (outType == ReportConstant.ReportExportFormat.CSV || outType == ReportConstant.ReportExportFormat.CSV_ZIP_EMAIL) {
            parameters.put("inCSVView", Boolean.TRUE);
        } else {
            parameters.put("inCSVView", Boolean.FALSE);
        }

        if ((outType == ReportConstant.ReportExportFormat.XLS) || (outType == ReportConstant.ReportExportFormat.XLS_ZIP_EMAIL)) {
            parameters.put("inXLSView", Boolean.TRUE);
        } else {
            parameters.put("inXLSView", Boolean.FALSE);
        }

        if (reportContext.getReportExportFormat().equals(ReportConstant.ReportExportFormat.CSV_ZIP_EMAIL) ||
                reportContext.getReportExportFormat().equals(ReportConstant.ReportExportFormat.CSV) ||
                reportContext.getReportExportFormat().equals(ReportConstant.ReportExportFormat.XLS) ||
                reportContext.getReportExportFormat().equals(ReportConstant.ReportExportFormat.XLS_ZIP_EMAIL)) {
            parameters.put(JRParameter.IS_IGNORE_PAGINATION, Boolean.TRUE);
        }

        try {
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(getDataList());
            parameters.putAll(reportContext.getReportCriteria().getReportParameters());
            JasperPrint print = null;

            if (reportContext.isDynamicReport()) {
                if (reportContext.getReportExportFormat().equals(ReportConstant.ReportExportFormat.HTML)) {
                }

                switch (reportContext.getReportType()) {
                    // your uncommon report build here
                }

            } else {

                String templatePath = this.reportContext.getConfigPath() + File.separator + "reportTemplates" +
                        File.separator + "jrxml" + File.separator + reportContext.getTemplateName() + ".jrxml";

                try {

                    file = ResourceUtils.getFile(templatePath);
                    jasperReport = JasperCompileManager.compileReport(file.getAbsolutePath());
                } catch (FileNotFoundException e) {
                    LOG.error("ERROR:Report template not found from path {}", templatePath);
                    throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_TEMPLATE_FILE_NOT_FOUND);
                } catch (JRException e) {
                    LOG.error("ERROR:Report Compiling error {}", templatePath, e);
                    throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_TEMPLATE_FILE_NOT_FOUND);
                }
                print = JasperFillManager.fillReport(jasperReport, parameters, dataSource);
            }

            BaseExporter exporter = createExporter(outType, print, parameters);
            exporter.setNoResults(reportContext.getOutPutLength() <= 0);
            ReportOutput reportOutput = exporter.generateOutput();
            formatCsvDates(reportOutput, outType);

            if (reportContext.isDynamicReport()) {
                String fileName = parameters.get("reportName").toString().replace(" ", "_");
                reportOutput.setFileName(fileName);

            } else {
                reportOutput.setFileName(reportContext.getReportFileName());
            }

            LOG.info("Report Exporter : Report Build ended ");
            return reportOutput;
        } catch (AppsException e) {
            LOG.error("Report Exporter : building report failed", e);
            throw e;
        } catch (JRException e) {
            LOG.error("Report Exporter : building report output failed", e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR, e);
        }
    }

    private BaseExporter createExporter(ReportConstant.ReportExportFormat outType, JasperPrint print, Map<String, Object> parameters) {
        BaseExporter exporter = null;
        switch (outType) {
            case HTML:
                exporter = new HTMLExporter(print, parameters);
                break;
            case PDF:
            case PDF_SHOW:
            case PDF_SHOW_INLINE:
            case PDF_DOWNLOAD:
            case PDF_ZIP_EMAIL:
            case EMAIL_REPORT:
                exporter = new PDFExporter(print, parameters);
                break;
            case CSV:
            case CSV_ZIP_EMAIL:
                exporter = new CSVExporter(print, parameters);
                break;
            case XLS:
            case XLS_ZIP_EMAIL:
                exporter = new XLSExporter(print, parameters);
                break;
        }
        return exporter;
    }

    private void formatCsvDates(ReportOutput reportOutput, ReportConstant.ReportExportFormat exportFormat) {
        if (exportFormat == ReportConstant.ReportExportFormat.CSV) {
            String pattern = "(,)(\\d\\d\\/\\D\\D\\D\\/)(\\d\\d\\d\\d)(\\s)(\\d\\d.\\d\\d)(,)";
            Pattern regExp = Pattern.compile(pattern);
            String str = ((CSVOutput) reportOutput).getCsv();
            while (true) {
                Matcher match = regExp.matcher(str);
                if (match.find()) {
                    String matchGroup0 = match.group(0).replace(",", "");
                    String replaceStr = "\"=\"\"" + matchGroup0 + "\"\"\"";
                    str = str.replace(matchGroup0, replaceStr);
                } else {
                    break;
                }
            }

            ((CSVOutput) reportOutput).setCsv(str);
        }
    }

    private List<?> getDataList() {
        reportContext.setOutPutLength(reportContext.getDataList().size());
        return reportContext.getDataList();
    }

}
