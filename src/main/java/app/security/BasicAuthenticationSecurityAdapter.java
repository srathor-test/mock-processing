package app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder; 

@Configuration
@EnableWebSecurity
public class BasicAuthenticationSecurityAdapter extends WebSecurityConfigurerAdapter {
 
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
 
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
   @Override
    public void configure(WebSecurity web) throws Exception {
         web.ignoring()         	
            .antMatchers("/h2-console/**")            
         	.antMatchers("/v2/**");
    }
    
    @Override
	protected void configure(HttpSecurity http) throws Exception {    	
    	http.csrf().disable().authorizeRequests()
    	.antMatchers("/*").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()	
        .authenticationEntryPoint(authenticationEntryPoint);       
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {		
		 auth.inMemoryAuthentication()
         .withUser("user").password(passwordEncoder().encode("password"))
         .authorities("USER");
	}
}