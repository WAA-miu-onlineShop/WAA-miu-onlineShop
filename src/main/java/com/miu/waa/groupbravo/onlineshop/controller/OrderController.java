package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.zip.DeflaterOutputStream;

@Controller
public class OrderController {
   @Autowired
   private OrderService orderService;
   @Autowired
   private ProductService productService;

    @PostMapping("/buyer/createOrder")
    public String createOrder(Model model, HttpServletRequest request, RedirectAttributes redirectAttributes){
        int totalItemsInCart = Integer.parseInt(request.getParameter("totalItems"));
        //${itemName} : ${selectedQty} : ${itemUnitPrice} : ${amount} : ${itemId}
         Order order=new Order();
         List<OrderLine> orderLineList=new ArrayList<>();
        for(int i=1; i<= totalItemsInCart; i++){
            String [] orderLine= request.getParameter("row-"+i).split(":");
            Long productId = Long.parseLong(orderLine[4].trim());
            String productName = orderLine[0];
            int selectedQuantity = Integer.parseInt(orderLine[1].trim());
            double unitPrice = Double.parseDouble(orderLine[2].trim());
            double computedAmount = Double.parseDouble(orderLine[3].trim());
            //System.out.println(Arrays.toString(orderLine));
           OrderLine orderLineObject= getOrderLine(productId,selectedQuantity);
           orderLineList.add(orderLineObject);
        }
         order.setOrderLineList(orderLineList);
         order.setTotalAmount(order.getTotalAmount());
         orderService.saveOrder(order);
         redirectAttributes.addFlashAttribute("orderSuccess",true);
         return "redirect:/buyer";
    }

    private OrderLine getOrderLine(Long productId,int selectedQuantity ){
        OrderLine orderLine=new OrderLine();
        Product product=productService.findProductById(productId).get();
        orderLine.setProduct(product);
        orderLine.setQuantity(BigDecimal.valueOf(selectedQuantity));
        orderLine.setAmount(orderLine.getQuantity().multiply(product.getUnitPrice()));
        return  orderLine;
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
