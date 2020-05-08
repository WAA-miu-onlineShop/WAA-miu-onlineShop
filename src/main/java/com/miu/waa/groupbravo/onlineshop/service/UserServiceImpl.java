package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getUserRole().getName().equals("SELLER")){
            user.setUserStatus(EUserStatus.NEW);
            //user.setActive(0);
        }else{
            user.setUserStatus(EUserStatus.APPROVED);
            //user.setActive(1);
        }
        return userRepository.save(user);
    }

}
