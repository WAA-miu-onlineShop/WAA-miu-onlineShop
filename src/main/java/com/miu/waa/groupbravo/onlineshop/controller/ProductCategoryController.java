package com.miu.waa.groupbravo.onlineshop.controller;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productCategories")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping
    public void saveProductCategory(@RequestBody ProductCategory productCategory) {

        productCategoryService.saveProductCategory(productCategory);
    }

    @PutMapping
    public ProductCategory updateProductCategory(@RequestBody ProductCategory productCategory) {
        return productCategoryService.updateProductCategory(productCategory);
    }
    @GetMapping
    public List<ProductCategory> findAllProductCategories() {

        return productCategoryService.findAllProductCategory();
    }

    @DeleteMapping("/{name}")
    public void deleteProductCategory(@PathVariable String name) {
        productCategoryService.deleteProductCategory(name);
    }



}
