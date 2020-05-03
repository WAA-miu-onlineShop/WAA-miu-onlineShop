package com.miu.waa.groupbravo.onlineshop.domain;

public enum EOrderStatus {
    NEW("new"),
    CANCELLED("Cancelled"),
    PAID("Paid"),
    SHIPPED("Shipped"),
    ON_THE_WAY("On the way"),
    DELIVERED("Delivered");
    private String status;
    private EOrderStatus(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
