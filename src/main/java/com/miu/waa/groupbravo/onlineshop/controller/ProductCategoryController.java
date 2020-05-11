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

    @PostMapping
    public void saveProductCategory(@Valid @ModelAttribute("productCategory") ProductCategory productCategory, Model model) {

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

    @DeleteMapping
    public void deleteProductCategory(@RequestBody ProductCategory productCategory){
        productCategoryService.deleteProductCategory(productCategory);
    }



}
