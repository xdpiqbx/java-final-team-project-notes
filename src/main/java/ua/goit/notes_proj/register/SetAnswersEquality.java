package ua.goit.notes_proj.register;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SetAnswersEqualityValidator.class)
public @interface SetAnswersEquality {
  String message() default "Some answers are not correct";
  Class<?>[] groups() default {};
  Class<? extends Payload>[] payload() default {};
}