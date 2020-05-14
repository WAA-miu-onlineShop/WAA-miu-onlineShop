package customAnnotation;

import com.miu.waa.groupbravo.onlineshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

//public class UniqueSerialNumberValidator implements ConstraintValidator<UniqueSerialNumber,String>{
//@Autowired
//private ProductService productService
//
//    @Override
//    public boolean isValid(String value, ConstraintValidatorContext context) {
//        return value != null && !productService.isSerialNumberAlreadyInUse(value);
//    }
//}
