package com.miu.waa.groupbravo.onlineshop.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ProductServiceImplTest {
   @Autowired
    ProductService productService;
    @Before
    public void setUp() throws Exception {
    }
     @Test
    public void testCreateProduct(){

/*

       User seller=new User();
        seller.setFirstName("Eric");

        ProductCategory productCategory=new ProductCategory();
        productCategory.setName("Test");
        Product product1=new Product();
        product1.setName("Laptop");
        product1.setDescription("dell laptopn 2345");
        product1.setSerialNumber("S34567");
        product1.setUnitPrice(new BigDecimal(12));
        product1.setSeller(seller);
        product1.setProductCategory(productCategory);
        productService.addProduct(product1);
*/

    }
}
