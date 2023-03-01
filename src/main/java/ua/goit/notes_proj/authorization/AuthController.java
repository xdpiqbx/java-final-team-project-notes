package ua.goit.notes_proj.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthController {
  @GetMapping
  public ModelAndView loginPage(){
    return new ModelAndView("login");
  }
}
