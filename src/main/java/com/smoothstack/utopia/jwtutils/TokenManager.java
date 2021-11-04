package com.smoothstack.utopia.jwtutils;

import java.io.Serializable;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class TokenManager implements Serializable {

   private static final long serialVersionUID = 7008375124389347049L;

   public static final long TOKEN_VALIDITY = 10 * 60 * 60;

   @Value("${secret}")
   private String jwtSecret;

   public String generateJwtToken(final UserDetails userDetails) {
      final Map<String, Object> claims = new HashMap<>();
      return Jwts.builder().setClaims(claims).setSubject(userDetails.getUsername())
         .setIssuedAt(new Date(System.currentTimeMillis()))
         .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000))
         .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
   }

   public Boolean validateJwtToken(final String token, final UserDetails userDetails) {
      final String username = getUsernameFromToken(token);
      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      final Boolean isTokenExpired = claims.getExpiration().before(new Date());
      return (username.equals(userDetails.getUsername()) && !isTokenExpired);
   }

   public String getUsernameFromToken(final String token) {
      final Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
      return claims.getSubject();
   }

}

