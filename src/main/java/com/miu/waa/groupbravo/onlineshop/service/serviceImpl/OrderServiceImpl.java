package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.OrderRepository;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(!optionalOrder.isPresent())
            throw new RuntimeException("Order does not exist");

        Order order = optionalOrder.get();
        order.setOrderStatus(getStatusByName(EOrderStatus.CANCELLED));

//        if(order.getPayment().getPayment() == PaymentMethod.POINT)
//            order.setPoint(0);

        orderRepository.save(order);

        User user = order.getBuyer();

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Dear ").append(user.getFirstName()).append(" ").append(user.getLastName()).append("<br/><br/>");
        stringBuilder.append("Your order #" + orderId.toString() + " on 3L* Store (%s) has been cancelled.<br/><br/>");
        stringBuilder.append("Thank you so much<br/><br/>Waa3L Team.");

       // emailService.sendEmail(stringBuilder.toString(), "Order Canceled", Arrays.asList(user.getEmail()));
        return order;
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
