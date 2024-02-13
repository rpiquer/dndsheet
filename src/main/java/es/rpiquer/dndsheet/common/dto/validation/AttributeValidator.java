package es.rpiquer.dndsheet.common.dto.validation;

import es.rpiquer.dndsheet.common.exception.DTOValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class AttributeValidator implements ConstraintValidator<ValidAttribute, Integer> {

    private String message;
    @Override
    public void initialize(ValidAttribute constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer attribute, ConstraintValidatorContext constraintValidatorContext) {
        if(attribute <= 0) {
            throw new DTOValidationException(message);
        }
        return attribute > 0;
    }
}