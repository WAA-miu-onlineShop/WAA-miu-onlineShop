package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDate;

public class User  extends  DomainClass{
    private String userNumber;
    private String phone;
    private  String firstName;
    private String lastName;
    private LocalDate dob;
    private Address address;
    private Address ShippingAddress;
    private UserRole userRole;
    @Enumerated(EnumType.STRING)
    private EUserStatus userStatus;

}
