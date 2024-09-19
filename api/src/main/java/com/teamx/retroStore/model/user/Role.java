package com.teamx.retroStore.model.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import com.teamx.retroStore.model.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "T_ROLE")
public class Role extends UserTraceableEntity implements AuditableEntity {

    @Getter
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "ID")
    private Integer ID;

    @Getter
    @Column(name = "NAME")
    private String name;

    @Getter
    @Column(name = "DESCRIPTION")
    private String description;

    @Getter
    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "T_ROLE_PRIVILEGE",
            joinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "PRIVILEGE_ID", referencedColumnName = "ID")})
    private Set<Privilege> privileges;

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(AppsConstants.Status status) {
        this.status = status;
    }

    public Set<Privilege> getPrivileges() {
        if (privileges == null) {
            this.privileges = new HashSet<>();
        }
        return privileges;
    }

    public void setPrivileges(Set<Privilege> privileges) {
        this.privileges = privileges;
    }

    public void removePrivileges(List<Privilege> removeSet) {
        if (removeSet != null && removeSet.size() > 0) {
            privileges.removeAll(removeSet);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return getName() != null ? getName().equals(role.getName()) : role.getName() == null;
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }

    @Override
    public String getAuditSummary() {
        return new StringBuilder().append("{")
                .append("\"Role ID\":").append(getStringValue(ID))
                .append(", \"Role Name\":\"").append(getStringValue(name)).append('\"')
                .append(", \"Description\":\"").append(getStringValue(description)).append('\"')
                .append(", \"status\":\"").append(getStringValue(status.getDescription()))
                .append("\"}")
                .toString();
    }

    @Override
    public String getAuditContent() {
        return new StringBuilder().append("{")
                .append("\"Role ID\":").append(getStringValue(ID))
                .append(", \"Role Name\":\"").append(getStringValue(name)).append('\"')
                .append(", \"Description\":\"").append(getStringValue(description)).append('\"')
                .append(", \"Status\":\"").append(getStringValue(status.getDescription())).append('\"')
                .append(", \"Privileges\": [").append(getPrivileges().stream().map(Privilege::getAuditSummary).collect(Collectors.joining(",")))
                .append("]}")
                .toString();
    }
}
