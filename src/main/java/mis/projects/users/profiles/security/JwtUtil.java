package mis.projects.users.profiles.security;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import mis.projects.users.profiles.ProfilesApplication;
import mis.projects.users.profiles.config.GlobalExceptionHandler;
import mis.projects.users.profiles.controllers.RoleController;
import mis.projects.users.profiles.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {

    private final RoleRepository roleRepository;

    private final RoleController roleController;

    private final ProfilesApplication profilesApplication;

    private final GlobalExceptionHandler globalExceptionHandler;
    
    @Value("${jwt.secret}")
    private String SECRET_KEY;
    private final long EXPIRATION_TIME = 86400000;

    JwtUtil(GlobalExceptionHandler globalExceptionHandler, ProfilesApplication profilesApplication, RoleController roleController, RoleRepository roleRepository) {
        this.globalExceptionHandler = globalExceptionHandler;
        this.profilesApplication = profilesApplication;
        this.roleController = roleController;
        this.roleRepository = roleRepository;
    } // 1 día

    public String generateToken(String username){ 
        return createToken(null, username);
    }

    private String createToken(Map<String, Object> claims, String subject){
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

        return Jwts.builder()
                    .claims(claims)
                    .subject(subject)
                    .issuedAt(new Date())
                    .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME ))
                    .signWith(key)
                    .compact();
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }

    private Claims extractClaims(String token){
        return Jwts.parser()
                    .verifyWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    public boolean validateToken(String token, String username){
        try {
            final String extractedUsername = extractUsername(token);
            return extractedUsername.equals(username) && !isTokenExpired(token);
        } catch (Exception e) {
            return false;
        }
    }
}
