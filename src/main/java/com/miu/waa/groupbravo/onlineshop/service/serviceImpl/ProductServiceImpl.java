package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.domain.ProductCategory;
import com.miu.waa.groupbravo.onlineshop.domain.User;
import com.miu.waa.groupbravo.onlineshop.repository.ProductCategoryRepository;
import com.miu.waa.groupbravo.onlineshop.repository.ProductRepository;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import com.miu.waa.groupbravo.onlineshop.service.SequenceNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private SequenceNumberService sequenceNumberService;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductCategoryRepository productCategoryRepository;

    @Override
    public void addProduct(Product product) {
        if(product.isNew()){
            String productNumber=sequenceNumberService.getNextProductNumber();
            ProductCategory productCategory=productCategoryRepository.findById(product.getProductCategory().getId()).get();
            product.setProductNumber(productNumber);
            product.setProductCategory(productCategory);
            product.setProductStatus(EProductStatus.NEW);
        }

        productRepository.save(product);
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




