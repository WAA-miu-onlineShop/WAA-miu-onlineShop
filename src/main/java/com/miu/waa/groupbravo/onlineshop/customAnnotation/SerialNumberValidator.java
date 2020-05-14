package com.miu.waa.groupbravo.onlineshop.customAnnotation;

import com.miu.waa.groupbravo.onlineshop.domain.Product;
import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
@Component
public class SerialNumberValidator implements ConstraintValidator<SerialNumber,String> {
    @Autowired
    ProductService productService;

    @Override
    public void initialize(SerialNumber serial) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Product product=null;
        try{
            product=productService.getProductBySerialNumber(value);
        }
        catch(Exception e){
            System.out.println("the Product does not exist!");
        }
        return product == null ? true : false;
    }
}
