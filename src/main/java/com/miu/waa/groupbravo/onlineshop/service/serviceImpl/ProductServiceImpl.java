package com.miu.waa.groupbravo.onlineshop.service.serviceImpl;
import com.miu.waa.groupbravo.onlineshop.domain.EProductStatus;
import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {


    @Override
    public void addProduct(Product product) {
        
        product.setProductStatus(EProductStatus.NEW);
    }

    @Override
    public void deleteProduct(String product) {

    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public Product findOne(String id) {
        return null;
    }
}




