package com.miu.waa.groupbravo.onlineshop.controller;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @Autowired
    private ProductCategoryService productCategoryService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderHistoryService orderHistoryService;

    @PostMapping("/buyer/order/pay/")
    public String makeOrderPayment(HttpServletRequest request){
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        Double amount = Double.parseDouble(request.getParameter("amount"));
        orderService.payOrder(orderService.findById(orderId));
        return "redirect:/buyer/orders";
    }

    @GetMapping(value = "/buyer/download/receipt/{orderId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public void downloadReceipt(@PathVariable Long orderId,Model model) throws FileNotFoundException, DocumentException {
        Order order = orderService.getOrderById(orderId);
        model.addAttribute("order",order);

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("OrderReceipt.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Chunk chunk = new Chunk("Receipt for your Order\n", font);
        for(OrderLine orderLine: order.getOrderLineList()) {
            String entry = orderLine.getProduct().getName() + " --------------- " + orderLine.getAmount() + "\n";
            chunk.append(entry);
        }
        chunk.append("Total Amount for the receipt is: " + order.getTotalAmount());
        document.add(chunk);
        document.close();
    }

    @GetMapping("/buyer/order/history/{orderId}")
    public String getOrderHistory(@PathVariable Long orderId, RedirectAttributes redirectAttributes){
        List<OrderHistory> orderHistory = orderHistoryService.findOrderHistoryByOrder(orderService.findById(orderId));
        redirectAttributes.addFlashAttribute("orderHistory",orderHistory);
        redirectAttributes.addFlashAttribute("orderHistoryDetailsURL",true);
        return "redirect:/buyer";
    }

    @GetMapping("/buyer/orders")
    public String getBuyerOrders(RedirectAttributes redirectAttributes, Model model){
        redirectAttributes.addFlashAttribute("buyerOrders",getBuyerOrdersUtil());
        redirectAttributes.addFlashAttribute("buyerOrdersURL",true);
        return "redirect:/buyer";
    }

    @GetMapping("/seller/orders")
    public String getSellerOrders(Model model, RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User seller = userService.findByUsername(auth.getName());
        List<EOrderStatus> statusList = new ArrayList<>();
        statusList.add(EOrderStatus.NEW);
        statusList.add(EOrderStatus.PAID);
        statusList.add(EOrderStatus.ON_THE_WAY);
        statusList.add(EOrderStatus.CANCELLED);
        statusList.add(EOrderStatus.SHIPPED);

        List<Order> sellerOrders = orderService.findOrderBySellerAndStatus(seller,statusList);

//        model.addAttribute("sellerOrders",sellerOrders);
//        model.addAttribute("sellerOrdersURL",true);
          redirectAttributes.addFlashAttribute("sellerOrders",sellerOrders);
          redirectAttributes.addFlashAttribute("sellerOrdersURL",true);

        return "redirect:/seller";
    }

    @PostMapping("/buyer/followership/save/")
    public String followUnFollowSeller(Model model,HttpServletRequest request){
        String[] followership = request.getParameterValues("followershipMap");
        for(String entry:followership){
            String entryArr[] = entry.split(",");
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            User buyer = userService.findByUsername(auth.getName());
            User seller = userService.findByUsername(entryArr[1].trim());
            if(entryArr[0].trim().toLowerCase().equals("true")){
                followService.subscribSeller(seller,buyer);
            }else{
                followService.unSubscribSeller(seller,buyer);
            }
        }
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

    @GetMapping("/seller/order/cancel/{orderId}")
    public String sellerCancelBuyerOrder(@PathVariable Long orderId, RedirectAttributes redirectAttributes){
        Order orderToBeCancelled = orderService.getOrderById(orderId);
        orderService.cancelOrder(orderToBeCancelled);
//        redirectAttributes.addFlashAttribute("buyerOrders",getBuyerOrdersUtil());
//        redirectAttributes.addFlashAttribute("buyerOrdersURL",true);
//        redirectAttributes.addFlashAttribute("orderCancelled",true);
        return "redirect:/seller/orders";
    }

    @PostMapping("/seller/order/changeStatus/")
    public String sellerChangeOrderStatus(HttpServletRequest request){
        Long orderId = Long.parseLong(request.getParameter("orderId"));
        String newStatus = request.getParameter("newStatus");
        System.out.println(newStatus);
        System.out.println(orderId);
        Order orderToBeChanged = orderService.getOrderById(orderId);
        if(newStatus.trim().toLowerCase() == "shipped"){
            orderService.shippingOrder(orderToBeChanged);
        }else if(newStatus.trim().toLowerCase() == "delivered"){
            orderService.delivering(orderToBeChanged);
        }
        return "redirect:/seller/orders";
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

    @GetMapping(value = "/seller")
    public String checkSellerApproval(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User seller = userService.findByUsername(auth.getName());
        model.addAttribute("products", productService.findProductsBySeller(seller));
        model.addAttribute("productCategories",productCategoryService.findAllProductCategory());
//        List<Order> sellerOrders = new ArrayList<>();
//        sellerOrders.add(new Order());
//        model.addAttribute("sellerOrders",sellerOrders);
        if(seller.getUserStatus().getStatus().toUpperCase().equals("APPROVED")){
            model.addAttribute("approved",true);
        }else{
            model.addAttribute("approved", false);
        }
        if(model.asMap().get("product")==null) {
            model.addAttribute("product", new Product());
        }
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

}
