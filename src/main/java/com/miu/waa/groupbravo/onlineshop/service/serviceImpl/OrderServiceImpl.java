package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;

import java.io.ByteArrayInputStream;
import java.util.List;

public class OrderServiceImpl implements OrderService {
    @Override
    public Order save(Order order) {
        return null;
    }

    @Override
    public EOrderStatus getStatusByName(EOrderStatus status) {
        return null;
    }

    @Override
    public List<Order> getAllOrders() {
        return null;
    }

    @Override
    public List<Order> getAllOrdersByUser(Long userId) {
        return null;
    }

    @Override
    public Order getOrderById(Long id) {
        return null;
    }

    @Override
    public Order cancelOrder(Long orderId) {
        return null;
    }

    @Override
    public int getPointsOfUser(Long userId) {
        return 0;
    }

    @Override
    public int exchangeToAccumulatedPoints(double subTotal) {
        return 0;
    }

    @Override
    public int exchangeToEqualPoints(double price) {
        return 0;
    }

    @Override
    public ByteArrayInputStream createReport(Order order) {
        return null;
    }
}
