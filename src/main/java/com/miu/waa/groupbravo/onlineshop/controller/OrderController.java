package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.Order;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/order")
public class OrderController {
//    @Autowired
//    private OrderService orderService;
//    @PostMapping("/createOrder")
//    public String createOrder(@Valid @ModelAttribute("order") Order order, BindingResult bindingResult, Model model){
//
//      if(bindingResult.hasErrors()){
//     return "buyer/";
//      }
//       orderService.saveOrder(order);
//        return "redirect:/buyer/";
//    }
}
