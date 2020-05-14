package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.Address;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @RequestMapping("/")
    public String root(Principal principal, HttpServletRequest httpRequest, SecurityContextHolder auth) {
        if(httpRequest.isUserInRole("ROLE_BUYER")){
            return "redirect:/buyer";
        }else if(httpRequest.isUserInRole("ROLE_SELLER")){
            return "redirect:/seller";
        }else if(httpRequest.isUserInRole("ROLE_ADMIN")){
            return "redirect:/admin";
        }else{
            System.out.println();
            return "redirect:/login-error";
        }
    }

    @RequestMapping("/admin")
    public String mainAdminPage(Model model) {
        List<User> sellers = new ArrayList<>();
        model.addAttribute("sellers",sellers);
        return "mainAdmin";
    }

    @RequestMapping("/buyer")
    public String mainBuyerPage(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        Long userId = user.getId();
        model.addAttribute("address",new Address());
        model.addAttribute("addresses", user.getAddresses());
        model.addAttribute("products", productController.getAvailableProduct());
        model.addAttribute("userDetails",user);
        //model.addAttribute()
        return "mainBuyer";
    }

    @RequestMapping("/seller")
    public String mainSellerPage(Model model) {
        //model.addAttribute("product", new Product());
        return "mainSeller";
    }

    @RequestMapping("/login")
    public String login() {

        return "loginForm";
        //return "login";
    }

    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "loginForm";
    }

    @PostMapping("/logout")
    public String logout(HttpSession httpSession, SessionStatus status){
        httpSession.invalidate();
        status.setComplete();
        return "redirect:/login";
    }


}
