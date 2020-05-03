package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;

public class Product extends DomainClass {
    private String serialNumber;
    private String name;
    private String description;
    private BigDecimal unitPrice=BigDecimal.ZERO;
    private User seller;
    @Enumerated(EnumType.STRING)
    private EProductStatus productStatus;
    private ProductCategory productCategory;
}
