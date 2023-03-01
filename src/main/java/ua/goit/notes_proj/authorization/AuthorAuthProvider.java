package ua.goit.notes_proj.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ua.goit.notes_proj.author.AuthorExtended;

@Service
@RequiredArgsConstructor
public class AuthorAuthProvider implements AuthenticationProvider {
  private final AuthorDetailsService authorDetailsService;
  private final PasswordEncoder passwordEncoder;
  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String username = authentication.getName();
    String password = authentication.getCredentials().toString();
    AuthorDetails author = authorDetailsService.loadUserByUsername(username);
    return checkPassword(author, password);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return  UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
  }

  private Authentication checkPassword(AuthorDetails author, String rawPassword){
    if(passwordEncoder.matches(rawPassword, author.getPassword())){
      AuthorExtended innerAuthor = new AuthorExtended(
          author.getId(),
          author.getUsername(),
          author.getPassword(),
          author.getAuthorities()
      );
      return new UsernamePasswordAuthenticationToken(innerAuthor, author.getPassword(), author.getAuthorities());
    }else{
      throw new BadCredentialsException("Bad credentials");
    }
  }
}
