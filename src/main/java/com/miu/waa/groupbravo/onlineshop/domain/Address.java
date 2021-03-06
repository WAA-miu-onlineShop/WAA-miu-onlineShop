package com.miu.waa.groupbravo.onlineshop.domain;
import com.miu.waa.groupbravo.onlineshop.repository.AddressRepository;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Entity
@NamedQueries({@NamedQuery(name= AddressRepository.QUERY_NAME.findAddressByUserAndAddressType,query = AddressRepository.QUERY.findAddressByUserAndAddressType)})
public class Address extends  DomainClass{
    @NotEmpty
    @Size(min=3, max= 50, message="{Size.Address.city.validation}")
    private String city;
    @NotEmpty
    @Size(min=2, max= 100, message="{Size.Address.street.validation}")
    private String street;
    @NotEmpty
    @Size(min=2, max= 100, message="{Size.Address.state.validation}")
    private String state;
    @NotEmpty
    @Size(min = 5, max = 5,message="{Size.Address.zipcode.validation}")
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
    public String toString() {
        return "[" +
                "city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ']';
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
