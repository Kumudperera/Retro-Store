package com.teamx.retroStore.dto.master.licenseCode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LicenseCodeDetails {
    public String email;
    public String licenseType;
    public String issueDate;
    public String expireDate;

    public LicenseCodeDetails() {}
    public LicenseCodeDetails(String[] parts) {
        this.email = parts[0];
        this.licenseType = parts[1];
        this.issueDate = parts[2];
        this.expireDate = parts[3];
    }
}
