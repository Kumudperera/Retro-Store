package com.teamx.retroStore.security.dto;

import com.teamx.retroStore.dto.master.user.UserDTO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class AuthDTO extends UserDTO {

    private String accessToken;

    private String refreshToken;

    public AuthDTO() {
    }
}
