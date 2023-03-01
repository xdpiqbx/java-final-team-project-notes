package ua.goit.notes_proj.admin;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.notes_proj.author.*;

@Controller
@PreAuthorize("hasAnyAuthority('ADMIN', 'MANAGER')")
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
  private final AuthorService authorService;
  @GetMapping
  public ModelAndView getAdmin(Authentication authentication){
    AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
    Author author = authorService.findAuthorById(authorExtended.getId());
    ModelAndView result = new ModelAndView("admin-page");
    result.addObject("author", author.getName());
    result.addObject("authors", authorService.findAllAuthors());
    result.addObject("authority", author.getAuthority());
    return result;
  }
  @PostMapping("/delete/author")
  public RedirectView deleteAuthor(@RequestParam long id, Authentication authentication){
    authorService.deleteById(id);
    AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
    if(id != authorExtended.getId()){
      return new RedirectView("/admin");
    }
    return new RedirectView("/logout");
  }
  @GetMapping("/edit/author")
  public ModelAndView pageEditNote(@RequestParam long id, @RequestParam(required=false, defaultValue="false") boolean error){
    Author author = authorService.findAuthorById(id);
    ModelAndView result = new ModelAndView("author-edit-page");
    result.addObject("error", error);
    result.addObject("author", author);
    result.addObject("role", author.getAuthority().name());
    result.addObject("authorities", Authority.values());
    return result;
  }
  @PostMapping("/edit/author")
  public RedirectView editAuthor(
    Authentication authentication,
    @Valid @ModelAttribute AuthorForEditDTO authorDTO,
    BindingResult bindingResult
  ){
    if(bindingResult.hasErrors()){
      RedirectView result = new RedirectView("/admin/edit/author");
      result.addStaticAttribute("id", authorDTO.getId());
      result.addStaticAttribute("error", true);
      return result;
    }
    authorService.editAuthor(authorDTO);
    if(authorDTO.getAuthority().equals(Authority.USER)){
      return new RedirectView("/note/list");
    }
    return new RedirectView("/admin");
  }
}
