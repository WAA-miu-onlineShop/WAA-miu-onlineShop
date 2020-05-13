package com.miu.waa.groupbravo.onlineshop.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ESequenceType;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import com.miu.waa.groupbravo.onlineshop.service.serviceImpl.ProductServiceImpl;
import org.junit.Before;
import org.junit.Test;

import org.mockito.Mock;

import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;


import java.math.BigDecimal;

import static org.mockito.Mockito.when;


public class ProductServiceImplTest {

     ProductService productService;
 @Mock
 private SequenceNumberService sequenceNumberService;
 @Mock
 private ProductRepository productRepository;
 @Mock
 private ProductCategoryRepository productCategoryRepository;
 @Mock
 private UserRepository userRepository;
    @Before
    public void setUp() throws Exception {
     MockitoAnnotations.initMocks(this);
     productService=new ProductServiceImpl(sequenceNumberService,productRepository,productCategoryRepository,userRepository);
     SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("erurangwa","122333"));

    }
     @Test
    public void testCreateProduct(){
      String productNumber="PR12020";
      ProductCategory productCategory=new ProductCategory();
      productCategory.setName("Test");
      productCategory.setId(Long.valueOf(1));
      User seller=new User();
      seller.setFirstName("Eric");
      Product product1=new Product();
      product1.setName("Laptop");
      product1.setDescription("dell laptopn 2345");
      product1.setSerialNumber("S34567");
      product1.setUnitPrice(new BigDecimal(12));
      product1.setProductCategory(productCategory);

      when(sequenceNumberService.getNextProductNumber()).thenReturn(productNumber);
      when(productCategoryRepository.findById(product1.getProductCategory().getId())).thenReturn(java.util.Optional.of(productCategory));
      when(userRepository.findByUsername("erurangwa")).thenReturn(seller);

        productService.addProduct(product1);


    }
}
