package ua.goit.notes_proj.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.goit.notes_proj.author.Author;
import ua.goit.notes_proj.author.AuthorRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorDetailsService implements UserDetailsService {
  private final AuthorRepository authorRepository;
  @Override
  public AuthorDetails loadUserByUsername(String username) throws UsernameNotFoundException {

    Author authorData = authorRepository.findByName(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    return new AuthorDetails(){
      @Override
      public long getId() {
        return authorData.getId();
      }
      @Override
      public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> authority = new ArrayList<>();
        authority.add(authorData.getAuthority().toString());
        return authority.stream()
            .map(it -> (GrantedAuthority) () -> it)
            .toList();
      }
      @Override
      public String getPassword() {
        return authorData.getPassword();
      }
      @Override
      public String getUsername() {
        return authorData.getName();
      }
      @Override
      public boolean isAccountNonExpired() {
        return true;
      }
      @Override
      public boolean isAccountNonLocked() {
        return true;
      }
      @Override
      public boolean isCredentialsNonExpired() {
        return true;
      }
      @Override
      public boolean isEnabled() {
        return true;
      }
    };
  }
}
