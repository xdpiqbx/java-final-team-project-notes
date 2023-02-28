package ua.goit.notes_proj.note;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.LinkedList;

@Repository
public interface NoteRepository extends JpaRepository<Note, String> {
    LinkedList<Note> findAllByAuthorId(long id);
}
