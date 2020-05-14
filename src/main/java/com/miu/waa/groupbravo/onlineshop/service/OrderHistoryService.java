package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.OrderHistory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.serviceImpl.OrderHistoryServiceImpl;

import java.util.List;

public interface OrderHistoryService {
    OrderHistory  saveOrderHitory(OrderHistory orderHistory);
    List<OrderHistory> getAllOrderHistoryByUser(Long userId);

}
