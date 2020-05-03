package com.miu.waa.groupbravo.onlineshop.domain;

import java.math.BigDecimal;

public class Coupon  extends DomainClass{
    private String couponNumber;
    private User buyer;
    private BigDecimal totalPoint=BigDecimal.ZERO;
}
