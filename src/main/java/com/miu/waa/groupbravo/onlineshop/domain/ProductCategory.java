package com.miu.waa.groupbravo.onlineshop.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ProductCategory extends DomainClass {
    private String description;
    private String name;
    private User seller;
    private BigDecimal quantityAvailable=BigDecimal.ZERO;
    private BigDecimal quantityPurchased=BigDecimal.ZERO;
}
