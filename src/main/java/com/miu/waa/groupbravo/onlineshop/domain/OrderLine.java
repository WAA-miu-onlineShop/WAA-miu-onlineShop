package com.miu.waa.groupbravo.onlineshop.domain;

import java.math.BigDecimal;

public class OrderLine extends DomainClass {
    private String description;
    private BigDecimal quantity=BigDecimal.ZERO;
    private BigDecimal amount=BigDecimal.ZERO;
    private Order order;
    private Product product;
}
