package com.miu.waa.groupbravo.onlineshop.domain;

public enum EUserStatus {
    NEW("New"),
    APPROVED("Approved"),
    DISABLED("Disabled");
    private String status;
    private EUserStatus(String status){
        this.status=status;
    }
    public String getStatus() {
        return status;
    }
}
