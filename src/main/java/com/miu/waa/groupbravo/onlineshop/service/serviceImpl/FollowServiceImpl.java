package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Following;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.FollowingRepository;
import com.miu.waa.groupbravo.onlineshop.service.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@Transactional
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
        Following following=followingRepository.getFollowingBySellerAndBuyer(seller,buyer);

        if(following !=null){

            following.setFollow(false);
            following.setDate(LocalDate.now());
            return followingRepository.save(following);
        }else{
            Following newFollowing=new Following();
            newFollowing.setBuyer(buyer);
            newFollowing.setSeller(seller);
            newFollowing.setDate(LocalDate.now());
            newFollowing.setFollow(false);
            return followingRepository.save(newFollowing);
        }

    }

    @Override
    public boolean isFollow(User seller, User buyer) {
        return  followingRepository.getFollowingBySellerAndBuyer(seller,buyer).getFollow();
    }
}
