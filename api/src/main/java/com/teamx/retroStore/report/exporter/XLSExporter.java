package com.teamx.retroStore.report.exporter;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.dto.common.report.output.XLSOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.UUID;

public class XLSExporter extends BaseExporter {
    private static final Logger LOG = LoggerFactory.getLogger(XLSExporter.class);

    public XLSExporter(JasperPrint print, Map<String, Object> parameters) {
        super(print, parameters);
    }

    @Override
    public ReportOutput generateOutput() throws AppsException {

        JRXlsxExporter exporter = new JRXlsxExporter();

        try {

            String fileName = getUUID(DomainConstants.FileFormat.XLSX.getDescription());
            File file = new File(fileName);

            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(file));
            SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
            configuration.setOnePagePerSheet(true);
            configuration.setDetectCellType(true);
            configuration.setRemoveEmptySpaceBetweenColumns(true);
            configuration.setRemoveEmptySpaceBetweenRows(true);
            configuration.setIgnoreCellBorder(true);
            exporter.setConfiguration(configuration);
            exporter.exportReport();
            byte[] byteArray = convertFileToByteArray(fileName, true);

            XLSOutput xlsOutput = new XLSOutput();
            xlsOutput.setXls(byteArray);
            return xlsOutput;
        } catch (JRException e) {
            LOG.error("Report Exporter : getting XLS link failed", e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR, e);
        }


    }

    private byte[] convertFileToByteArray(String fileName, boolean removeOriginal) throws AppsException {
        byte[] fileContent;
        Path path = Paths.get(fileName);
        try {
            fileContent = Files.readAllBytes(path);

        } catch (IOException e) {
            LOG.error("ERROR : failed to read file {}", path.toAbsolutePath());
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_FILE_STORAGE_FAILED_TO_READ);
        }
        File file = new File(fileName);
        if (removeOriginal) {
            file.delete();
        }
        return fileContent;
    }

    private String getUUID(String extension) {
        String uuid = UUID.randomUUID().toString() + extension;
        return uuid;
    }


}
