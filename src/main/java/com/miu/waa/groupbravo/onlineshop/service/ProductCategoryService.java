package com.miu.waa.groupbravo.onlineshop.service;

import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;

import java.util.List;

public interface ProductCategoryService {
    public void saveProductCategory(ProductCategory productCategory);
    public void deleteProductCategory(ProductCategory productCategory);
    public ProductCategory updateProductCategory(ProductCategory productCategory);
    public List<ProductCategory> findAllProductCategory();
    public List<ProductCategory> findProductCategoriesBySeller(User seller);
    public ProductCategory findById(Long productCategoryId);
}
