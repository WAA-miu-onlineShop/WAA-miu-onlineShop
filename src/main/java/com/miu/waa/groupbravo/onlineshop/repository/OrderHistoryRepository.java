
package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.OrderHistory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderHistoryRepository extends CrudRepository<OrderHistory,Long> {
}

