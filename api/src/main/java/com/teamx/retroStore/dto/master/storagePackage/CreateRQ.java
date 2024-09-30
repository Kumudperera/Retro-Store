package com.teamx.retroStore.dto.master.storagePackage;

import com.teamx.retroStore.common.constant.AppsConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.math.BigDecimal;

@Setter
@Getter
@ToString
public class CreateRQ implements Serializable {
    private String name;

    private String description;

    private BigDecimal storage;

    private BigDecimal price;

    private AppsConstants.Status status = AppsConstants.Status.ACT;
}
