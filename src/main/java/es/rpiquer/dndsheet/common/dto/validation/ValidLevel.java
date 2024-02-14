package es.rpiquer.dndsheet.common.dto.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AttributeValidator.class)
public @interface ValidLevel {
    String message() default "El nivel debe ser entre 1 y 20, ambos incluidos";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
