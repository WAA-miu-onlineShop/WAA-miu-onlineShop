package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryRepository productCategoryRepository;
    @Override
    public void saveProductCategory(ProductCategory productCategory) {

    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        productCategoryRepository.delete(productCategory);
    }

    @Override
    public ProductCategory updateProductCategory(ProductCategory productCategory) {

        return productCategoryRepository.save(productCategory);
    }

    @Override
    public List<ProductCategory> findAllProductCategory() {

        return (List<ProductCategory>)productCategoryRepository.findAll();
    }
}
