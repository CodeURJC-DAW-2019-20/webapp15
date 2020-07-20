package es.urjc.code.daw.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@Order(1)
public class RestSecurityConfig extends WebSecurityConfigurerAdapter{

	@Autowired
	public UserRepositoryAuthenticationProvider userRepoAuthProvider;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//Public
		http.antMatcher("/api/**");
		
		//log
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/logIn").authenticated();
		
		//Urls user
		//Urls admin

		//Private pages
		http.authorizeRequests().anyRequest().permitAll();
		
        // Disable CSRF
		http.csrf().disable();
		
		// Use Http Basic Authentication
		http.httpBasic();

        // Do not redirect when logout
		http.logout().logoutSuccessHandler((rq, rs, a) -> {	});

	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(userRepoAuthProvider);
	}
}
