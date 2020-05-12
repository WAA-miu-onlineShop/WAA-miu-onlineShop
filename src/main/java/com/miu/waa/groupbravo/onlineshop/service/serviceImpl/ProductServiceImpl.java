package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.repository.UserRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    //@Autowired
    private SequenceNumberService sequenceNumberService;
   // @Autowired
    private ProductRepository productRepository;
    //@Autowired
    private ProductCategoryRepository productCategoryRepository;
   // @Autowired
    private UserRepository userRepository;
    public ProductServiceImpl(SequenceNumberService sequenceNumberService,ProductRepository productRepository,ProductCategoryRepository productCategoryRepository,UserRepository userRepository){
     this.sequenceNumberService=sequenceNumberService;
     this.productRepository=productRepository;
     this.productCategoryRepository=productCategoryRepository;
     this.userRepository=userRepository;
    }
    @Override
    public void addProduct(Product product) {
        if(product.isNew()){
            String productNumber=sequenceNumberService.getNextProductNumber();
            ProductCategory productCategory=productCategoryRepository.findById(product.getProductCategory().getId()).get();
            product.setProductNumber(productNumber);
            product.setProductCategory(productCategory);
            product.setProductStatus(EProductStatus.NEW);
            product.setSeller(getUser());
            ///UPLOAD FILE
            String rootDirectory=System.getProperty("user.dir");
            MultipartFile photo = product.getMultipartFile();
            String path=rootDirectory+"\\images\\"+ product.getProductNumber()+ ".jpg";
            product.setFile(path);
            if (photo!=null && !photo.isEmpty()) {
                try {
                    photo.transferTo(new File(path));
                } catch (Exception e) {
                   // throw new FileNotFoundException("Can't upload the image: " + photo.getOriginalFilename() );
                }
            }
        }

        productRepository.save(product);
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
    @Override
    public void deleteProduct(Product product) {
        if(product.getProductStatus().compareTo(EProductStatus.NEW)==0||product.getProductStatus().compareTo(EProductStatus.AVAILABLE)==0) {
            productRepository.delete(product);
        }else{
             new Exception("You can not delete  not new or available product");
        }
    }

    @Override
    public Product updateProduct(Product product) {

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

    //find product by user

}




