package com.miu.waa.groupbravo.onlineshop.utils;

import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.security.Principal;

@Component
public class SecurityUtil {
    @Autowired
    private UserRepository userRepository;

    public static User getUser(){
        Principal principal=(Principal) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       // User user=userRepository.findByUsername(principal.getName());
        return null;
    }
}
