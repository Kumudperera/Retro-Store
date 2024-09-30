package com.teamx.retroStore.dto.master.licenseCode;

import com.teamx.retroStore.model.LicenseCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class LicenseCodeDTO implements Serializable {

    private Integer id;
    public LicenseCodeDTO() {}

    public LicenseCodeDTO(LicenseCode licenseCode) {
        this.id = licenseCode.getId();
    }
}
