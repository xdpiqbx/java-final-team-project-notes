package ua.goit.notes_proj.author;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AuthorService {
  private final AuthorRepository authorRepository;
  public List<Author> findAllAuthors(){
    return authorRepository.findAll();
  }
  public Author findAuthorById(long id){
    return authorRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Author with id: ["+id+"] does not exists"));
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
