package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.EUserStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
   @Autowired
   private OrderService orderService;
    @PostMapping("/createOrder")
    public String createOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model){

      if(bindingResult.hasErrors()){
     return "buyer/";
      }
       orderService.saveOrder(order);
        return "redirect:/buyer/";
    }

    @GetMapping("cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long orderId, Model model) throws Exception {
      Order order=orderService.findById(orderId);
        orderService.cancelOrder(order);
        return "";
    }
    @GetMapping("shippingOrder/{orderId}")
    public String shippingOrder(@PathVariable("orderId") Long orderId, Model model) throws Exception {
        Order order=orderService.findById(orderId);
        orderService.shippingOrder(order);
        return "";
    }
    @GetMapping("delivered/{orderId}")
    public String delivered(@PathVariable("orderId") Long orderId, Model model) throws Exception {
        Order order=orderService.findById(orderId);
        orderService.delivering(order);
        return "";
    }

}
