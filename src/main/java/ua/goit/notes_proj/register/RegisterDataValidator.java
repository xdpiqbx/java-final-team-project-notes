package ua.goit.notes_proj.register;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegisterDataValidator {
  private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
  private final Validator validator = factory.getValidator();
  public List<String> validate(RegisterFormData registerFormData){
    return validator.validate(registerFormData)
      .stream()
      .map(violation -> violation.getPropertyPath().toString())
      .toList();
  }
}
