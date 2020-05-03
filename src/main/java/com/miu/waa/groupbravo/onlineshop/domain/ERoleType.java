package com.miu.waa.groupbravo.onlineshop.domain;

public enum ERoleType {
    ADMIN("Admin"),
    SELLER("Seller"),
    BUYER("Buyer");
    private String status;
    private ERoleType(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
