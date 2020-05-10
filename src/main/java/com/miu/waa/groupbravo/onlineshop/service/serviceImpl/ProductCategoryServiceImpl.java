package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Override
    public void saveProductCategory(ProductCategory productCategory) {

    }

    @Override
    public void deleteProductCategory(String name) {

    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return null;
    }

    @Override
    public List<ProductCategory> findAllProductCategory() {
        return null;
    }
}
