package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;

import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductCategoryService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void saveProductCategory(ProductCategory productCategory) {
     productCategory.setSeller(getUser());
        productCategoryRepository.save(productCategory);

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
        return (List<ProductCategory>) productCategoryRepository.findAll();
    }

    @Override
    public List<ProductCategory> findProductCategoriesBySeller(User seller) {
        return productCategoryRepository.findBySeller(seller);
    }

    @Override
    public ProductCategory findById(Long productCategoryId) {
    return productCategoryRepository.findById(productCategoryId).get();
    }

    private  User getUser(){
        User user=null;
        try {
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = "";
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();
            }
            user = userRepository.findByUsername(username);
        }catch (Exception ex){
            System.out.println(ex.getMessage());
        }
        return user;
    }
}
