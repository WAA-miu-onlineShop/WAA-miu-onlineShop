package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Following;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.FollowingRepository;
import com.miu.waa.groupbravo.onlineshop.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;

public class FollowServiceImpl  implements FollowService {
    @Autowired
    private FollowingRepository followingRepository;

    @Override
    public Following subscribSeller(User seller, User buyer) {
        Following following=followingRepository.getFollowingBySellerAndBuyer(seller,buyer);

        if(following.isNew()){
            Following newFollowing=new Following();
            newFollowing.setBuyer(buyer);
            newFollowing.setSeller(seller);
            newFollowing.setDate(LocalDate.now());
            newFollowing.setFollow(true);
            return followingRepository.save(newFollowing);
        }else{
            return following;
        }

    }

    @Override
    public Following unSubscribSeller(User seller, User buyer) {
        return null;
    }

    @Override
    public boolean isFollow(User seller, User buyer) {
        return false;
    }
}
