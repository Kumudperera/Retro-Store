package com.teamx.retroStore.dto.master.licenseCode;

import com.teamx.retroStore.common.constant.AppsConstants;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class LicenseCodeRQ {

    private Integer id;

    private AppsConstants.YesNo valid;

    private AppsConstants.Status status;

    private Integer storagePackageId;

    private Integer companyUserId;

    private BigDecimal usedStorage;

    public LicenseCodeRQ(CreateRQ createRQ) {
        this.storagePackageId = createRQ.getStoragePackageId();
        this.companyUserId = createRQ.getCompanyUserId();
    }

    public LicenseCodeRQ(Integer id, UpdateRQ updateRQ) {
        this.id = id;
        this.storagePackageId = updateRQ.getStoragePackageId();
        this.valid = updateRQ.getValid();
        this.status = updateRQ.getStatus();
        this.usedStorage = updateRQ.getUsedStorage();
    }
}
