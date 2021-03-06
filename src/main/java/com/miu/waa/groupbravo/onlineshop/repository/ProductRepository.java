package com.miu.waa.groupbravo.onlineshop.repository;

import com.miu.waa.groupbravo.onlineshop.constant.IRepositoryConstant;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {


    public static class QUERY{
        public static final String findByBuyer="select p from Product p,OrderLine o where o.product=p and p.productStatus ='ORDERED'  and  o.order.buyer= :buyer ";
        public static final String findByStatus="select p from Product p where p.productStatus in :productStatusList ";
        public static final String findByCategoryAndStatus="select p from Product p where p.productStatus in :productStatusList and p.productCategory= :productCategory ";
    }
    public static class QUERY_NAME{
        public static final String findByBuyer="Product.findByBuyer";
        public static final String findByStatus="Product.findByStatus";
        public static final String findByCategoryAndStatus="Product.findByCategoryAndStatus";
    }
    List<Product> findByBuyer(@Param(IRepositoryConstant.BUYER)User buyer);
    List<Product> findBySeller(User seller);
    List<Product> findByStatus(@Param(IRepositoryConstant.PRODUCT_STATUS_LIST)List<EProductStatus> productStatusList);
    List<Product> findByCategoryAndStatus(@Param(IRepositoryConstant.PRODUCT_CATEGORY) ProductCategory productCategory, @Param(IRepositoryConstant.PRODUCT_STATUS_LIST) List<EProductStatus> productStatusList);
    Product findBySerialNumber(String serialNumber);
}
