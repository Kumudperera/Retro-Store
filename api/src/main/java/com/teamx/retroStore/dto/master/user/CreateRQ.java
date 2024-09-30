package com.teamx.retroStore.dto.master.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@Schema(name = "UserCreateRQ", description = "Request object for create user")
public class CreateRQ implements Serializable {

    @NotEmpty(message = "Username is required!")
    private String username;

    @NotEmpty(message = "Email is required!")
    @Email(regexp = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$", message = "invalid email")
    private String email;

    @NotEmpty(message = "User Type is required!")
    private DomainConstants.UserType userType;

    @NotEmpty(message = "First name is required!")
    private String firstName;

    @NotEmpty(message = "Last name is required!")
    private String lastName;

    @NotEmpty(message = "Status is is required!")
    private AppsConstants.Status status;

    private String password;

    private List<Integer> addedRoles;
}
