package com.teamx.retroStore.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class AppsProperties {

    @Value("${private.key.der.absolute.path}")
    private String privateKeyDerAbsolutePath;

    @Value("${public.key.der.absolute.path}")
    private String publicKeyDerAbsolutePath;

    @Value("${public.key.pem.absolute.path}")
    private String publicKeyPemAbsolutePath;

    @Value("${apps.user.generated.password.length}")
    private String userGeneratedPasswordLength;

    @Value("${apps.azure.endpoint.url}")
    private String storageBaseURL;

    @Value("${apps.teamx.retroStore.web.url}")
    private String retroStoreWebURL;

    @Value("${apps.config.email.template.path}")
    private String templatePath;

    @Value("${apps.mail.server.from.other}")
    private String otherEmailFrom;

    @Value("${apps.mail.server.from.notification}")
    private String notificationMailFrom;

    @Value("${apps.send.email}")
    private String isSendingMailActivated;

    @Value("${apps.teamx.retroStore.reset.password.link.expire.in.minute}")
    private Integer passwordResetLinkExpireTimeIn;

    @Value("${apps.teamx.retroStore.base.be.url}")
    private String baseBEURL;

    @Value("${api.prefix.v1}")
    private String apiPrefix;

    @Value("${apps.teamx.retroStore.welcome.image.url}")
    private String welcomeImageUrl;

    @Value("${apps.teamx.retroStore.admin.user.username}")
    private String adminUsername;

    @Value("${apps.teamx.retroStore.admin.user.email}")
    private String adminUserEmail;

    @Value("${apps.teamx.retroStore.admin.user.password}")
    private String adminUserPassword;



}
