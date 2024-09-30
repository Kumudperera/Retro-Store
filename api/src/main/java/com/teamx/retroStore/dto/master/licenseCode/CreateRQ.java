package com.teamx.retroStore.dto.master.licenseCode;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Schema(name = "LicenseCodeCreateRQ", description = "Request object for create licenseCode")
public class CreateRQ implements Serializable {

    @NotEmpty(message = "The storage package Id is required!")
    private Integer storagePackageId;

    @NotEmpty(message = "The company user Id is required!")
    private Integer companyUserId;
}
