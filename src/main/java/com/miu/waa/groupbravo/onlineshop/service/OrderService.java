package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.User;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface OrderService {

    public abstract Order saveOrder(Order order);
    //public abstract EOrderStatus getStatusByName(EOrderStatus status);
    public abstract List<Order> getAllOrders();
    public abstract List<Order> getAllOrdersByUser(Long userId);
    public abstract  Order getOrderById(Long id);
    public abstract  Order cancelOrder(Order order) ;
    public abstract Order payOrder(Order order) ;
    public abstract  Order shippingOrder(Order order) ;
    public abstract  Order delivering(Order order) ;
    public abstract  List<Order> findOrderBySellerAndStatus(User seller,List<EOrderStatus> statusList);
    public abstract  List<Order> findOrderByBuyerAndStatus(User buyer,List<EOrderStatus> statusList);
    Order findById(Long orderId);
}
