package ua.goit.notes.author;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
public class AuthorExtended extends User{
  private final long id;
  public AuthorExtended(long id, String username, String password, Collection<? extends GrantedAuthority> authorities) {
    super(username, password, authorities);
    this.id = id;
  }
  public long getId() {
    return id;
  }
}
