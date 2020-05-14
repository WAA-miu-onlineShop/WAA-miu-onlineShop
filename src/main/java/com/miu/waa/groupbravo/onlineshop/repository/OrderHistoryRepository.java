
package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.OrderHistory;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory, Long> {
    List<OrderHistory> findByOrder(Order order);
}

