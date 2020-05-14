package com.miu.waa.groupbravo.onlineshop.customAnnotation;
import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = SerialNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE })
public @interface SerialNumber {

    String message() default "{SerialNumber.default.uniqueness.validation}";
    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default{};

    }

