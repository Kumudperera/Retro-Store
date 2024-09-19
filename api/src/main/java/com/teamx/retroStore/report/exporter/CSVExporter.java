package com.teamx.retroStore.report.exporter;

import com.teamx.retroStore.dto.common.report.output.CSVOutput;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleWriterExporterOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class CSVExporter extends BaseExporter {

    private static final Logger LOG = LoggerFactory.getLogger(CSVExporter.class);

    public CSVExporter(JasperPrint print, Map<String, Object> parameters) {
        super(print, parameters);
    }


    @Override
    public ReportOutput generateOutput() throws AppsException {

        JRCsvExporter exporterCSV = new JRCsvExporter();
        StringBuffer stringBuffer = new StringBuffer();
        SimpleWriterExporterOutput simpleWriterExporterOutput = new SimpleWriterExporterOutput(stringBuffer);
        SimpleExporterInput simpleExporterInput = new SimpleExporterInput(print);

        exporterCSV.setExporterInput(simpleExporterInput);
        exporterCSV.setExporterOutput(simpleWriterExporterOutput);

        try {
            exporterCSV.exportReport();
            CSVOutput csvOutput = new CSVOutput();
            csvOutput.setCsv(stringBuffer.toString());
            return csvOutput;
        } catch (JRException e) {
            LOG.error("Report Exporter : getting CSV link failed", e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR, e);
        }
    }
}
