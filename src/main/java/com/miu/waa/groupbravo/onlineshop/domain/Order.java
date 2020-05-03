package com.miu.waa.groupbravo.onlineshop.domain;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.util.List;

public class Order extends DomainClass {
    private String orderNumber;
    private BigDecimal totalAmount=BigDecimal.ZERO;
    private BigDecimal coupons=BigDecimal.ZERO;
    @Enumerated(EnumType.STRING)
    private EOrderStatus orderStatus;
    private User buyer;
    private Address shippingAddress;
    private List<OrderLine> orderLineList;
}
