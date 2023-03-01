package ua.goit.notes_proj.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.goit.notes_proj.note.Note;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private static final int MAX_NOTE_CONTENT_LENGTH_IN_NOTES_LIST = 30;
  private final AuthorRepository authorRepository;
  public List<Author> findAllAuthors(){
    return authorRepository.findAll();
  }
  public Author findAuthorById(long id){
    Author author = authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author with id: [" + id + "] does not exists"));
    List<Note> notes = author.getNotes();
    notes.forEach(note -> {
      String content = note.getContent();
      int length = content.length();
      if (length > MAX_NOTE_CONTENT_LENGTH_IN_NOTES_LIST){
        note.setContent(content.substring(0, MAX_NOTE_CONTENT_LENGTH_IN_NOTES_LIST) + " ...");
      }
    });
    author.setNotes(notes);
    return author;
  }
  public void deleteById(long id) {
    authorRepository.deleteById(id);
  }
  public void editAuthor(AuthorForEditDTO authorDTO){
    Author author = findAuthorById(authorDTO.getId());
    author.setName(authorDTO.getName());
    author.setAuthority(Authority.valueOf(authorDTO.getAuthority().name()));
    authorRepository.save(author);
  }
}
