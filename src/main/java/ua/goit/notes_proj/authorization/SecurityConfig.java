package ua.goit.notes_proj.authorization;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
    http
        .headers().frameOptions().sameOrigin() // for /h2-console
          .and()
        .csrf().disable()
        .authorizeHttpRequests()
          .requestMatchers("/login").permitAll()
          .requestMatchers("/register").permitAll()
          .requestMatchers("/note/share/**").permitAll()
          .requestMatchers("/note/**").authenticated()
          .requestMatchers("/admin/**").hasAnyAuthority("ADMIN", "MANAGER")
        .anyRequest().authenticated()
          .and()
        .formLogin()
          .loginPage("/login")
        .defaultSuccessUrl("/note/list", true);
    return http.build();
  }
}