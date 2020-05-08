package com.miu.waa.groupbravo.onlineshop.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;
import java.util.List;

@Entity
public class User  extends  DomainClass{
    private String userNumber;
    private String phone;
    private  String firstName;
    private String lastName;
    private LocalDate dob;
    @OneToOne
    @JoinColumn(name="address_id")
    private Address address;
    @OneToOne
    @JoinColumn(name="shipping_address_id")
    private Address shippingAddress;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_role_id")
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private EUserStatus userStatus;
    @OneToMany(mappedBy ="buyer",cascade = CascadeType.ALL)
    private List<Order> orderList;

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public String getUserNumber() {
        return userNumber;
    }

    public void setUserNumber(String userNumber) {
        this.userNumber = userNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public EUserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(EUserStatus userStatus) {
        this.userStatus = userStatus;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(userNumber, user.userNumber)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(userNumber)
                .toHashCode();
    }
}
