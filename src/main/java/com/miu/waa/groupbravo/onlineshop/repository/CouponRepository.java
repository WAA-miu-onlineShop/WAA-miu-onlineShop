package com.miu.waa.groupbravo.onlineshop.repository;


import com.miu.waa.groupbravo.onlineshop.domain.Coupon;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CouponRepository extends CrudRepository<Coupon, Long> {

    public static class QUERY{
       public static final String findByUser="select c from Coupon c where c.buyer= :buyer";
    }
    public static class QUERY_NAME{
        public static final String findByUser="Coupon.findByUser";
    }
    Coupon findByUser(@Param("buyer")User buyer);
}
