package com.teamx.retroStore.dto.master.storagePackage;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.model.StoragePackage;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

@Getter
@Setter
public class StoragePackageDTO implements Serializable {

    private Integer id;

    private String name;

    private String description;

    private BigDecimal storage;

    private String storageUnit;

    private DomainConstants.StoragePackagePeriods period;

    private BigDecimal periodPrice;

    private AppsConstants.Status status;

    public StoragePackageDTO() {}

    public StoragePackageDTO(ResultSet rs) throws SQLException {
        this.id = rs.getInt("tsp.ID");
        this.name = rs.getString("tsp.NAME");
        this.description = rs.getString("tsp.DESCRIPTION");
        this.storage = rs.getBigDecimal("tsp.STORAGE");
        this.storageUnit = rs.getString("tsp.STORAGE_UNIT");
        this.period = DomainConstants.StoragePackagePeriods.valueOf(rs.getString("tsp.PERIOD"));
        this.periodPrice = rs.getBigDecimal("tsp.PERIOD_PRICE");
        this.status = AppsConstants.Status.resolveStatus(rs.getString("tsp.STATUS"));
    }

    public StoragePackageDTO(StoragePackage storagePackage) {
        this.id = storagePackage.getId();
        this.name = storagePackage.getName();
        this.description = storagePackage.getDescription();
        this.storage = storagePackage.getStorage();
        this.periodPrice = storagePackage.getPeriodPrice();
        this.status = storagePackage.getStatus();
    }
}
