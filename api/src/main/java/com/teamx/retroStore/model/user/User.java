package com.teamx.retroStore.model.user;

import com.teamx.retroStore.common.constant.AppsConstants;
import com.teamx.retroStore.common.constant.DomainConstants;
import com.teamx.retroStore.model.common.UserTraceableEntity;
import com.teamx.retroStore.model.common.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "T_USER")
public class User extends UserTraceableEntity implements AuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native")
    @Column(name = "ID")
    private Integer ID;

    @Column(name = "USERNAME")
    private String username;

    @Enumerated(EnumType.STRING)
    @Column(name = "USER_TYPE")
    private DomainConstants.UserType userType;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS")
    private AppsConstants.Status status;

    @Column(name = "LINK_EXPIRE_TIME")
    private Date linkExpireTime;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "T_USER_ROLE",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "ROLE_ID", referencedColumnName = "ID")})
    private Set<Role> roles;

    public Set<Role> getRoles() {
        if (roles == null) {
            roles = new HashSet<>();
        }
        return roles;
    }

    public void removeRoles(List<Role> removeSet) {
        if (removeSet != null && removeSet.size() > 0) {
            this.getRoles().removeAll(removeSet);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        User user = (User) obj;
        return getUsername() != null ? getUsername().equals(user.getUsername()) : user.getUsername() == null;
    }

    @Override
    public int hashCode() {
        return getUsername() != null ? getUsername().hashCode() : 0;
    }

    @Override
    public String getAuditSummary() {
        return new StringBuilder().append("{")
                .append("\"User ID\":\"").append(getStringValue(ID)).append("\"")
                .append(", \"User Name\":\"").append(getStringValue(username)).append('\"')
                .append(", \"First Name\":\"").append(getStringValue(firstName)).append('\"')
                .append(", \"Last Name\":\"").append(getStringValue(lastName)).append('\"')
                .append(", \"Status\":\"").append(getStringValue(status.getDescription())).append('\"')
                .append("}")
                .toString();
    }

    @Override
    public String getAuditContent() {
        StringBuilder content = new StringBuilder().append("{")
                .append("\"User ID\":\"").append(getStringValue(ID)).append("\"")
                .append(", \"User Name\":\"").append(getStringValue(username)).append('\"')
                .append(", \"First Name\":\"").append(getStringValue(firstName)).append('\"')
                .append(", \"Last Name\":\"").append(getStringValue(lastName)).append('\"')
                .append(", \"Email\":\"").append(getStringValue(email)).append('\"')
                .append(", \"Status\":\"").append(getStringValue(status.getDescription())).append('\"')
                .append(", \"Roles\":").append(getSummaryStringList(getRoles()));

        content.append('}');
        return content.toString();
    }

}
