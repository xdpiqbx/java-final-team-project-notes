package ua.goit.notes_proj.note;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ua.goit.notes_proj.author.Author;

@Data
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    @NotBlank(message = "Title is mandatory")
    @Size(min = 5, max = 100)
    private String title;
    @NotBlank(message = "There is no point in a note without content")
    @Size(min = 5, max = 10000)
    private String content;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(
            name="author_id",
            nullable=false)
    private Author author;
}
