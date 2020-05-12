package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.ERoleType;
import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @GetMapping(value = "/seller/sellerStatus/{username}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Boolean checkSellerStatus(@PathVariable String username){
        //System.out.println(username.trim());
        User seller = userService.findByUsername(username.trim());
        Boolean outPut = false;
        //System.out.println(seller.getUserStatus().getStatus());
        if(seller.getUserStatus().getStatus().toUpperCase().equals("APPROVED")){
            outPut = true;
        }
        return outPut;
    }

    @GetMapping(value = "/seller/sellerStatus/")
    public String checkSellerApproval(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User seller = userService.findByUsername(auth.getName());
        //get list of product categories
        model.addAttribute("productCategories",productCategoryService.findAllProductCategory());
        if(seller.getUserStatus().getStatus().toUpperCase().equals("APPROVED")){
            //fetch seller products from the database
            model.addAttribute("approved",true);
        }else{
            model.addAttribute("approved", false);
        }
        model.addAttribute("product", new Product());
        return "mainSeller";
    }

    @GetMapping("/admin/manageSeller")
    public String approveSeller(Model model){
        model.addAttribute("sellers", getListOfSellers());
        return "mainAdmin";
    }

    @GetMapping("/admin/changeSellerStatus/{username}")
    public String changeSellerStatus(@PathVariable String username, Model model){
        User seller = userService.findByUsername(username);
        if(seller.getUserStatus().compareTo(EUserStatus.NEW)==0){
            seller.setUserStatus(EUserStatus.APPROVED);
        }else if(seller.getUserStatus().compareTo(EUserStatus.APPROVED)==0){
            seller.setUserStatus(EUserStatus.DISABLED);
        }else{
            seller.setUserStatus(EUserStatus.APPROVED);
        }
        //System.out.println(seller.getUserStatus().getStatus());
        userService.approveSeller(seller);

        return "forward:/admin/manageSeller";
    }

    public List<User> getListOfSellers(){
        return getListOfUsers().stream()
                                .filter(user -> user.getUserRole().getName().toUpperCase().equals("SELLER"))
                                .collect(Collectors.toList());
    }

    public List<User> getListOfUsers(){
        return userService.findAll();
    }



    @GetMapping("/user/index")
    public String userIndex() {
        return "user/index";
    }

    @GetMapping("/admin/index")
    public String adminIndex(Model model) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
        model.addAttribute("adminMessage","Content Available Only for Users with Admin Role");
        return "user/admin";
    }

    @GetMapping("/dba/index")
    public String dbaIndex(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUsername(auth.getName());
        model.addAttribute("userName", "Welcome " + user.getFirstName() + " " + user.getLastName() + " (" + user.getUsername() + ")");
        model.addAttribute("adminMessage","Content Available Only for Users with DBA Role");
        return "user/dba";
    }
}
