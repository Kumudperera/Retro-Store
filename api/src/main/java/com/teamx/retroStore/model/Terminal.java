package com.teamx.retroStore.model;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import com.teamx.retroStore.model.user.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "t_terminal")
public class Terminal extends UserTraceableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "ID")
    private Integer id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TERMINAL_USER_ID")
    private User terminalUser;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LICENSE_CODE_ID")
    private LicenseCode licenseCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_USER_ID")
    private User companyUser;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status = AppsConstants.Status.ACT;
}

