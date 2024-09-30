package com.teamx.retroStore.model.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.model.common.audit.AuditableEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "T_PRIVILEGE")
public class Privilege implements AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @Column(name = "ID")
    private Integer id;

    @Column(name = "CODE")
    private String code;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BEHAVIOUR_DESCRIPTION")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PRIVILEGE_CATEGORY_ID")
    private PrivilegeCategory privilegeCategory;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PrivilegeCategory getPrivilegeCategory() {
        return privilegeCategory;
    }

    public void setPrivilegeCategory(PrivilegeCategory privilegeCategory) {
        this.privilegeCategory = privilegeCategory;
    }

    public AppsConstants.Status getStatus() {
        return status;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Privilege privilege = (Privilege) o;

        return getCode() != null ? getCode().equals(privilege.getCode())
                : privilege.getCode() == null;
    }

    @Override
    public int hashCode() {
        return getCode() != null ? getCode().hashCode() : 0;
    }

    @Override
    public String getAuditSummary() {
        return new StringBuilder().append("{").append("\"Privilege ID\":\"").append(getStringValue(id))
                .append("\"").append(", \"Privilege Name\":\"").append(getStringValue(name)).append("\"")
                .append(", \"Status\":\"").append(getStringValue(status.getDescription())).append("\"").append("}")
                .toString();
    }

    @Override
    public String getAuditContent() {
        return new StringBuilder().append("{").append("\"Privilege ID\":\"").append(getStringValue(id))
                .append("\"").append(", \"Privilege Code\":\"").append(getStringValue(code)).append('\"')
                .append(", \"Privilege Name\":\"").append(getStringValue(name)).append("\"")
                .append(", \"Description\":\"").append(getStringValue(description)).append("\"")
                .append(", \"Privilege Category\":\"").append(getStringValue(privilegeCategory)).append("\"")
                .append(", \"Status\":\"").append(getStringValue(status.getDescription())).append("\"").append("}")
                .toString();
    }
}
