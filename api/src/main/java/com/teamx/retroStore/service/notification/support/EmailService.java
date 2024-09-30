package com.teamx.retroStore.service.notification.support;


import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.config.EmailConfig;
import com.teamx.retroStore.config.AppsProperties;
import com.teamx.retroStore.dto.master.notification.EmailSendRequest;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.exception.impl.AppErrorCode;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import jakarta.mail.internet.MimeMessage;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;

@Service
public class EmailService {

    private static final Logger LOG = LoggerFactory.getLogger(EmailService.class);

    @Autowired
    @Qualifier("emailTemplateEngine")
    private TemplateEngine templateEngine;

    @Autowired
    @Qualifier("otherMailSender")
    private JavaMailSender otherMailSender;

    @Autowired
    @Qualifier("notificationMailSender")
    private JavaMailSender notificationMailSender;

    @Autowired
    private AppsProperties appsProperties;

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public void sendMail(EmailSendRequest request, CredentialsDTO credentialDTO) throws AppsException {
        boolean isSendingMailActivated = Boolean.parseBoolean(appsProperties.getIsSendingMailActivated());
        if (!isSendingMailActivated) {
            LOG.warn("WARN : Sending email disabled!");
            return;
        }
        try {
            LOG.info("START : Sending email : {}", request);
            JavaMailSender mailSender;
            String from;

            switch (request.getPurpose()) {
                case NOTIFICATION:
                    mailSender = notificationMailSender;
                    from = (StringUtils.isNotEmpty(request.getFrom())) ? request.getFrom() : appsProperties.getNotificationMailFrom();
                    break;

                default:
                    mailSender = otherMailSender;
                    from = (StringUtils.isNotEmpty(request.getFrom())) ? request.getFrom() : appsProperties.getOtherEmailFrom();
                    break;
            }

            Context context = new Context();
            context.setVariables(request.getParams());

            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper message = new MimeMessageHelper(mimeMessage, true, EmailConfig.EMAIL_TEMPLATE_ENCODING);
            message.setSubject(request.getSubject());
            message.setFrom(from);
            request.setFrom(from); // this setter is to log purpose only (toString)

            if (!request.getToAddresses().isEmpty()) {
                message.setTo(request.getToAddresses().toArray(new String[request.getToAddresses().size()]));
            } else {
                throw new AppsException(AppErrorCode.APPS_EXCEPTION_SYSTEM_EMAIL_TO_ADDRESS_NOT_FOUND);
            }
            if (!request.getCcAddresses().isEmpty()) {
                message.setCc(request.getCcAddresses().toArray(new String[request.getCcAddresses().size()]));
            }
            if (!request.getBccAddresses().isEmpty()) {
                message.setBcc(request.getBccAddresses().toArray(new String[request.getBccAddresses().size()]));
            }

            if (request.getSendType() == DomainConstants.EmailSendType.TEXT) {
                String textContent = "";
                if (StringUtils.isNotEmpty(request.getTemplateName())) {
                    String templatePath = this.appsProperties.getTemplatePath().concat(File.separator).concat("text").concat(File.separator).concat(request.getTemplateName()).concat(".txt");
                    File file = ResourceUtils.getFile(templatePath);
                    textContent = this.templateEngine.process(file.getAbsolutePath(), context);
                } else if (StringUtils.isNotEmpty(request.getTextContent())) {
                    textContent = request.getTextContent();
                }
                message.setText(textContent);
            } else if (request.getSendType() == DomainConstants.EmailSendType.HTML) {
                String templatePath = this.appsProperties.getTemplatePath().concat(File.separator).concat("html").concat(File.separator).concat(request.getTemplateName()).concat(".html");
                File file = ResourceUtils.getFile(templatePath);
                String htmlContent = this.templateEngine.process(file.getAbsolutePath(), context);
                message.setText(htmlContent, true);
            }

            if (request.hasAttachment()) {
                message.addAttachment(request.getAttachmentFileName(), new File(request.getAttachmentPath()));
            }

            if (request.hasAttachmentMap()) {
                for (String attachedFileName : request.getAttachmentMap().keySet()) {
                    InputStreamSource source = new ByteArrayResource(request.getAttachmentMap().get(attachedFileName));
                    message.addAttachment(attachedFileName, source);
                }
            }

            mailSender.send(mimeMessage);
            LOG.info("END : Sending email : {}", request);
        } catch (Exception e) {
            LOG.error("ERROR : Message sending failed : {}", request, e);
            if (e.getMessage().contains("Mailbox full")) {
                throw new AppsException(AppErrorCode.APPS_EXCEPTION_SYSTEM_EMAIL_FAILED_MAILBOX_FULL);
            }
            throw new AppsException(AppErrorCode.APPS_EXCEPTION_SYSTEM_EMAIL_FAILED);
        }
    }

    @Async
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = AppsException.class)
    public void sendMailAsync(EmailSendRequest request, CredentialsDTO credentialsDTO) throws AppsException {
        try {
            this.sendMail(request, credentialsDTO);
        } catch (AppsException e) {
            LOG.error("ERROR : Asynchronous email sending failed : {} : {}", request, credentialsDTO.getId(), e);
        }
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = AppsException.class)
    public String getHTMLContent(EmailSendRequest request, CredentialsDTO credentialDTO) throws AppsException {
        LOG.info("START : Get HTML content for email {} by:{}", request.getTemplateName(), credentialDTO.getId());
        Context context = new Context();
        context.setVariables(request.getParams());
        String templatePath = this.appsProperties.getTemplatePath() + File.separator + "html" + File.separator + request.getTemplateName() + ".html";
        String htmlContent = this.templateEngine.process(templatePath, context);
        LOG.info("END : Get HTML content for email {} by:{}", request.getTemplateName(), credentialDTO.getId());
        return htmlContent;
    }

}

