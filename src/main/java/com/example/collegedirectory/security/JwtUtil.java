package com.example.collegedirectory.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtil {

    private String SECRET_KEY = "c4adb7e11cf0c6f6fb1f462495fa9ca71aeb04c891432c9882a9dc90df15a2c8058d735aba5966809a0c6168c91b0b41af4e08e5e184d5b604d51e052d1b86b4b834a2516bde8b16278b920b6e8f85b4ba5a6e5c42c659414e8bee78392c754271e93b025f75de7f79637a3c45287bb54a6a1ca168c2878b8018d3e5bea70bbfc20c8d3e3152881fef4bed89882ed53d118e69edc8e1ebc758d3900d58cb650bca583911c4abf89a5ccc78818a9b01b84062d6f5218808f61806a2e88a52eac3bb569033d8effaed40707df3f048e69824f45aaf1f15a69e5769300b8cf510c5a93ba6c65fcc063c40362fdebb77bce3d61f9e06981e9d31876ab1c8179349f5";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}