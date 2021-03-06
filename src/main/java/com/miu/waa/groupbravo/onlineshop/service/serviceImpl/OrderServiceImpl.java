package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.exceptions.BravoException;
import com.miu.waa.groupbravo.onlineshop.repository.*;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    private SequenceNumberService sequenceNumberService;

    private OrderHistoryRepository orderHistoryRepository;

    private UserRepository userRepository;

    private CouponRepository couponRepository;
    private ProductRepository productRepository;
    private ProductCategoryRepository productCategoryRepository;
    private AddressRepository addressRepository;

    public OrderServiceImpl(AddressRepository addressRepository,ProductCategoryRepository productCategoryRepository,ProductRepository productRepository,OrderRepository orderRepository,SequenceNumberService sequenceNumberService,OrderHistoryRepository orderHistoryRepository,UserRepository userRepository,CouponRepository couponRepository){
        this.productRepository=productRepository;
        this.orderRepository=orderRepository;
        this.sequenceNumberService=sequenceNumberService;
        this.orderHistoryRepository=orderHistoryRepository;
        this.userRepository=userRepository;
        this.couponRepository=couponRepository;
        this.productCategoryRepository=productCategoryRepository;
        this.addressRepository=addressRepository;
    }
    @PreAuthorize("hasAuthority('BUYER')")
    @Override
    public Order saveOrder(Order order) {
        Order savedOrder = null;
        if (order.isNew()) {
            String orderNumber = sequenceNumberService.getNextOrderNumber();
            order.setOrderNumber(orderNumber);
            order.setOrderStatus(EOrderStatus.NEW);
            order.setBuyer(getUser());
            BigDecimal couponPoints = getCouponPoint(order);
            order.setCoupons(couponPoints);
            savedOrder = orderRepository.save(order);
            Address shippingAddress=addressRepository.findAddressByUserAndAddressType(order.getBuyer(),EAddressRole.SHIPPING);
            order.setShippingAddress(shippingAddress);
            updateProductAndOrderLine(savedOrder);
            //order.getOrderLineList().stream().reduce()
            createOrderHistory(savedOrder);
            addCouponPoints(savedOrder);
        } else {
            savedOrder = order;
        }
        return savedOrder;
    }
    private void updateProductAndOrderLine(Order order){

        for(OrderLine orderLine: order.getOrderLineList()){
            orderLine.setOrder(order);
            Product product=orderLine.getProduct();
            orderLine.setDescription(product.getDescription());
            product.setProductStatus(EProductStatus.ORDERED);
            ProductCategory productCategory=product.getProductCategory();
            productCategory.setQuantityPurchased(productCategory.getQuantityPurchased().add(orderLine.getQuantity()));
            productCategory.setQuantityAvailable(productCategory.getQuantityAvailable().subtract(orderLine.getQuantity()));

        productRepository.save(product);
            productCategoryRepository.save(productCategory);

        }
    }
    private  Coupon addCouponPoints(Order order){
        Coupon coupon = couponRepository.findByUser(order.getBuyer());
        if (coupon == null) {
            coupon = new Coupon();
            coupon.setBuyer(order.getBuyer());
            String couponNumber = sequenceNumberService.getNextCouponNumber();
            coupon.setCouponNumber(couponNumber);
        }
        coupon.setTotalPoint(coupon.getTotalPoint().add(getCouponPoint(order)));
       return  couponRepository.save(coupon);
    }
    private OrderHistory createOrderHistory(Order order){
        OrderHistory orderHistory = new OrderHistory();
        orderHistory.setDescription("Order History");
        orderHistory.setOrder(order);
        orderHistory.setOrderStatus(order.getOrderStatus());
        orderHistory.setUser(getUser());//Login in user, we have to retrieve this from Principal
        orderHistory.setHistoryDate(LocalDateTime.now());
        return  orderHistoryRepository.save(orderHistory);

    }
    private BigDecimal getCouponPoint(Order order) {
        //the points are the number of ordered items
        return BigDecimal.valueOf(order.getOrderLineList().size());
    }

    private User getUser() {
        User user = null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            user = userRepository.findByUsername(username);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return user;
    }
    @Override
    public List<Order> getAllOrders() {
        return (List<Order>) orderRepository.findAll();

    }

    @Override
    public List<Order> getAllOrdersByUser(Long userId) {
        return orderRepository.getAllOrdersByUser(userId);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).get();
    }

    @Override
    public Order cancelOrder(Order order)  {
        if (order.getOrderStatus().compareTo(EOrderStatus.NEW) != 0) {
            try {
                throw new Exception("You can only cancel an order that is new");
            } catch (Exception e) {
                throw  new BravoException(e);
            }
        }
        order.setOrderStatus(EOrderStatus.CANCELLED);
         makeProductAvailable(order);
        //Create OrderHistory
        createOrderHistory(order);
        //Reduce the coupons amounts
        reduceCouponPoints(order);
        return orderRepository.save(order);
    }
    private void makeProductAvailable(Order order){
        //Avail the product
        List<OrderLine> orderLineList=order.getOrderLineList();
        for(OrderLine orderLine:orderLineList){
            Product product= orderLine.getProduct();
            product.setProductStatus(EProductStatus.AVAILABLE);
            ProductCategory productCategory=product.getProductCategory();
            productCategory.setQuantityAvailable(productCategory.getQuantityAvailable().add(orderLine.getQuantity()));
            product.getProductCategory().setQuantityPurchased(productCategory.getQuantityPurchased().subtract(orderLine.getQuantity()));
            productRepository.save(product);
        }
    }
    private Coupon reduceCouponPoints(Order order){
        Coupon coupon = couponRepository.findByUser(order.getBuyer());
        coupon.setTotalPoint(coupon.getTotalPoint().subtract(order.getCoupons()));
     return couponRepository.save(coupon);
    }

    @Override
    public Order payOrder(Order order){
        if (order.getOrderStatus().compareTo(EOrderStatus.NEW) != 0) {
            try {
                throw new BravoException("You can only pay the total amount for a new order");
            } catch (Exception e) {
                throw  new BravoException(e);
            }
        }
        order.setOrderStatus(EOrderStatus.PAID);
        //Create orderHistory
        createOrderHistory(order);
        return orderRepository.save(order);
    }

    @Override
    public Order shippingOrder(Order order) {

            if (order.getOrderStatus().compareTo(EOrderStatus.PAID) != 0) {

                try {
                throw new BravoException("You can only ship a paid order");

            } catch(Exception e){
                throw new BravoException(e);
            }
        }
        order.setOrderStatus(EOrderStatus.ON_THE_WAY);
        //create orderHistory
        createOrderHistory(order);
        return orderRepository.save(order);
    }

    @Override
    public Order delivering(Order order){

        if (order.getOrderStatus().compareTo(EOrderStatus.PAID) != 0 && order.getOrderStatus().compareTo(EOrderStatus.ON_THE_WAY) != 0) {
            try {
                throw new BravoException("You can only deliver a shipped or paid order");

            } catch (Exception e) {
                throw new BravoException(e);
            }
        }

        order.setOrderStatus(EOrderStatus.DELIVERED);
        //Create orderHistory
        createOrderHistory(order);
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findOrderBySellerAndStatus(User seller, List<EOrderStatus> statusList) {
        return orderRepository.findBySellerAndStatus(seller,statusList);
    }

    @Override
    public List<Order> findOrderByBuyerAndStatus(User buyer, List<EOrderStatus> statusList) {
        return orderRepository.findOrderByBuyerAndStatus(buyer,statusList);

    }
    @Override
    public Order findById(Long orderId) {
        return orderRepository.findById(orderId).get();
    }


}
