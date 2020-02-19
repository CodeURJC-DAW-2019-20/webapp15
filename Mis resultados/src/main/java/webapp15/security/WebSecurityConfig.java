package webapp15.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity 
@ComponentScan
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
    public UserRepositoryAuthenticationProvider authenticationProvider;
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Public pages
        http.authorizeRequests().antMatchers("/").permitAll();
       // http.authorizeRequests().antMatchers("/index").permitAll();
        http.authorizeRequests().antMatchers("/home").permitAll();
        
        // Login form
        http.formLogin().loginPage("/login");
        http.formLogin().usernameParameter("email");
        http.formLogin().passwordParameter("password");
        http.formLogin().defaultSuccessUrl("/home");
        http.formLogin().failureUrl("/loginerror");
        
        // Logout
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/home");
        
    }
    
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
    	
    	/*auth.inMemoryAuthentication().withUser("estudiante@gmail.com").password("1234").roles("STUDENT");
        auth.inMemoryAuthentication().withUser("admin@gmail.com").password("1234").roles("ADMIN");*/
        auth.authenticationProvider(authenticationProvider);
    }
}