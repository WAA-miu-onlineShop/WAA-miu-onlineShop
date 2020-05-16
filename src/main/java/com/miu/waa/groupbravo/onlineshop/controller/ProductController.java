package com.miu.waa.groupbravo.onlineshop.controller;
import com.miu.waa.groupbravo.onlineshop.domain.*;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.ReviewService;
import com.miu.waa.groupbravo.onlineshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    UserController userController;

    @Autowired
    private ReviewService reviewService;

    @PostMapping("/seller/product")
    public String addProduct(@Valid @ModelAttribute("product") Product product, BindingResult bindingResult, Model model)throws FileNotFoundException {
        if (bindingResult.hasErrors()) {

            return userController.checkSellerApproval(model);
        }
        productService.addProduct(product);

        return userController.checkSellerApproval(model);
    }

    @PostMapping("/seller/product/update")
    public String updateProduct(@Valid @ModelAttribute("productDetails") Product product, BindingResult bindingResult, Model model) {
        if(bindingResult.hasErrors()){
            return userController.checkSellerApproval(model);
        }
        productService.updateProduct(product);
        return userController.checkSellerApproval(model);
    }

    @GetMapping("/list")
    public String findAllProducts() {
        productService.findAll();
        return ("seller/product");
    }
    //@GetMapping("/availableProduct")
    public List<Product> getAvailableProduct(){
        List<EProductStatus> available=new ArrayList<>();
        available.add(EProductStatus.AVAILABLE);
        List<Product> availableProducts=productService.findProductByStatus(available);
        return availableProducts;
    }
    @GetMapping("/availableProduct/{productCategoryId}")
    public String getAvailableProductByCategory(@PathVariable("productCategoryId") Long productCategoryId, Model model){

        ProductCategory productCategory= productCategoryService.findById(productCategoryId);
        List<EProductStatus> available=new ArrayList<>();
        available.add(EProductStatus.AVAILABLE);
        List<Product> availableProducts=productService.findProductByCategoryAndStatus(productCategory,available);
        model.addAttribute("availableProducts",availableProducts);
        return "buyer/ ";
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

    @DeleteMapping("/seller/product/delete")
    public String deleteProduct(Product product,Model model) {
        if(product.getProductStatus().compareTo(EProductStatus.NEW)!=0||product.getProductStatus().compareTo(EProductStatus.AVAILABLE)!=0){
            model.addAttribute("errorMessage","you can not delete a purchased product");
            return ("seller/product");
        }
          else {
            productService.deleteProduct(product);
        }
        return ("seller/product");
    }
//
//    @DeleteMapping("/seller/product/delete/{product_id}")
//    public String deleteProduduct(Long id,Model model){
//    Product product=productService.findById(id);
//        if(product.getProductStatus().compareTo(EProductStatus.NEW)!=0||product.getProductStatus().compareTo(EProductStatus.AVAILABLE)!=0){
//            model.addAttribute("errorMessage","you can not delete a purchased product");
//            return ("seller/product");
//        }
//        else {
//            productService.deleteProduct(product);
//        }
//
//        return ("seller/product");
//    }

    @GetMapping("/seller/product/delete/{productId}")
    public String deleteProductById(@PathVariable Long productId, Model model) {
        productService.deleteProductById(productId);
        return userController.checkSellerApproval(model);
    }

    @GetMapping("/seller/product/updateDetails/{productId}")
    public String getProductDetailsById(@PathVariable Long productId, Model model){
        Product prod = productService.findProductById(productId).get();
        model.addAttribute("productDetails",prod);
        return userController.checkSellerApproval(model);
        //return "mainSeller";
    }

    @PostMapping("/buyer/product/review/")
    public String makeProductReview(HttpServletRequest request, RedirectAttributes redirectAttributes){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User buyer = userService.findByUsername(auth.getName());
        Long productId = Long.parseLong(request.getParameter("productId").trim());
        String description = request.getParameter("productReview");
        Review review = new Review();
        review.setBuyer(buyer);
        review.setDescription(description);
        Product product = productService.findProductById(productId).get();
        review.setProduct(product);
        reviewService.saveReview(review);
        review.setReviewDate(LocalDateTime.now());
        reviewService.saveReview(review);
        redirectAttributes.addFlashAttribute("saveReviewURL",true);
        return "redirect:/buyer";
    }

    @GetMapping("/admin/product/reviews")
    public String getProductReviews(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("productReviews",reviewService.findAllReviews());
        redirectAttributes.addFlashAttribute("productReviewsURL",true);
        return "redirect:/admin";
    }

    @GetMapping("/admin/approve/product/review/{reviewId}")
    public String approveProductReview(@PathVariable Long reviewId, RedirectAttributes redirectAttributes){
        Review review = reviewService.findReviewById(reviewId);
        reviewService.approveReview(review);
        redirectAttributes.addFlashAttribute("productReviewsApprovalURL",true);
        return "redirect:/admin";
    }

    @GetMapping("/buyer/view/product/reviews")
    public String viewAllProductReviews(RedirectAttributes redirectAttributes){
        redirectAttributes.addFlashAttribute("approvedProductReviews",
                reviewService.findAllReviews()
                                .stream()
                                .filter(review->review.getReviewStatus().compareTo(EReviewStatus.APPROVED)==0)
                                .collect(Collectors.toList())
        );
        redirectAttributes.addFlashAttribute("approvedProductReviewsURL",true);
        return "redirect:/buyer";
    }




}
