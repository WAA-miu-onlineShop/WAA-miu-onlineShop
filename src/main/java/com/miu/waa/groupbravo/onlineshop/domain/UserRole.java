package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

public class UserRole extends DomainClass{
    private String name;
    private String description;
    @Enumerated(EnumType.STRING)
    private ERoleType roleType;
}
