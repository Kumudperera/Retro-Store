package com.teamx.retroStore.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.nio.file.NoSuchFileException;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.PKCS8EncodedKeySpec;

@Configuration
public class SecurityKeys {

    private static final Logger LOG = LoggerFactory.getLogger(SecurityKeys.class);

    private final AppsProperties appsProperties;

    public SecurityKeys(AppsProperties appsProperties) {
        this.appsProperties = appsProperties;
    }

    @Bean
    public PrivateKey privateKey() throws Exception {
        String absolutePathToKey = this.appsProperties.getPrivateKeyDerAbsolutePath();
        byte[] privKeyByteArray = null;
        try {
            InputStream privateKeyInputStream = new ClassPathResource(absolutePathToKey).getInputStream();
            privKeyByteArray = privateKeyInputStream.readAllBytes();
        } catch (NoSuchFileException e) {
            LOG.error("Defined private key file path :{} does not exist", absolutePathToKey);
            throw e;
        }
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privKeyByteArray);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePrivate(keySpec);
    }
}
