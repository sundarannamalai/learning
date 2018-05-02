package learning.webservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * Created by
 * <p>
 * User: sannamalai
 * Date: 02/05/18
 * Time: 10:25 PM
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	public void conifgureGlobalSecurity(AuthenticationManagerBuilder authBuilder) throws Exception {
		authBuilder
				.inMemoryAuthentication()
				.withUser("surveyuser").password("{noop}suser123").roles("USER")
				.and()
				.withUser("surveyadmin").password("{noop}sadmin123").roles("USER", "ADMIN");

		;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.httpBasic().and().authorizeRequests()
				.antMatchers("/surveys/**").hasRole("USER")
				.antMatchers("/**").hasRole("ADMIN")
				.and().csrf().disable()
				.headers().frameOptions().disable();
	}
}
