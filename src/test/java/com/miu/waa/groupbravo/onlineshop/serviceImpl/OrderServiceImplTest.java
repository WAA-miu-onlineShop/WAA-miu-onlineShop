package com.miu.waa.groupbravo.onlineshop.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.builder.OrderBuilder;
import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.repository.*;
import com.miu.waa.groupbravo.onlineshop.service.OrderService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import com.miu.waa.groupbravo.onlineshop.service.serviceImpl.OrderServiceImpl;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.matchers.InstanceOf;
import org.mockito.internal.stubbing.BaseStubbing;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceImplTest {

    private OrderService orderService;
    @Mock
    private OrderRepository orderRepository;
        @Mock
    private SequenceNumberService sequenceNumberService;
    @Mock
    private OrderHistoryRepository orderHistoryRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private CouponRepository couponRepository;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private ProductCategoryRepository productCategoryRepository;
    @Mock
    private AddressRepository addressRepository;
    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        orderService=new OrderServiceImpl(addressRepository,productCategoryRepository,productRepository,orderRepository,sequenceNumberService,orderHistoryRepository,userRepository,couponRepository);
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("erurangwa","122333"));
    }
      @Test
    public void testSaveOrder(){
       String orderNumber="OR12020";

        OrderHistory orderHistory=mock(OrderHistory.class);
        Coupon coupon=mock(Coupon.class);
        Product product1=mock(Product.class);
        User buyer=new User();
        Product product=new Product();
        buyer.setFirstName("elias");
        OrderLine orderLine1=new OrderLine();
        ProductCategory productCategory=mock(ProductCategory.class);
          ProductCategory productCategory1=new ProductCategory();
          productCategory1.setName("Laptop");
          product.setProductCategory(productCategory1);
          orderLine1.setProduct(product);
          Order order=new OrderBuilder().withOrderNumber(orderNumber).withOrderLineList(Arrays.asList(orderLine1)).build();

          Address address=mock(Address.class);
        when(sequenceNumberService.getNextOrderNumber()).thenReturn(orderNumber);
        when(orderHistoryRepository.save(orderHistory)).thenReturn(orderHistory);
        when(couponRepository.save(coupon)).thenReturn(coupon);
        when(couponRepository.findByUser(buyer)).thenReturn(null);
        when(userRepository.findByUsername("erurangwa")).thenReturn(buyer);
        when(productRepository.save(product1)).thenReturn(product1);
        when(orderRepository.save(order)).thenReturn(order);
        when(productCategoryRepository.save(productCategory)).thenReturn(productCategory);
        when(addressRepository.findById(Long.valueOf(anyLong()))).thenReturn(java.util.Optional.of(new Address()));

        orderService.saveOrder(order);

    }
}
