
package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.Order;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {

    @Query("select o from Orders o where o.buyer.id = :userId")
    List<Order> getAllOrdersByUser(Long userId);

    @Query("select o.orderStatus from Orders o")
    String getOrderStatus();
}

