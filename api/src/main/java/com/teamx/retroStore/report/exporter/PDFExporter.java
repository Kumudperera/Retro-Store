package com.teamx.retroStore.report.exporter;

import com.teamx.retroStore.dto.common.report.output.PDFOutput;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.lowagie.text.pdf.ByteBuffer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class PDFExporter extends BaseExporter {

    private static final Logger LOG = LoggerFactory.getLogger(PDFExporter.class);

    public PDFExporter(JasperPrint print, Map<String, Object> parameters) {
        super(print, parameters);
    }

    @Override
    public ReportOutput generateOutput() throws AppsException {
        try {
            ByteBuffer byteBuffer = new ByteBuffer();
            JRPdfExporter exporter = new JRPdfExporter();
            SimpleExporterInput simpleExporterInput = new SimpleExporterInput(print);
            exporter.setExporterInput(simpleExporterInput);
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(byteBuffer));


            SimplePdfReportConfiguration simplePdfReportConfiguration = new SimplePdfReportConfiguration();
            simplePdfReportConfiguration.setIgnoreHyperlink(false);

            exporter.setConfiguration(simplePdfReportConfiguration);
            exporter.exportReport();

            PDFOutput pdfOutput = new PDFOutput();
            pdfOutput.setPdf(byteBuffer.getBuffer());
            return pdfOutput;
        } catch (JRException e) {
            LOG.error("Report Exporter : getting PDF stream failed", e);
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_REPORT_ENGINE_EXCEPTION_JASPER_ERROR, e);
        }
    }
}
