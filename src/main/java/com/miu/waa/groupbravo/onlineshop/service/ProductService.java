package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    public void addProduct(Product product);
    public void deleteProduct(Product product);
    public Product updateProduct(Product product);
    public List<Product> findAll();
    public List<Product> findProductsBySeller(User seller);
    public void deleteProductById(Long productId);
    public Optional<Product> findProductById(Long productId);
    public List<Product> findProductByStatus(List<EProductStatus> productStatusList);
    public List<Product> findProductByCategoryAndStatus(ProductCategory productCategory,List<EProductStatus> productStatusList);
    Product getProductBySerialNumber(String serialNumber);
}
