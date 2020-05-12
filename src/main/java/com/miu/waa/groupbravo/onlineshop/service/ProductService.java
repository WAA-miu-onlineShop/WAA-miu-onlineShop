package com.miu.waa.groupbravo.onlineshop.service;


import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import java.util.List;

public interface ProductService {
    public void addProduct(Product product);
    public void deleteProduct(Product product);
    public Product updateProduct(Product product);
    public List<Product> findAll();
    public List<Product> findProductsBySeller(User seller);
}
