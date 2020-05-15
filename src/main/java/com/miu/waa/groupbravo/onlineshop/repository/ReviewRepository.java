package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.domain.Review;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository  extends CrudRepository<Review, Long> {

    @Query("select a from Review a where a.product.seller= :seller ")
    List<Review> findBySeller(User seller);
    List<Review> findByBuyer(User buyer);
}
