package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.OrderHistory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.OrderHistoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.OrderRepository;
import com.miu.waa.groupbravo.onlineshop.service.OrderHistoryService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OrderHistoryServiceImpl implements OrderHistoryService {
    @Autowired
    private OrderHistoryRepository orderHistoryRepository;
    @Override
    public OrderHistory saveOrderHitory(OrderHistory orderHistory) {
        return orderHistoryRepository.save(orderHistory);
    }

    @Override
    public List<OrderHistory> getAllOrderHistoryByUser(Long userId) {
        return null;
    }
    //HistoryByOrder
}
