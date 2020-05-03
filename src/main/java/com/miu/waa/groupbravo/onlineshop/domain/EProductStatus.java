package com.miu.waa.groupbravo.onlineshop.domain;

public enum EProductStatus {
    NEW("New"),
    AVAILABLE("Available"),
    ORDERED("Ordered"),
    PURCHASED("Purchased");
    private String status;
    private EProductStatus(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
