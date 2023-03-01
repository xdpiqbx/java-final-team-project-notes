package ua.goit.notes_proj.author;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class AuthorForEditDTO {
    private long id;
    @NotBlank(message = "Name is mandatory")
    @Size(min = 5, max = 50)
    @Pattern(regexp = "^[a-zA-Z\\d]*$")
    private String name;
    private boolean error;
    @Enumerated(EnumType.STRING)
    private Authority authority;
}
