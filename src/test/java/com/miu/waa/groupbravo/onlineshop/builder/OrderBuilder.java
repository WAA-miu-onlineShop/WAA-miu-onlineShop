package com.miu.waa.groupbravo.onlineshop.builder;

import com.miu.waa.groupbravo.onlineshop.domain.*;

import java.math.BigDecimal;
import java.util.List;

public class OrderBuilder {
    private Order order;

    public OrderBuilder(){
        order=new Order();
    }

    public OrderBuilder withOrderNumber(String orderNumber){
        order.setOrderNumber(orderNumber);
        return this;
    }
    public OrderBuilder withTotalAmount(BigDecimal totalAmount){
        this.order.setTotalAmount(totalAmount);
        return this;
    }
    public OrderBuilder withCoupons(BigDecimal coupons){
        this.order.setCoupons(coupons);
        return this;
    }
    public  OrderBuilder withStatus(EOrderStatus status){
        this.order.setOrderStatus(status);
        return this;
    }


    public  OrderBuilder withBuyer(User buyer){
        this.order.setBuyer(buyer);
        return this;
    }

    public OrderBuilder withShippingAddress(Address address){
        this.order.setShippingAddress(address);
        return this;
    }
    public OrderBuilder withOrderLineList(List<OrderLine> orderLines){
        this.order.setOrderLineList(orderLines);
        return this;
    }
    public Order build(){
        return this.order;
    }

}
