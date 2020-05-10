package com.miu.waa.groupbravo.onlineshop.domain;

public enum EAddressRole {
    SHIPPING("Shipping"),
    BILLING("Billing");
    private String status;
    private EAddressRole(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
