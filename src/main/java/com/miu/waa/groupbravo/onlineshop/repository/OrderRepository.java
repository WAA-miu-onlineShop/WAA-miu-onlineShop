
package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.EOrderStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, Long> {
    public static class QUERY{
        public static final String findOrderByBuyerAndStatus="select o from Orders o where o.buyer = :buyer and o.orderStatus in :statusList ";
        public static final String findBySellerAndStatus="select o from Orders o where o in (select b.order from OrderLine b,Product p where b.product =p and p.seller = :seller ) and o.orderStatus in :statusList ";
    }
    public static class QUERY_NAME{
        public static final String findOrderByBuyerAndStatus="Order.findOrderByBuyerAndStatus";
        public static final String findBySellerAndStatus="Order.findBySellerAndStatus";

    }
    @Query("select o from Orders o where o.buyer.id = :userId")
    List<Order> getAllOrdersByUser(Long userId);

   List<Order> findOrderByBuyerAndStatus(@Param("buyer")User buyer,@Param("statusList")List<EOrderStatus> statusList);//@Param("statusList")List<EOrderStatus> statusList

    List<Order> findBySellerAndStatus(@Param("buyer")User seller,@Param("statusList")List<EOrderStatus> statusList);
}

