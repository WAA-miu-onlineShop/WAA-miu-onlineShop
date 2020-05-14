package com.miu.waa.groupbravo.onlineshop.config;

import com.miu.waa.groupbravo.onlineshop.domain.ERoleType;
import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;


@Configuration
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
        if (roles.contains(ERoleType.ADMIN.toString())) {
            httpServletResponse.sendRedirect("/admin");
        } else if (roles.contains(ERoleType.BUYER.toString())){
            httpServletResponse.sendRedirect("/buyer");
        }else if (roles.contains(ERoleType.SELLER.toString())){
            //httpServletResponse.sendRedirect("/seller");
            httpServletResponse.sendRedirect("/seller/sellerStatus/");
        }else{
            httpServletResponse.sendRedirect("/login");
        }
    }
}
