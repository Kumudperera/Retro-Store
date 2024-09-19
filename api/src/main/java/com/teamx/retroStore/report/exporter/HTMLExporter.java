package com.teamx.retroStore.report.exporter;

import com.teamx.retroStore.dto.common.report.output.HTMLOutput;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterConfiguration;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import net.sf.jasperreports.export.SimpleHtmlReportConfiguration;
import net.sf.jasperreports.export.type.HtmlSizeUnitEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class HTMLExporter extends BaseExporter {

    private static final Logger LOG = LoggerFactory.getLogger(HTMLExporter.class);

    private String logoPath;

    public HTMLExporter(JasperPrint print, Map<String, Object> parameters) {
        super(print, parameters);
        //  this.logoPath = parameters.get("logoPath").toString();
    }

    @Override
    public ReportOutput generateOutput() throws AppsException {

        StringBuffer outHTML = new StringBuffer();
        HtmlExporter exporterHTML = new HtmlExporter();

        SimpleExporterInput exporterInput = new SimpleExporterInput(print);
        exporterHTML.setExporterInput(exporterInput);

        String pageNumberStr = parameters.get("pageNumber").toString();
        String numberOfPages = String.format("%04d", print.getPages().size());

        SimpleHtmlExporterOutput exporterOutput;
        try {
            exporterOutput = new SimpleHtmlExporterOutput(outHTML);
//            exporterOutput.setImageHandler(new WebHtmlResourceHandler(logoPath));
            exporterHTML.setExporterOutput(exporterOutput);

            SimpleHtmlReportConfiguration reportExportConfiguration = new SimpleHtmlReportConfiguration();
            SimpleHtmlExporterConfiguration simpleHtmlExporterConfiguration = new SimpleHtmlExporterConfiguration();
            simpleHtmlExporterConfiguration.setHtmlHeader(
                    String.format("%04d", isNoResults() ? 0 : print.getPages().size()));
            simpleHtmlExporterConfiguration.setHtmlFooter("");
            simpleHtmlExporterConfiguration.setFlushOutput(true);

            exporterHTML.setConfiguration(simpleHtmlExporterConfiguration);

            reportExportConfiguration.setWhitePageBackground(true);
            reportExportConfiguration.setRemoveEmptySpaceBetweenRows(true);
            reportExportConfiguration.setAccessibleHtml(true);
            reportExportConfiguration.setOverrideHints(false);
            reportExportConfiguration.setSizeUnit(HtmlSizeUnitEnum.PIXEL);

            reportExportConfiguration.setEndPageIndex(Integer.parseInt(numberOfPages) - 1);

            if (Integer.parseInt(pageNumberStr) > 0) {
                Integer pageNumber = Integer.parseInt(pageNumberStr) - 1;
                reportExportConfiguration.setPageIndex(pageNumber);
            }

            exporterHTML.setConfiguration(reportExportConfiguration);
            exporterHTML.exportReport();
            HTMLOutput htmlOutput = new HTMLOutput();
            htmlOutput.setHtml(outHTML.toString());
            return htmlOutput;
        } catch (JRException e) {
            LOG.error("Report Exporter : getting HTML stream failed", e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR, e);
        }
    }
}
