package com.miu.waa.groupbravo.onlineshop.service;

import edu.mum.cs.bravowaaproject.model.User;
import edu.mum.cs.bravowaaproject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        if(user.getRoles().stream().findFirst().get().equals("SELLER")){
            //user.setUserStatus(EUserStatus.NEW);
            user.setActive(0);
        }else{
            user.setActive(1);
        }
        return userRepository.save(user);
    }

}
