package com.teamx.retroStore.model;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "t_license_code_history")
public class LicenseCodeHistory extends UserTraceableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LICENSE_CODE_ID")
    private LicenseCode licenseCode;

    @Column(name = "PURCHASED_DATE")
    private Date purchasedDate;

    @Column(name = "NEXT_PAYMENT_DATE")
    private Date nextPaymentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status = AppsConstants.Status.ACT;
}

