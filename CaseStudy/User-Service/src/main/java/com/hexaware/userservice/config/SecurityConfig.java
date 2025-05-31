package com.hexaware.userservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return new UserInfoUserDetailsService();
	}
	
	 @Bean
	 public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
	    	
	    		return http.csrf().disable()
	    			.authorizeHttpRequests().requestMatchers("/users/","/users/register","/users/login")
	    			.permitAll()
	    			.and()
	    			.authorizeHttpRequests().requestMatchers("/products/**")
	    			.authenticated().and()   //.formLogin().and().build();
	    			.sessionManagement()
	    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    			.and()
	    			.authenticationProvider(authenticationProvider())
	    			.build();
	    	
	    }
	
	@Bean    
    public PasswordEncoder passwordEncoder() {          
        return new BCryptPasswordEncoder();
    }
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    	
    	return config.getAuthenticationManager();
    	
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

}
