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
import java.math.BigDecimal;
import java.util.List;

@Controller
@RequestMapping("/products")
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
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model, HttpRequest request)throws FileNotFoundException {
        if (bindingResult.hasErrors()) {

            return "product/mainSeller";
        }

        //uploading a picture
        MultipartFile photo = product.getMultipartFile();
        String rootDirectory = servletContext.getRealPath("/");

        if (photo!=null && !photo.isEmpty()) {
            try {
                photo.transferTo(new File(rootDirectory+"\\images\\"+ product.getProductNumber()+ ".jpg"));
            } catch (Exception e) {
                throw new FileNotFoundException("Can't upload the image: " + photo.getOriginalFilename() );
            }
        }
        productService.addProduct(product);

        return "redirect:list";
    }

    @PutMapping("/update")
    public String updateProduct(Product product) {
        productService.updateProduct(product);
        return "redirect:list";
    }

    @GetMapping("/list")
    public String findAllProducts() {
        productService.findAll();
        return "product/mainSeller_List";
    }

    @GetMapping("/byseller")
    public String findProductsBySeller(String username) {
        User seller = userService.findByUsername(username);
        productService.findProductsBySeller(seller);
        return "product/mainSeller_List";
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
            return "product/mainSeller_List";
        }
        else {
            productService.deleteProduct(product);
        }
        return "product/mainSeller_List";
    }




}
