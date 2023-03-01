package ua.goit.notes_proj.register;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Set;

@Data
public class RegisterFormData {
  @NotBlank(message = "Name is mandatory")
  @Size(min = 5, max = 50)
  @Pattern(regexp = "^[a-zA-Z\\d]*$")
  private String username;
  @NotBlank(message = "Password is mandatory")
  @Size(min = 8, max = 100)
  private String password;
  @NotNull
  @SetAnswersEquality
  private Set<String> answers;
}
