package com.hexaware.userservice.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.hexaware.userservice.service.JwtService;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {
		
		@Autowired
		JwtService jwtService;
		
		
		@Override
		protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
				throws ServletException, IOException {
			
			String path = request.getServletPath();
			if (path.equals("/users/register") || path.equals("/users/login")) {
			    filterChain.doFilter(request, response); // Let it go to controller
			    return;
			}
			
			  String authHeader = request.getHeader("Authorization");
		        String token = null;
		        String username = null;
		        if (authHeader != null && authHeader.startsWith("Bearer ")) {
		            token = authHeader.substring(7);
		            username = jwtService.extractUsername(token);
		            
		   
		        }
		        
		        
		        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
		            if (jwtService.validateToken(token)) {
		            	
		            	Claims claims = jwtService.extractAllClaims(token);
			            
			            List<String> roles = claims.get("roles", List.class);

			            List<GrantedAuthority> authorities = roles.stream()
			                .map(role -> new SimpleGrantedAuthority("ROLE_" + role)
)
			                .collect(Collectors.toList());
		               
		            	UsernamePasswordAuthenticationToken authToken =
		                new UsernamePasswordAuthenticationToken(username, null, authorities);
		             
		            	authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
		                SecurityContextHolder.getContext().setAuthentication(authToken);
		            }
		        }
		        filterChain.doFilter(request, response);
		    
		
	}

}
