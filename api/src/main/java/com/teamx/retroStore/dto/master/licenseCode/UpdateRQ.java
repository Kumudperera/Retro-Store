package com.teamx.retroStore.dto.master.licenseCode;


import com.teamx.retroStore.common.constant.AppsConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Schema(name = "LicenseCodeUpdateRQ", description = "Request object for update licenseCode")
public class UpdateRQ implements Serializable {

    @NotEmpty(message = "Valid is required!")
    private AppsConstants.YesNo valid;
    @NotEmpty(message = "Status is required!")
    private AppsConstants.Status status;

    private Integer storagePackageId;

    private BigDecimal usedStorage;
}
