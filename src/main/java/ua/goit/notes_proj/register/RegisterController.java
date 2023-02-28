package ua.goit.notes_proj.register;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
public class RegisterController {
  private final RegisterService registerService;
  private final RegisterDataValidator registerDataValidator;
  private final RegisterQuestions registerQuestions;
  @GetMapping
  public ModelAndView registerPage() {
    ModelAndView result = new ModelAndView("register");
    result.addObject("questions", registerQuestions.getQuestionsSet());
    return result;
  }
  @PostMapping
  public ModelAndView register(
      @Valid @ModelAttribute RegisterFormData registerFormData,
      BindingResult bindingResult
  ){
    if (bindingResult.hasErrors()){
      ModelAndView result = new ModelAndView("register");
      List<String> validate = registerDataValidator.validate(registerFormData);
      result.addObject("isValidName", !validate.contains("username"));
      result.addObject("isValidPass", !validate.contains("password"));
      result.addObject("isAnswersCorrect", !validate.contains("answers"));
      result.addObject("questions", registerQuestions.getQuestionsSet());
      return result;
    }
    if(!registerService.add(registerFormData)){
      ModelAndView result = new ModelAndView("register");
      result.addObject("questions", registerQuestions.getQuestionsSet());
      result.addObject("userAlreadyExists", registerFormData.getUsername());
      return result;
    }
    return new ModelAndView("login");
  }
}
