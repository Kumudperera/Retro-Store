package com.teamx.retroStore.model;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@Entity
@Table(name = "t_storage_package")
public class StoragePackage extends UserTraceableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "STORAGE")
    private BigDecimal storage;

    @Enumerated(EnumType.STRING)
    @Column(name = "STORAGE_UNIT")
    private DomainConstants.StorageUnits storageUnit = DomainConstants.StorageUnits.GB;

    @Enumerated(EnumType.STRING)
    @Column(name = "PERIOD")
    private DomainConstants.StoragePackagePeriods period = DomainConstants.StoragePackagePeriods.MONTHLY;

    @Column(name = "PERIOD_PRICE")
    private BigDecimal periodPrice;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status = AppsConstants.Status.ACT;
}
