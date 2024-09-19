package com.teamx.retroStore.security.service;

import com.teamx.retroStore.security.dto.AuthDTO;
import com.teamx.retroStore.security.model.RetroStoreUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class RetroStoreUserDetailsService implements UserDetailsService {

    private final SecurityService securityService;

    @Autowired
    public RetroStoreUserDetailsService(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AuthDTO authDTO;
        try {
            authDTO = this.securityService.getUserByUsername(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Invalid identification");
        }
        return new RetroStoreUserDetails(authDTO);
    }
}
