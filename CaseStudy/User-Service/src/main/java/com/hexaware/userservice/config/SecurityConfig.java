package com.hexaware.userservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.hexaware.userservice.filter.JwtAuthFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
	
	@Autowired
    private JwtAuthFilter jwtAuthFilter;
	
	@Bean
	public UserDetailsService userDetailsService() {
	    return new UserInfoUserDetailsService();
	}
	
	 @Bean
	 public  SecurityFilterChain   getSecurityFilterChain(HttpSecurity http) throws Exception {
	    	
	 
				return http.csrf().disable()
	    			.authorizeHttpRequests().requestMatchers("/users/register","/users/login")
	    			.permitAll()
	    			.anyRequest()
	    			.authenticated().and()   //.formLogin().and().build();
	    			.sessionManagement()
	    			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	    			.and()
	    			.authenticationProvider(authenticationProvider())
	    			.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
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
