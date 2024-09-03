package com.ecommerce.petCare.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtService {



    private static final String SECRET_KEY = "08792A5964FCF2258B752C81620AD5D5DEB15D2C91DE7A000C044D47111562D9";


    public String generateToken(Authentication authentication) {


        // Initialize a map to hold claims
        Map<String,Object> claims = new HashMap<>();


        // Extract the roles from the Authentication object
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        System.out.println(authorities);
        claims.put("roles", authorities.stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList()));

        System.out.println(claims);

        // Build the JWT token with the claims, subject, and other details
        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(authentication.getName())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .and()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);

    }


    public String extractUserName(String token) {
        try {
            return extractClaim(token, Claims::getSubject);
        }catch (JwtException e){
            throw new RuntimeException(e.getMessage());
        }

    }

    private <T> T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();

    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUserName(token);

        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }



}
