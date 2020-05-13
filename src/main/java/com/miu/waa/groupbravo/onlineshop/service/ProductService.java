package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.domain.Product;
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
}
