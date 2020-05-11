package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.repository.OrderRepository;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public EOrderStatus getStatusByName(EOrderStatus status) {
        return orderRepository.getOrderStatus();
    }

    @Override
    public List<Order> getAllOrders() {
        return (List<Order>)orderRepository.findAll();

    }

    @Override
    public List<Order> getAllOrdersByUser(Long userId) {
        return orderRepository.getAllOrdersByUser(userId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
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
