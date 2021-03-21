package com.payroll.payrollWebService.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.payroll.payrollWebService.security.authservices.UserDetailsImpl;
import io.jsonwebtoken.*;

@Component
public class 	JwtUtils {
	private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

	@Value("${anodiam.app.jwtSecret}")
	private String jwtSecret;

	@Value("${anodiam.app.jwtExpirationMs}")
	private int jwtExpirationMs;

	public JwtUtils() {
	}

	public String generateJwtToken(Authentication authentication) {

		UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

		try {
			String JWTToken = Jwts.builder()
					.setSubject(userPrincipal.getUsername())
					.setIssuedAt(new Date())
					.setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
					.signWith(SignatureAlgorithm.HS512,jwtSecret)
					.compact();
			System.out.println("Generated Token : " + JWTToken);
			return JWTToken;
		}catch(Exception ex)
		{
			System.out.println("Exception is : " + ex.toString());
		}
		return "Token Generation Failed";
	}

	public String getUserNameFromJwtToken(String token) {
		return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateJwtToken(String authToken) {

		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException e) {
			logger.error("Invalid JWT signature: {}", e.getMessage());
		} catch (MalformedJwtException e) {
			logger.error("Invalid JWT token: {}", e.getMessage());
		} catch (ExpiredJwtException e) {
			logger.error("JWT token is expired: {}", e.getMessage());
		} catch (UnsupportedJwtException e) {
			logger.error("JWT token is unsupported: {}", e.getMessage());
		} catch (IllegalArgumentException e) {
			logger.error("JWT claims string is empty: {}", e.getMessage());
		}
		return false;
	}

}
