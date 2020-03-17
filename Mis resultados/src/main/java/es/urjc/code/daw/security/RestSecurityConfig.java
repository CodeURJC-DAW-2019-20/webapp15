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
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/user/").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/user/updateFav/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/apostar/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/apostar/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/apostar/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/apostar/**").hasRole("USER");
		http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/historico").hasRole("USER");
		
		//Urls admin
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/update/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/updateTeam/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/updateTeamWin/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.PUT, "/api/updateTeamTied/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/saveTeam/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.DELETE, "/api/deleteTeam/**").hasRole("ADMIN");
		http.authorizeRequests().antMatchers(HttpMethod.POST, "/api/equipos/addMatch/**").hasRole("ADMIN");
		
		
		// Other URLs can be accessed without authentication
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
