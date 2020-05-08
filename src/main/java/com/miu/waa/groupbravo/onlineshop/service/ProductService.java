package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Product;

import java.util.List;

public interface ProductService {
    public void addProduct(Product product);
    public void deleteProduct(String serialNumber);
    public Product updateProduct(Product product);
    public List<Product> findAll();
    public Product findOne(String id);

}
