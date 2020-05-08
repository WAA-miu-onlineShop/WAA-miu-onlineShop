package com.miu.waa.groupbravo.onlineshop.controller;

import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @PostMapping
	public void addProduct(@RequestBody Product product) {

		productService.addProduct(product);
	}

    @PutMapping
	public Product updateProduct(@RequestBody Product product) {
		return productService.updateProduct(product);
	}
    @GetMapping
    public List<Product> findAllProducts() {
        return productService.findAll();
    }
    @GetMapping("/{serialNumber}")
    public Product findOneProduct(@PathVariable String serialNumber) {
        return productService.findOne(serialNumber);
    }

    @DeleteMapping("/{serialNumber}")
    public void deleteProduct(@PathVariable String serialNumber) {
        productService.deleteProduct(serialNumber);
    }



}
