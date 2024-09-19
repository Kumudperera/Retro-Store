package com.teamx.retroStore.service.report;

import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.common.constant.ReportConstant;
import com.teamx.retroStore.dto.common.report.ReportEmailRecipientDTO;
import com.teamx.retroStore.dto.common.report.ReportGenerateContext;
import com.teamx.retroStore.dto.common.report.ReportGenerateDTO;
import com.teamx.retroStore.dto.common.report.output.PDFOutput;
import com.teamx.retroStore.dto.common.report.output.ReportOutput;
import com.teamx.retroStore.dto.common.report.output.XLSOutput;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.service.notification.support.EmailService;
import com.teamx.retroStore.util.ReportExporter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportEngineService {

    private static final Logger LOG = LoggerFactory.getLogger(ReportEngineService.class);

    @Autowired
    private EmailService messagingService;

    @Transactional(propagation = Propagation.SUPPORTS, rollbackFor = AppsException.class)
    public ReportGenerateContext generateReport(ReportGenerateContext reportContext, List<?> dataList) throws Exception {
        LOG.info("START : Report Engine Service : Generating report {}", reportContext);
        reportContext.setDataList(dataList);
        ReportExporter exporter = new ReportExporter(reportContext);

        ReportOutput reportOutput = exporter.buildReportOutput();
        reportContext.setReportOutput(reportOutput);
        LOG.info("END : Report Engine Service : Generating report {}", reportContext);
        return reportContext;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void emailReport(ReportGenerateContext reportContext, List<?> reportData) throws Exception {

        boolean messageSent = false;
        LOG.info("Report Engine Service : Emailing report started :{}", reportContext);

        reportContext = this.generateReport(reportContext, reportData);

        EmailSendRequest emailSendRequest = new EmailSendRequest();


        for (ReportEmailRecipientDTO emailRecipientDTO : reportContext.getEmailRecipientDTOS()) {
            emailSendRequest.addToAddress(emailRecipientDTO.getEmail());
            emailSendRequest.addCcAddress(emailRecipientDTO.getCcEmail());
            emailSendRequest.addBcAddress(emailRecipientDTO.getBccEmail());
        }
        emailSendRequest.setSendType(DomainConstants.EmailSendType.HTML);
        emailSendRequest.setTemplateName("report_via_email");

        emailSendRequest.setPurpose(DomainConstants.EmailPurpose.REPORT);
        emailSendRequest.setSubject(reportContext.getReportType().getDescription());
//        emailSendRequest.setTemplateName((String) reportContext.getEmailConfigParam().get("emailTemplate"));
        emailSendRequest.setParams(reportContext.getEmailConfigParam());


        ReportGenerateDTO reportDTO = exportReport(reportContext);

        Map<String, byte[]> attachmentMap = new HashMap<>();
        attachmentMap.put(reportDTO.getFileName(), reportDTO.getReportByteArray());
        emailSendRequest.setAttachmentMap(attachmentMap);

        CredentialsDTO credentialsDTO = reportContext.getCredentialsDTO();

        messagingService.sendMail(emailSendRequest, credentialsDTO);
        LOG.info("Report Engine Service : Emailing report end :{}", reportContext);
    }

    protected ReportGenerateDTO exportReport(ReportGenerateContext reportContext) {
        byte[] reportByteArray = null;
        ReportGenerateDTO reportDTO = new ReportGenerateDTO();
        ReportConstant.FileType fileType = ReportConstant.FileType.XLS;
        switch (reportContext.getReportExportFormat()) {
            case PDF: {
                reportByteArray = ((PDFOutput) reportContext.getReportOutput()).getPdf();
                fileType = ReportConstant.FileType.PDF;
                break;
            }
            case XLS: {
                reportByteArray = ((XLSOutput) reportContext.getReportOutput()).getXls();
                fileType = ReportConstant.FileType.XLS;
                break;
            }
        }
        reportDTO.setReportByteArray(reportByteArray);
        reportDTO.setReportExportFormat(reportContext.getReportExportFormat());
        reportDTO.setFileName(reportContext.getReportType().getDescription() + fileType.getExtension());
        return reportDTO;
    }
}
