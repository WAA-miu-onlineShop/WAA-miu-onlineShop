package com.miu.waa.groupbravo.onlineshop.domain;


public enum ESequenceType {

	ORDER("Order"),
	PAYMENT("Payment"),
	USER("User"),
	PRODUCT("Product"),
	COUPON("Coupon");
	private String status;
	

	private ESequenceType(String status) {
		this.status=status;
	}

	public String getStatus() {
		return status;
	}
	

}
