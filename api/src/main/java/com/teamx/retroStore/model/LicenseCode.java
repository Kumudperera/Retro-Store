package com.teamx.retroStore.model;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.exception.impl.AppsException;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import com.teamx.retroStore.model.user.User;
import com.teamx.retroStore.security.dto.CredentialsDTO;
import com.teamx.retroStore.util.CalendarUtil;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "t_license_code")
public class LicenseCode extends UserTraceableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "PURCHASED_DATE")
    private Date purchasedDate;

    @Column(name = "NEXT_PAYMENT_DATE")
    private Date nextPaymentDate;

    @Column(name = "PURCHASED_STORAGE")
    private BigDecimal purchasedStorage;

    @Column(name = "REMAIN_STORAGE")
    private BigDecimal remainStorage;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORAGE_PACKAGE_ID")
    private StoragePackage storagePackage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_USER_ID")
    private User companyUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "VALID")
    private AppsConstants.YesNo valid = AppsConstants.YesNo.Y;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status = AppsConstants.Status.ACT;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.ALL}, orphanRemoval = true, mappedBy = "licenseCode")
    private Set<LicenseCodeHistory> licenseCodeHistories;

    public Set<LicenseCodeHistory> getLicenseCodeHistories() {
        if (this.licenseCodeHistories == null) {
            this.licenseCodeHistories = new HashSet<>();
        }

        return this.licenseCodeHistories;
    }

    public void addLicenseCodeHistory(Date now, CredentialsDTO credentialsDTO) throws AppsException {
        LicenseCodeHistory licenseCodeHistory = new LicenseCodeHistory();

        if (this.getStoragePackage() == null) {
            throw new AppsException("Please assign a storage package");
        }

        licenseCodeHistory.setPurchasedDate(this.purchasedDate);
        licenseCodeHistory.setNextPaymentDate(this.nextPaymentDate);
        licenseCodeHistory.setStatus(AppsConstants.Status.ACT);
        licenseCodeHistory.setLicenseCode(this);
        licenseCodeHistory.setCreatedBy(credentialsDTO.getId());
        licenseCodeHistory.setCreatedDate(now);

        this.getLicenseCodeHistories().add(licenseCodeHistory);
    }
}

