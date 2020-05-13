package com.miu.waa.groupbravo.onlineshop.utils;

import com.miu.waa.groupbravo.onlineshop.OnlineshopApplication;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.thymeleaf.spring5.context.SpringContextUtils;

import java.security.Principal;


public class SecurityUtil {

    public static User getUser(){
        User user=null;
        try {
       /*     SpringApplicationBuilder applicationBuilder =SpringContextUtils.getApplicationContext(User);
            SpringApplicationBuilder edew=SpringContextUtils.getApplicationContext().
            ApplicationContext context=applicationBuilder.context();
            UserRepository userRepository = (UserRepository) context.getBean(UserRepository.class);*/
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
         //  user= userRepository.findByUsername(username);
        }catch(Exception ex){
            //An exception occurs
            System.out.println("exception :"+ex.getMessage());
        }
        return user;
    }
}
