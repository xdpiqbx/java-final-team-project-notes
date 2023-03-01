package ua.goit.notes_proj.note;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class NoteDTO {
    private String id;
    @NotBlank(message = "Title is mandatory")
    @Size(min = 5, max = 100)
    private String title;
    @NotBlank(message = "There is no point in a note without content")
    @Size(min = 5, max = 10000)
    private String content;
    @Enumerated(EnumType.STRING)
    private AccessType accessType;
    public void setAccessType(String accessType) {
        this.accessType = AccessType.valueOf(accessType.toUpperCase());
    }
}
