package com.miu.waa.groupbravo.onlineshop.domain;

public enum EReviewStatus {
    NEW("New"),
    APPROVED("Approved"),
    CANCELLED("Cancelled");
    private String status;
    private EReviewStatus(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
