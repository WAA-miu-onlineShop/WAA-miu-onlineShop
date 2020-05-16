package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Coupon;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.CouponRepository;
import com.miu.waa.groupbravo.onlineshop.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class CouponServiceImpl implements CouponService {
    @Autowired
    private CouponRepository couponRepository;
    @Override
    public Coupon findCouponByUser(User buyer) {
        return couponRepository.findByUser(buyer);
    }
}
