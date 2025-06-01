package com.hexaware.rentalservice.service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	public static final String SECRET = "j0lYz3pWhH7DQo2x2X/kCmMoW1iB0W9xDlLSP6Q1vQo=";

	
	private Key getSignKey() {

		byte[] keyBytes = Decoders.BASE64.decode(SECRET);

		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	// BELOW METHODS HELP TO READ TOKEN FROM CLIENT AND GET Claims , username, exp time etc from token
	
	
		public Claims extractAllClaims(String token) {

			return Jwts.parserBuilder().setSigningKey(getSignKey()).build().parseClaimsJws(token).getBody();

		}

		public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {

			final Claims claims = extractAllClaims(token);
			

			return claimsResolver.apply(claims);

		}

		 public String extractUsername(String token) {
		        return extractClaim(token, Claims::getSubject);
		    }

		    public Date extractExpiration(String token) {
		        return extractClaim(token, Claims::getExpiration);
		    }

		
		    private Boolean isTokenExpired(String token) {
		        return extractExpiration(token).before(new Date());
		    }

		    public Boolean validateToken(String token) {
		        //final String username = extractUsername(token);
		        return !isTokenExpired(token);
		    } 
		

}
