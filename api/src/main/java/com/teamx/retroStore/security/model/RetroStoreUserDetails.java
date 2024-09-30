package com.teamx.retroStore.security.model;

import com.teamx.retroStore.security.dto.AuthDTO;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

public class RetroStoreUserDetails implements UserDetails {

    @Getter
    private final AuthDTO authUser;

    private final List<GrantedAuthority> authorities;

    public RetroStoreUserDetails(AuthDTO authDTO) {
        this.authUser = authDTO;
        this.authorities = generateAuthorities(authDTO);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return authUser.getPassword();
    }

    @Override
    public String getUsername() {
        return authUser.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    private List<GrantedAuthority> generateAuthorities(AuthDTO user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        user.getPrivileges().forEach((privilege) -> authorities.add(new SimpleGrantedAuthority(privilege)));
        return authorities;
    }

    public Integer getUserID() {
        return authUser.getId();
    }

    public Set<String> getPrivileges() {
        return this.authUser.getPrivileges();
    }
}
