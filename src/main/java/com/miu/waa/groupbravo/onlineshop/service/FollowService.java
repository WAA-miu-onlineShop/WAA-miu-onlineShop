package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Following;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FollowService {
    public Following subscribSeller(User seller,User buyer);
    public Following unSubscribSeller(User seller,User buyer);
    public boolean isFollow(User seller,User buyer);
    List<Following> findFollowingsBySeller(User seller);
    List<Following> findFollowingsByBuyer(User buyer);

}
