package ua.goit.notes_proj.register;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Set;
public class SetAnswersEqualityValidator implements ConstraintValidator<SetAnswersEquality, Set<String>> {
  @Override
  public boolean isValid(Set<String> answers, ConstraintValidatorContext context) {
    if (answers == null) {
      return true;
    }
    RegisterQuestions registerQuestions = new RegisterQuestions();
    Set<String> correctAnswers = registerQuestions.getCorrectAnswers();
    return answers.equals(correctAnswers);
  }
  @Override
  public void initialize(SetAnswersEquality constraintAnnotation) {
    ConstraintValidator.super.initialize(constraintAnnotation);
  }
}
