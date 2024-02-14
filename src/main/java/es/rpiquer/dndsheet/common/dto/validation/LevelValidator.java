package es.rpiquer.dndsheet.common.dto.validation;

import es.rpiquer.dndsheet.common.exception.DTOValidationException;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class LevelValidator implements ConstraintValidator<ValidLevel, Integer>{
    private String message;
    @Override
    public void initialize(ValidLevel constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Integer level, ConstraintValidatorContext constraintValidatorContext) {
        if(level <= 0 || level >20) {
            throw new DTOValidationException(message);
        }
        return level > 0;
    }
    
}
