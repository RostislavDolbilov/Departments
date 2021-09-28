package company.constraint.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = DismissalDateValidatorImpl.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface DismissalDateValidator {
    String message() default "";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
