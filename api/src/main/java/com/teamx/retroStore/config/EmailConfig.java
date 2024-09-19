package com.teamx.retroStore.config;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.FileTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import org.thymeleaf.templateresolver.StringTemplateResolver;

import java.io.IOException;
import java.util.Properties;

/**
 * Email Sending Configurations
 */
@Configuration
@PropertySource(name = "apps.properties", value = "classpath:apps.properties")
@PropertySource(name = "messaging.properties", value = "classpath:messaging.properties")
public class EmailConfig implements EnvironmentAware, ApplicationContextAware {

    public static final String EMAIL_TEMPLATE_ENCODING = "UTF-8";

    private static final String HOST = "apps.mail.server.host";
    private static final String PORT = "apps.mail.server.port";
    private static final String PROTOCOL = "apps.mail.server.protocol";
    private static final String USERNAME = "apps.mail.server.username";
    private static final String PASSWORD = "apps.mail.server.password";
    private static final String SMTP_AUTH = "mail.smtp.auth";
    private static final String TLS_ENABLED = "mail.smtp.starttls.enable";
    private static final String QUIT_WAIT = "mail.smtp.quitwait";

    public static final String OTHER = ".other";
    public static final String NOTIFICATION = ".notification";

    private Environment environment;

    private ApplicationContext applicationContext;

    @Bean(name = "otherMailSender")
    public JavaMailSender otherMailSender() throws IOException {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST + OTHER));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT + OTHER)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL + OTHER));
        mailSender.setUsername(this.environment.getProperty(USERNAME + OTHER));
        mailSender.setPassword(this.environment.getProperty(PASSWORD + OTHER));

        // JavaMail-specific mail sender configuration, based on javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.put(SMTP_AUTH, this.environment.getProperty(SMTP_AUTH));
        javaMailProperties.put(TLS_ENABLED, this.environment.getProperty(TLS_ENABLED));
        javaMailProperties.put(QUIT_WAIT, this.environment.getProperty(QUIT_WAIT));
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    @Bean(name = "notificationMailSender")
    public JavaMailSender notificationMailSender() throws IOException {

        final JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

        // Basic mail sender configuration, based on emailconfig.properties
        mailSender.setHost(this.environment.getProperty(HOST + NOTIFICATION));
        mailSender.setPort(Integer.parseInt(this.environment.getProperty(PORT + NOTIFICATION)));
        mailSender.setProtocol(this.environment.getProperty(PROTOCOL + NOTIFICATION));
        mailSender.setUsername(this.environment.getProperty(USERNAME + NOTIFICATION));
        mailSender.setPassword(this.environment.getProperty(PASSWORD + NOTIFICATION));

        // JavaMail-specific mail sender configuration, based on javamail.properties
        final Properties javaMailProperties = new Properties();
        javaMailProperties.put(SMTP_AUTH, this.environment.getProperty(SMTP_AUTH));
        javaMailProperties.put(TLS_ENABLED, this.environment.getProperty(TLS_ENABLED));
        javaMailProperties.put(QUIT_WAIT, this.environment.getProperty(QUIT_WAIT));
        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    @Bean(name = "emailTemplateEngine")
    public TemplateEngine emailTemplateEngine() {

        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        // Resolver for TEXT emails
        templateEngine.addTemplateResolver(textTemplateResolver());
        // Resolver for HTML emails (except the editable one)
        templateEngine.addTemplateResolver(htmlTemplateResolver());
        // Resolver for HTML editable emails (which will be treated as a String)
        templateEngine.addTemplateResolver(stringTemplateResolver());
        // Message source, internationalization specific to emails
        templateEngine.setTemplateEngineMessageSource(emailMessageSource());
        //templateEngine.setTemplateResolver(textTemplateResolver());
        return templateEngine;
    }

    private ITemplateResolver textTemplateResolver() {
        final FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setOrder(1);
        templateResolver.setTemplateMode(TemplateMode.TEXT);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver htmlTemplateResolver() {
        final FileTemplateResolver templateResolver = new FileTemplateResolver();
        templateResolver.setOrder(2);
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding(EMAIL_TEMPLATE_ENCODING);
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    private ITemplateResolver stringTemplateResolver() {
        final StringTemplateResolver templateResolver = new StringTemplateResolver();
        templateResolver.setOrder(3);
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    @Bean
    public ResourceBundleMessageSource emailMessageSource() {
        final ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("template/mail/MailMessages");
        return messageSource;
    }

    @Override
    public void setEnvironment(final Environment environment) {
        this.environment = environment;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

}
