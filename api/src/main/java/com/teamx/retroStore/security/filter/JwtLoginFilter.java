package com.teamx.retroStore.security.filter;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamx.retroStore.security.dto.AuthDTO;
import com.teamx.retroStore.security.dto.LoginCredentials;
import com.teamx.retroStore.security.model.RetroStoreUserDetails;
import com.teamx.retroStore.security.service.JwtAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import javax.crypto.Cipher;
import javax.crypto.spec.OAEPParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.PrivateKey;
import java.util.Base64;
import java.util.Collections;

public class JwtLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger LOG = LoggerFactory.getLogger(JwtLoginFilter.class);

    private final UserDetailsService userDetailsService;
    private final PrivateKey privateKey;

    public JwtLoginFilter(String defaultFilterProcessesUrl, AuthenticationManager authenticationManager,
                          UserDetailsService userDetailsService, PrivateKey privateKey) {
        super(defaultFilterProcessesUrl, authenticationManager);
        this.privateKey = privateKey;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        Authentication auth = null;
        try {
            LoginCredentials credentials = new ObjectMapper().readValue(request.getInputStream(), LoginCredentials.class);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                    credentials.getUsername(),
                    credentials.getPassword(),
                    Collections.emptyList()
            );

            auth = getAuthenticationManager().authenticate(token);
        } catch (Exception e) {
            String message = null;

            if (e instanceof JsonMappingException) {
                message = "Malformed login request";
                logger.warn("Invalid login request JSON format", e);
                unsuccessfulAuthentication(request, response, authenticationException(message, e));
            } else if (e instanceof BadCredentialsException) {
                message = "Invalid login credentials";
                logger.warn("Invalid login credentials", e);
                unsuccessfulAuthentication(request, response, authenticationException(message, e));
            } else {
                message = "Unknown login error";
                logger.warn("Login error ", e);
                unsuccessfulAuthentication(request, response, authenticationException(message, e));
            }
        }
        return auth;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        AuthDTO authDTO = null;
        try {

            RetroStoreUserDetails retroStoreUserDetails = (RetroStoreUserDetails) this.userDetailsService.loadUserByUsername(authResult.getName());
            authDTO = retroStoreUserDetails.getAuthUser();

        } catch (Exception e) {
            LOG.warn("Error in post authentication user extraction for request : {} : ", request);
        }
        JwtAuthenticationService.addAuthentication(response, authDTO);
    }

    private String rsaDecodePassword(String password) throws GeneralSecurityException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/OAEPPadding");

        cipher.init(Cipher.DECRYPT_MODE, this.privateKey, OAEPParameterSpec.DEFAULT);
        return new String(cipher.doFinal(Base64.getDecoder().decode(password)));
    }

    private AuthenticationException authenticationException(String message, Throwable e) {
        return new AuthenticationException(message, e) {
            @Override
            public synchronized Throwable getCause() {
                return super.getCause();
            }
        };
    }
}
