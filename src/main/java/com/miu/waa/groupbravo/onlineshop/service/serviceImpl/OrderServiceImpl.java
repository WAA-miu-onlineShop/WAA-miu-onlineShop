package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.OrderHistory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.OrderHistoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.OrderRepository;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.ByteArrayInputStream;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SequenceNumberService sequenceNumberService;
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;

    @Override
    public Order save(Order order) {
        if(order.isNew()) {
            String orderNumber = sequenceNumberService.getNextOrderNumber();
            order.setOrderNumber(orderNumber);
            order.setOrderStatus(EOrderStatus.NEW);
            OrderHistory orderHistory = new OrderHistory();
            orderHistory.setDescription("Order History");
            orderHistory.setOrderStatus(order.getOrderStatus());
            orderHistory.setUser(order.getBuyer());//Login in user, we have to retrieve this from Principal
            orderHistory.setHistoryDate(LocalDateTime.now());
            Order savedOrder=save(order);
            //Add coupons
            //create coupon object, we search first if the coupon for that buyer already exist, if true increment the totalPoint, if not create a new coupon for that buyer
            orderHistory.setOrder(savedOrder);
            orderHistoryRepository.save(orderHistory);
        }
        return orderRepository.save(order);
    }

    @Override
    public EOrderStatus getStatusByName(EOrderStatus status) {
        return orderRepository.getOrderStatus();
    }
   //orderByUser(Seller)AndStatus
    //AllOrderByStatus(seller)
    //AllOrderByBuyer
    //AllOrdersByBuyerAndStatus
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

        orderRepository.save(order);

        User user = order.getBuyer();


        return order;
    }

    @Override
    public int getPointsOfUser(Long userId) {
        int points = 0;
        List<Order> orders = this.getAllOrdersByUser(userId);

        for(Order o: orders)
            points += o.getCoupons().intValue();

        return points;
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
