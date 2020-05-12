package com.miu.waa.groupbravo.onlineshop.controller;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.ServletContext;
import javax.validation.Valid;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    UserService userService;
    @Autowired
    ServletContext servletContext;

    @PostMapping("/save")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model)throws FileNotFoundException {
        if (bindingResult.hasErrors()) {

            return ("seller/product");
        }
        productService.addProduct(product);

        return ("seller/product");
    }

    @PutMapping("/update")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return ("seller/product");
    }

    @GetMapping("/list")
    public String findAllProducts() {
        productService.findAll();
        return ("seller/product");
    }

    @GetMapping("/byseller")
    public String findProductsBySeller(String username) {
        User seller = userService.findByUsername(username);
        productService.findProductsBySeller(seller);
        return ("seller/product");
    }

        @ModelAttribute("productCategoryList")
        public List<ProductCategory> getProductCategoryBySeller(String username){
            User seller = userService.findByUsername(username);

            return  productCategoryService.findProductCategoriesBySeller(seller);
        }


//    @GetMapping("/byseller")
//    public String findProductsBySeller(User seller) {
//        productService.findProductsBySeller(seller);
//        return "product/mainSeller_List";
//    }

    @DeleteMapping("/delete")
    public String deleteProduct( Product product,Model model) {
        if(product.getProductStatus().compareTo(EProductStatus.NEW)!=0||product.getProductStatus().compareTo(EProductStatus.AVAILABLE)!=0){
            model.addAttribute("errorMessage","you can not delete a purchased product");
            return ("seller/product");
        }
          else {
            productService.deleteProduct(product);
        }
        return ("seller/product");
    }




}
