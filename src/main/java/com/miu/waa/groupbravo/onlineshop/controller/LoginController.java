package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.service.FollowService;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Autowired
    private ProductController productController;

    @Autowired
    private UserController userController;

    @Autowired
    private FollowService followService;

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
    public String mainBuyerPage(Model model, RedirectAttributes redirectAttributes) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userService.findByUsername(auth.getName());
        Long userId = buyer.getId();
        model.addAttribute("address",new Address());
        model.addAttribute("addresses", buyer.getAddresses());
        model.addAttribute("products", productController.getAvailableProduct());
        model.addAttribute("userDetails",buyer);
        List<Order> orders = userController.getBuyerOrdersUtil();
        model.addAttribute("buyerOrders",orders);

        List<User> sellers = userService.findAll()
                            .stream()
                            .filter(seller -> seller.getUserRole().getRoleType().compareTo(ERoleType.SELLER)==0)
                            .collect(Collectors.toList());
        //model.addAttribute("")

        Map<User,Boolean> followingMap = new HashMap<>();

        if(sellers.size()>0) {
            for (User seller : sellers) {
                //System.out.println(followService.isFollow(seller, buyer));
                if (followService.isFollow(seller, buyer)) {
                    followingMap.put(seller,true);
                }else{
                    followingMap.put(seller,false);
                }
            }
        }

        model.addAttribute("followingMap",followingMap);

        return "mainBuyer";
    }

    @RequestMapping("/seller/sellerStatus")
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
