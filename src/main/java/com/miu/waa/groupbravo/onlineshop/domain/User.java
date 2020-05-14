package com.miu.waa.groupbravo.onlineshop.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "user")
public class User extends  DomainClass{
    private String userNumber;
    private String phone;
    @Column(name="firstname")
    @NotEmpty
    @Size(min=3, max=20, message="{Size.User.firstname.validation}")
    private String firstName;

    @Column(name="lastname")
    @NotEmpty
    @Size(min=3, max=20, message="{Size.User.lastname.validation}")
    private String lastName;
    @NotEmpty
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dob;

    @NotEmpty
    @Size(min=4, max=10, message="{Size.User.username.validation}")

    private String username;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "password")
    @Length(min = 5, message = "*Your password must have at least 5 characters")
    @NotEmpty(message = "*Please provide your password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="user_id")
    private List<Address> addresses;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="user_role_id")
    private UserRole userRole;

    @Enumerated(EnumType.STRING)
    private EUserStatus userStatus;
    @OneToMany(mappedBy ="buyer",cascade = CascadeType.ALL)
    private List<Order> orderList;


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

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public void setAddress(Address address){
        addresses.add(address);
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
