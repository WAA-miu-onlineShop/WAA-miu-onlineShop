package com.miu.waa.groupbravo.onlineshop.domain;

import java.math.BigDecimal;

public class Payment extends DomainClass {
    private String paymentNumber;
    private BigDecimal paidAmount=BigDecimal.ZERO;
    private Order order;
}
