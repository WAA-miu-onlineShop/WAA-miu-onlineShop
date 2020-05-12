package com.miu.waa.groupbravo.onlineshop.controller;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    ProductService productService;
    @PostMapping("/save")
	public String  addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult,Model model) {
        if(bindingResult.hasErrors()){

            return "product/mainSeller";

        }
		 productService.addProduct(product);
        return "redirect:list";

	}

    @PutMapping("/update")
	public String updateProduct( Product product) {
        productService.updateProduct(product);
        return "redirect:list";
	}
    @GetMapping("/list")
    public String findAllProducts() {
         productService.findAll();
         return "product/mainSeller_List";
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@Valid @ModelAttribute("product") Product product) {
    productService.deleteProduct(product);
    return "product/mainSeller_List";
    }

}
