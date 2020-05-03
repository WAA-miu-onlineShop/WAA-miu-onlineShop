package com.miu.waa.groupbravo.onlineshop.domain;

import java.time.LocalDateTime;

public class OrderHistory extends  DomainClass {
    private String description;
    private LocalDateTime historyDate;
    private Order order;
    private EOrderStatus orderStatus;
    private User user;
}
