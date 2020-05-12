package com.miu.waa.groupbravo.onlineshop.controller;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/productCategories")
public class ProductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @PostMapping("/saveCategory")
    public void saveProductCategory(@Valid @ModelAttribute("productCategory") ProductCategory productCategory) {

        productCategoryService.saveProductCategory(productCategory);
    }

    @PutMapping("/updateProductCategory")
    public ProductCategory updateProductCategory(ProductCategory productCategory) {
        return productCategoryService.updateProductCategory(productCategory);
    }
    @GetMapping("/findAll")
    public List<ProductCategory> findAllProductCategories() {

        return productCategoryService.findAllProductCategory();
    }

    @DeleteMapping("/deleteCategory")
    public void deleteProductCategory(ProductCategory productCategory){

        productCategoryService.deleteProductCategory(productCategory);
    }



}
