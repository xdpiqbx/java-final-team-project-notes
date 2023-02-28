package ua.goit.notes_proj.author;

import lombok.Data;

@Data
public class Author {
    private long id;
    private String name;
    private Authority authority;
}
