package com.social.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;

import javax.crypto.SecretKey;

import java.util.Date;

import static com.social.config.JwtConstant.SECRET_KEY;

public class JwtProvider {

    private static SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

   public static String generateToken(Authentication authentication){

       String jwt = Jwts.builder()
               .setIssuer("Rishab").setIssuedAt(new Date())
               .setExpiration(new Date(new Date().getTime()+86400000))
               .claim("email",authentication.getName())
               .signWith(key)
               .compact();

       return jwt;
   }

   public static String getEmailFromJwtToken(String jwt){

       jwt = jwt.substring(7);

       Claims claims = Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(jwt).getBody();

       String email = String.valueOf(claims.get("email"));

       return email;
   }
}
