package com.patika.security.Jwt;

import io.jsonwebtoken.*;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    /*
    jwt üreteceğim
    jwt doğrulama
    jwt->username
     */

    private String jwtSecret="patika*";
    private Long jwtExpirationMS =86400000L;     //24*60*60*1000

    //token üretiyoruz
    public String generateJwtToken(UserDetails userDetails){
        return Jwts.builder().
                setSubject(userDetails.getUsername()).
                setExpiration(new Date(new Date().getTime()+jwtExpirationMS)).
                signWith(SignatureAlgorithm.HS512,jwtSecret).compact();

    }

    //Token'dan email alma
    public String getEmailFromToken(String token){
        return Jwts.parser().setSigningKey(jwtSecret).
                parseClaimsJws(token).
                getBody().getSubject();
    }
    ///*/* TOKEN DOĞRULAMA
    public boolean validateJwtToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException | SignatureException |
                 IllegalArgumentException e) {

        }
        return false;
    }

}
