package com.volounteerfinder.mvcserver.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Date;

@Component
public class JwtProvider {

    @Value("4a2a5d8F1b3c6e7h9Gts4r88ggjjd99494dkkbpkddkvllvlvo99925ys4nfn48h02938rjfasdkjf9aeifjgbktsdkfmvt545tw4kesfmg45kwesgk5m0S")
    private String jwtSecret;

    @Value("3600000")
    private int jwtExpiration;

    public String generateToken(Authentication authentication) {

       /* User user = this.getUsernameFromJwt(authentication.getName());
        UserDetails userPrincipal = (UserDetails)authentication.getAuthorities();
        return Jwts.builder()
                .setSubject(userPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();*/
        Claims claims = Jwts.claims().setSubject(authentication.getName());
        claims.put("userUsername", authentication.getPrincipal() + "");

        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public String getUsernameFromJwt(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody().getSubject();
    }

    public boolean validateToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            // Invalid JWT signature
        } catch (MalformedJwtException e) {
            // Invalid JWT token
        } catch (ExpiredJwtException e) {
            // Expired JWT token
        } catch (UnsupportedJwtException e) {
            // Unsupported JWT token
        } catch (IllegalArgumentException e) {
            // JWT claims string is empty
        }
        return false;
    }
}
