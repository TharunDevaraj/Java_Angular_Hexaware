package com.hexaware.userservice.service;

import java.security.Key;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.hexaware.userservice.entity.UserInfo;
import com.hexaware.userservice.repository.UserInfoRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	@Autowired
	UserInfoRepository userInfoRepository;
	
	public static final String SECRET = "j0lYz3pWhH7DQo2x2X/kCmMoW1iB0W9xDlLSP6Q1vQo=";

	// BELOW METHODS GENERATE AND GIVEN TOKEN
	
	public String createToken(Map<String, Object> claims, String username) {

		return Jwts.builder().setClaims(claims).setSubject(username).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 30))
				.signWith(getSignKey(), SignatureAlgorithm.HS256).compact();

	}

	private Key getSignKey() {

		byte[] keyBytes = Decoders.BASE64.decode(SECRET);

		return Keys.hmacShaKeyFor(keyBytes);
	}

	public String generateToken(String username) {
		
		UserInfo userDetails=userInfoRepository.findByUserName(username);
		if(userDetails==null)
		{
			throw new UsernameNotFoundException(username);
		}

		Map<String, Object> claims = new HashMap<>();
		
		List<String> roles =Arrays.asList( userDetails.getRoles().split(","));

		    claims.put("roles", roles);

		return createToken(claims, username);

	}

}
