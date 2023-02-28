package ua.goit.notes_proj.note;

import lombok.RequiredArgsConstructor;
import org.commonmark.node.Node;
import org.commonmark.parser.Parser;
import org.commonmark.renderer.html.HtmlRenderer;
import org.commonmark.renderer.text.TextContentRenderer;
import org.springframework.stereotype.Service;
import ua.goit.notes_proj.author.Author;
import ua.goit.notes_proj.author.AuthorExtended;


import java.util.*;

@Service
@RequiredArgsConstructor
public class NoteService {
    private final NoteRepository noteRepository;
    public LinkedList<Note> findAllByAuthorId(long id){
        LinkedList<Note> allByAuthorId = noteRepository.findAllByAuthorId(id);
        allByAuthorId.forEach(note -> {
            note.setContent(this.convertMarkdownToPlainText(note.getContent()));
        });
        return noteRepository.findAllByAuthorId(id);
    }

    public void createNewNote(AuthorExtended authorExt, NoteDTO noteDTO){
        Note note = new Note();
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setAccessType(AccessType.valueOf(noteDTO.getAccessType().name().toUpperCase()));
        Author author = new Author();
        author.setId(authorExt.getId());
        note.setAuthor(author);
        noteRepository.save(note);
    }

    public Note getNoteById(String id){
        return noteRepository.findById(id)
                .orElseGet(()-> {
                    Note note = new Note();
                    note.setTitle("Not exists");
                    note.setContent("Empty");
                    note.setAccessType(AccessType.PRIVATE);
                    return note;
                });
    }

    public void editNote(AuthorExtended authorExt, NoteDTO noteDTO) {
        Note note = new Note();
        note.setId(noteDTO.getId());
        note.setTitle(noteDTO.getTitle());
        note.setContent(noteDTO.getContent());
        note.setAccessType(AccessType.valueOf(noteDTO.getAccessType().name().toUpperCase()));
        Author author = new Author();
        author.setId(authorExt.getId());
        note.setAuthor(author);
        noteRepository.save(note);
    }
    public void deleteById(String id){
        noteRepository.deleteById(id);
    }

    public String convertMarkdownToHtml(String content){
        Parser parser = Parser.builder().build();
        Node node = parser.parse(content);
        HtmlRenderer renderer = HtmlRenderer.builder().build();
        return renderer.render(node);
    }

    public  String convertMarkdownToPlainText(String mdDoc){
        Parser parser = Parser.builder().build();
        Node node = parser.parse(mdDoc);
        TextContentRenderer renderer = TextContentRenderer.builder().build();
        return renderer.render(node);
    }

    public void setMockData(AuthorExtended authorExt){
        String[] strings = {
                "Single Responsibility Principle#a class should do one thing and therefore it should have only a single reason to change",
                "Open-Closed Principle#classes should be open for extension and closed to modification",
                "Liskov Substitution Principle#subclasses should be substitutable for their base classes",
                "Interface Segregation Principle#is about separating the interfaces",
                "Dependency Inversion Principle#classes should depend upon interfaces or abstract classes instead of concrete classes and functions"
        };
        Author author = new Author();
        Arrays.stream(strings).forEach(str->{
            Note note = new Note();
            String[] titleContent = str.split("#");
            note.setTitle(titleContent[0]);
            note.setContent(titleContent[1]);
            note.setAccessType(AccessType.PRIVATE);
            author.setId(authorExt.getId());
            note.setAuthor(author);
            noteRepository.save(note);
        });
    }
}
