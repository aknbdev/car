package uz.sitelabs.car.validation;

import uz.sitelabs.car.dto.dto.RegistrationDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object>{

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        RegistrationDto dto =  (RegistrationDto) value;
        return dto.getPassword().equals(dto.getMatchingPassword());
    }
    
}
