package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.exceptions.BravoException;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import com.miu.waa.groupbravo.onlineshop.utils.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SequenceNumberService sequenceNumberService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;
   @Autowired
    private UserRepository userRepository;
    public ProductServiceImpl(SequenceNumberService sequenceNumberService,ProductRepository productRepository,ProductCategoryRepository productCategoryRepository,UserRepository userRepository){
     this.sequenceNumberService=sequenceNumberService;
     this.productRepository=productRepository;
     this.productCategoryRepository=productCategoryRepository;
     this.userRepository=userRepository;
    }
    @PreAuthorize("hasAuthority('SELLER')")
    @Override
    public Product addProduct(Product product) {
        if(product.isNew()){
            String productNumber=sequenceNumberService.getNextProductNumber();
            ProductCategory productCategory=productCategoryRepository.findById(product.getProductCategory().getId()).get();
            product.setProductNumber(productNumber);
            product.setProductCategory(productCategory);
            product.setProductStatus(EProductStatus.NEW);
            product.setSeller(getUser());
            productCategory.setQuantityAvailable(productCategory.getQuantityAvailable().add(BigDecimal.valueOf(1)));
            if(product.getMultipartFile()!=null){
                updatePicture(product);
            }
            ///UPLOAD FILE
           // String rootDirectory=System.getProperty("user.dir");

        }

       return productRepository.save(product);
    }

    private void updatePicture(Product product){

        MultipartFile picture = product.getMultipartFile();
        //String path=rootDirectory+"\\images\\"+ product.getProductNumber()+ ".jpg";

        String filePath=product.getFile();
        String pathToBeSaved=product.getProductNumber()+ ".jpg";
        String newName= StringUtils.cleanPath(pathToBeSaved);
        product.setFile(newName);
        if (picture!=null && !picture.isEmpty()) {
            try {

                Path path= Paths.get(filePath+"src\\main\\resources\\static\\images\\" + newName);
                Files.copy(picture.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);

            } catch (Exception e) {

                throw new BravoException("Can't upload the image: " + picture.getOriginalFilename() );
            }
        }
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
    @PreAuthorize("hasAuthority('SELLER')")
    @Override
    public void deleteProduct(Product product) {
        if(product.getProductStatus().compareTo(EProductStatus.NEW)==0||product.getProductStatus().compareTo(EProductStatus.AVAILABLE)==0) {
            productRepository.delete(product);
        }else{
             new BravoException("You can  neither delete a  new nor available product");
        }
    }
    @PreAuthorize("hasAuthority('SELLER')")
    @Override
    public Product updateProduct(Product product) {
        ProductCategory productCategory=productCategoryRepository.findById(product.getProductCategory().getId()).get();
        User seller=userRepository.findById(product.getSeller().getId()).get();
        product.setSeller(seller);
        product.setProductCategory(productCategory);
        if(product.getMultipartFile()!=null){
            updatePicture(product);
        }
        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {

        return (List<Product>)productRepository.findAll();
    }

    @Override
    public List<Product> findProductsBySeller(User seller) {
        return productRepository.findBySeller(seller);
    }

    @Override
    public void deleteProductById(Long productId) {
        productRepository.deleteById(productId);
    }

    @Override
    public Optional<Product> findProductById(Long productId) {
        return productRepository.findById(productId);
    }

    @Override
    public List<Product> findProductByStatus(List<EProductStatus> productStatusList) {
        return productRepository.findByStatus(productStatusList);
    }

    @Override
    public List<Product> findProductByCategoryAndStatus(ProductCategory productCategory, List<EProductStatus> productStatusList) {
        return productRepository.findByCategoryAndStatus(productCategory,productStatusList);
    }

    @Override
    public Product getProductBySerialNumber(String serialNumber) {
        return productRepository.findBySerialNumber(serialNumber);
    }

    @Override
    public List<Product> findProductsByBuyer(User buyer) {
        return productRepository.findByBuyer(buyer);
    }


    //find product by user

}




