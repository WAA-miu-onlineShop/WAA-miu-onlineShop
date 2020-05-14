package com.miu.waa.groupbravo.onlineshop.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "userrole")
public class UserRole extends  DomainClass{
    @NotEmpty
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ERoleType roleType;
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

    public ERoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(ERoleType roleType) {
        this.roleType = roleType;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRole userRole = (UserRole) o;

        return new EqualsBuilder()
                .append(name, userRole.name)
                .append(roleType, userRole.roleType)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .append(roleType)
                .toHashCode();
    }
}
