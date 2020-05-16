package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Coupon;
import com.miu.waa.groupbravo.onlineshop.domain.User;

public interface CouponService {
    public Coupon findCouponByUser(User buyer);
}
