package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.service.*;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @PostMapping("/buyer/order/pay/")
    public String makeOrderPayment(HttpServletRequest request){
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        orderService.payOrder(orderService.findById(orderId));
        return "redirect:/buyer/orders";
    }

    @GetMapping("/buyer/orders")
    public String getBuyerOrders(RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("buyerOrders",getBuyerOrdersUtil());
        redirectAttributes.addFlashAttribute("buyerOrdersURL",true);
        return "redirect:/buyer";
    }

    @GetMapping("/buyer/order/cancel/{orderId}")
    public String cancelBuyerOrder(@PathVariable Long orderId, RedirectAttributes redirectAttributes){
        Order orderToBeCancelled = orderService.getOrderById(orderId);
        orderService.cancelOrder(orderToBeCancelled);
        redirectAttributes.addFlashAttribute("buyerOrders",getBuyerOrdersUtil());
        redirectAttributes.addFlashAttribute("buyerOrdersURL",true);
        redirectAttributes.addFlashAttribute("orderCancelled",true);
        return "redirect:/buyer";
    }

    public List<Order> getBuyerOrdersUtil(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userService.findByUsername(auth.getName());
        List<EOrderStatus> statusList = new ArrayList<>();
        statusList.add(EOrderStatus.NEW);
        statusList.add(EOrderStatus.PAID);
        statusList.add(EOrderStatus.ON_THE_WAY);
        statusList.add(EOrderStatus.CANCELLED);
        statusList.add(EOrderStatus.SHIPPED);
        List<Order> buyerOrders = orderService.findOrderByBuyerAndStatus(buyer,statusList);
        return buyerOrders;
    }

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

    @PostMapping("/buyer/saveAddress")
    public String saveAddress(@Valid @ModelAttribute("address") Address address, BindingResult bindingResult, HttpServletRequest httpRequest){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userService.findByUsername(auth.getName());

        //System.out.println("The Address Role is: " + httpRequest.getParameter("addressRole"));

        EAddressRole addressRole1 = (httpRequest.getParameter("addressRole").trim().toLowerCase().equals("billing")) ? EAddressRole.BILLING : EAddressRole.SHIPPING;
        address.setAddressRole(addressRole1);
        buyer.setAddress(address);

        userService.updateUser(buyer);
        return "redirect:/buyer";
    }

    @GetMapping(value = "/seller/sellerStatus/")
    public String checkSellerApproval(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User seller = userService.findByUsername(auth.getName());
        model.addAttribute("products", productService.findProductsBySeller(seller));
        model.addAttribute("productCategories",productCategoryService.findAllProductCategory());
        if(seller.getUserStatus().getStatus().toUpperCase().equals("APPROVED")){
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
