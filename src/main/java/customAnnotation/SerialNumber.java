package customAnnotation;
import java.lang.annotation.*;
import java.lang.annotation.*;
import javax.validation.*;

@Constraint(validatedBy = SerialNumberValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface SerialNumber {

    String message() default "{SerialNumber.uniqueness.validation}";
    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default{};

    }

