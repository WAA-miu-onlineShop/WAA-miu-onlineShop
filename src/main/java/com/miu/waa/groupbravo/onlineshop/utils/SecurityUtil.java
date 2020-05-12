package com.miu.waa.groupbravo.onlineshop.utils;

import com.miu.waa.groupbravo.onlineshop.OnlineshopApplication;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.security.Principal;


public class SecurityUtil {
    @Autowired
    private UserRepository userRepository;

    public static User getUser(){
       // SpringContextUtils

       // SpringContextUtils.getApplicationContext().getBean("NNNN");
        Object principal= SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }
       // User user=userRepository.findByUsername(principal.getName());
        return null;
    }
}
