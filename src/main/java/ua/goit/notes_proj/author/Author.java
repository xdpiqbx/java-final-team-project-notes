package ua.goit.notes.author;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;
import ua.goit.notes.note.Note;

import java.util.List;

@Data
@Entity
public class Author {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  @NotBlank(message = "Name is mandatory")
  @Size(min = 5, max = 50)
  @Pattern(regexp = "^[a-zA-Z\\d]*$")
  private String name;
  @ToString.Exclude
  private String password;
  @Enumerated(EnumType.STRING)
  @Column(columnDefinition = "varchar(7) default 'USER'")
  private Authority authority;
  @OneToMany (mappedBy = "author", cascade = CascadeType.REMOVE)
  @ToString.Exclude
  private List<Note> notes;
}
