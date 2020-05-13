package com.miu.waa.groupbravo.onlineshop.repository;

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
        public static final String findByStatus="select p from Product whare p.productStatus in :productStatusList ";
        public static final String findByCategoryAndStatus="select p from Product whare p.productStatus in :productStatusList and p.productCategory= :productCategory ";

    }
    public static class QUERY_NAME{
        public static final String findByStatus="Product.findByStatus";
        public static final String findByCategoryAndStatus="Product.findByCategoryAndStatus";
    }
    List<Product> findBySeller(User seller);

    List<Product> findByStatus(@Param("productStatusList")List<EProductStatus> productStatusList);

    List<Product> findByCategoryAndStatus(@Param("productCategory") ProductCategory productCategory, @Param("productStatusList") List<EProductStatus> productStatusList);
}
