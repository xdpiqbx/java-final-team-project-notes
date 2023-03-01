package ua.goit.notes_proj.register;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
@Component
class RegisterQuestions {
  private final long trueCount;
  private final Set<String> correctAnswers;
  private final Map<String, Boolean> questions;
  RegisterQuestions(){
    this.questions = getVariants();
    this.trueCount = questions.values().stream().filter(item -> item).count();
    this.correctAnswers = questions.entrySet()
      .stream()
      .filter(Map.Entry::getValue)
      .map(Map.Entry::getKey)
      .collect(Collectors.toSet());
  }
  public Map<String, Boolean> getVariants() {
    Map<String, Boolean> variants = new HashMap<>();
    variants.put("Полуниця", true);
    variants.put("Палініца", false);
    variants.put("Пальцініндзя", false);
    variants.put("Паляниця", true);
    variants.put("Паланіца", false);
    return variants;
  }
  public Set<String> getQuestionsSet(){
    return questions.keySet();
  }

  public Set<String> getCorrectAnswers() {
    return correctAnswers;
  }
}
