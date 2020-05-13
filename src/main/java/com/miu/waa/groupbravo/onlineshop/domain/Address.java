package com.miu.waa.groupbravo.onlineshop.domain;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
public class Address extends  DomainClass{
    private String city;
    private String street;
    private String state;
    private String zipCode;
    @Enumerated(EnumType.STRING)
    private EAddressRole addressRole;

    public EAddressRole getAddressRole() {
        return addressRole;
    }

    public void setAddressRole(EAddressRole addressRole) {
        this.addressRole = addressRole;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        return new EqualsBuilder()
                .append(city, address.city)
                .append(street, address.street)
                .append(state, address.state)
                .append(zipCode, address.zipCode)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(city)
                .append(street)
                .append(state)
                .append(zipCode)
                .toHashCode();
    }
}
