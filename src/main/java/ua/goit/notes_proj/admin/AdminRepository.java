package ua.goit.notes_proj.admin;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.notes_proj.author.Author;

public interface AdminRepository extends JpaRepository<Author, Long> {
}
