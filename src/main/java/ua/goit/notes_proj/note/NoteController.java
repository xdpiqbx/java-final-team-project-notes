package ua.goit.notes_proj.note;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import ua.goit.notes_proj.author.Author;
import ua.goit.notes_proj.author.AuthorExtended;
import ua.goit.notes_proj.author.AuthorService;

import java.util.List;

@Controller
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;
    private final AuthorService authorService;
    @GetMapping("/list")
    public ModelAndView getListOfNotes(Authentication authentication){
        AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
        Author author = authorService.findAuthorById(authorExtended.getId());
        List<Note> notes = author.getNotes();
        notes.forEach(note -> {
            String content = note.getContent();
            int length = content.length();
            if (length > 30){
                note.setContent(content.substring(0, 30) + "...");
            }
        });
        ModelAndView result = new ModelAndView("note-list");
        result.addObject("notes", notes);
        result.addObject("author", author.getName());
        result.addObject("authority", author.getAuthority());
        return result;
    }
    @GetMapping("/create")
    public ModelAndView pageCreateNote(){
        return new ModelAndView("create-or-edit-note");
    }
    @PostMapping("/create")
    public RedirectView createNote(
            Authentication authentication,
            @Valid @ModelAttribute NoteDTO noteDTO,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return new RedirectView("/note/create-edit-note-error-page");
        }
        AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
        noteService.createNewNote(author, noteDTO);
        return new RedirectView("/note/list");
    }
    @GetMapping("/edit")
    public ModelAndView pageEditNote(Authentication authentication, @RequestParam String id){
        AuthorExtended authorExtended = (AuthorExtended)authentication.getPrincipal();
        Note note = noteService.getNoteById(id);
        if(note.getAuthor().getId() != authorExtended.getId()){
            return new ModelAndView("note-not-exists");
        }
        ModelAndView result = new ModelAndView("create-or-edit-note");
        result.addObject("note", note);
        result.addObject("public", note.getAccessType().getType().equals("public"));
        result.addObject("private", note.getAccessType().getType().equals("private"));
        return result;
    }
    @PostMapping("/edit")
    public RedirectView editNote(
            Authentication authentication,
            @Valid @ModelAttribute NoteDTO noteDTO,
            BindingResult bindingResult
    ){
        if (bindingResult.hasErrors()){
            return new RedirectView("/note/create-edit-note-error-page");
        }
        AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
        Note note = noteService.getNoteById(noteDTO.getId());
        if(note.getAuthor().getId() != author.getId()){
            return new RedirectView("/note/list");
        }
        noteService.editNote(author, noteDTO);
        return new RedirectView("/note/list");
    }
    @GetMapping("/share/{id}")
    public ModelAndView createNote(@PathVariable("id") String id){
        Note note = noteService.getNoteById(id);
        if(note.getAccessType().getType().equals("private")){
            return new ModelAndView("note-not-exists");
        }
        note.setContent(noteService.convertMarkdownToHtml(note.getContent()));
        ModelAndView result = new ModelAndView("note");
        result.addObject("note", note);
        return result;
    }
    @PostMapping("/delete")
    public RedirectView deleteNote(@RequestParam String id){
        noteService.deleteById(id);
        return new RedirectView("/note/list");
    }
    @GetMapping("/create-edit-note-error-page")
    public ModelAndView errorPageCreateEdit(){
        return new ModelAndView("create-edit-note-error-page");
    }

    @GetMapping("/addmock")
    public RedirectView createNote(Authentication authentication){
        AuthorExtended author = (AuthorExtended)authentication.getPrincipal();
        noteService.setMockData(author);
        return new RedirectView("/note/list");
    }
}
