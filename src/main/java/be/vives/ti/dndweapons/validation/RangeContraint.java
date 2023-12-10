package be.vives.ti.dndweapons.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RangeConstraintValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface RangeContraint {
    String message() default "Invalid range";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}