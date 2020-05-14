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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Arrays;
import java.util.zip.DeflaterOutputStream;

@Controller
public class OrderController {
   @Autowired
   private OrderService orderService;

    @PostMapping("/buyer/createOrder")
    public String createOrder(Model model, HttpServletRequest request){
        int totalItemsInCart = Integer.parseInt(request.getParameter("totalItems"));
        //${itemName} : ${selectedQty} : ${itemUnitPrice} : ${amount} : ${itemId}
        for(int i=1; i<= totalItemsInCart; i++){
            String [] orderLine = request.getParameter("row-"+i).split(":");
            Long productId = Long.parseLong(orderLine[4].trim());
            String productName = orderLine[0];
            int selectedQuantity = Integer.parseInt(orderLine[1].trim());
            double unitPrice = Double.parseDouble(orderLine[2].trim());
            double computedAmount = Double.parseDouble(orderLine[3].trim());
            //System.out.println(Arrays.toString(orderLine));
        }

        return "redirect:/buyer/";
    }

    @GetMapping("cancelOrder/{orderId}")
    public String cancelOrder(@PathVariable("orderId") Long orderId, Model model){
      Order order=orderService.findById(orderId);
        orderService.cancelOrder(order);
        return "";
    }
    @GetMapping("shippingOrder/{orderId}")
    public String shippingOrder(@PathVariable("orderId") Long orderId, Model model){
        Order order=orderService.findById(orderId);
        orderService.shippingOrder(order);
        return "";
    }
    @GetMapping("delivered/{orderId}")
    public String delivered(@PathVariable("orderId") Long orderId, Model model) {
        Order order=orderService.findById(orderId);
        orderService.delivering(order);
        return "";
    }

}
