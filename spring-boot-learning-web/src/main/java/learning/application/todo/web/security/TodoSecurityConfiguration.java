package learning.application.todo.web.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 14/04/18
 * Time: 10:55 PM
 */
@Configuration
@EnableWebSecurity
public class TodoSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Autowired
  public void conifgureGlobalSecurity(AuthenticationManagerBuilder authBuilder) throws Exception {
    authBuilder
        .inMemoryAuthentication()
        .withUser("learn")
        .password("{noop}lrn123")
        .roles("USER", "ADMIN")
    ;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests().antMatchers("/login")
        .permitAll()
        .antMatchers("/", "/*todo*/**").access("hasRole('USER')").and()
        .formLogin();
  }
}
