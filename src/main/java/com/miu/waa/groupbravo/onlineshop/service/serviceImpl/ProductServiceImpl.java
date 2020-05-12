package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
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
    @Override
    public void addProduct(Product product) {
        if(product.isNew()){
            String productNumber=sequenceNumberService.getNextProductNumber();
           product.setProductNumber(productNumber);
           product.setProductStatus(EProductStatus.NEW);

        }

        productRepository.save(product);
    }


    @Override
    public void deleteProduct(Product product) {
    productRepository.delete(product);
    }

    @Override
    public Product updateProduct(Product product) {

        return productRepository.save(product);
    }

    @Override
    public List<Product> findAll() {

        return (List<Product>)productRepository.findAll();
    }

    //find product by user

}




