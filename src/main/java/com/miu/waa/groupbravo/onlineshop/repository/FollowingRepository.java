package com.miu.waa.groupbravo.onlineshop.repository;
import com.miu.waa.groupbravo.onlineshop.domain.Following;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowingRepository extends CrudRepository<Following, Long> {
    public Following getBySellerAndBuyer(User seller, User Buyer);
    List<Following> findBySeller(User seller);
    List<Following> findByBuyer(User buyer);
}
