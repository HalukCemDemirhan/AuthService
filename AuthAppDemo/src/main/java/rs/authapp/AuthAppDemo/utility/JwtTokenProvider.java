package rs.authapp.AuthAppDemo.utility;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import org.springframework.security.core.Authentication;
import rs.authapp.AuthAppDemo.enums.RoleType;
import rs.authapp.AuthAppDemo.model.User;

import java.nio.charset.StandardCharsets;

@Component
public class JwtTokenProvider {
/*
    @Value("${app.jwtSecret}")
    private String jwtSecret;

 */
    public static final String SECRET = "OmllU2syY0dsN1Q2Tm5GSkhaRzN4Z1VZbWhuYzNRZFFMbGFWbUxhbWx0Y3pFbXJ6eHpFbWdZbXJ5ZkFzNXRiYg==";

    @Value("${app.jwtExpirationMs}")
    private int jwtExpirationInMs;

    public String generateToken(Authentication authentication, RoleType roleType) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationInMs);

        // Create a key for signing the JWT
        /*
        byte[] apiKeySecretBytes = jwtSecret.getBytes(StandardCharsets.UTF_8);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

         */
        Key signingKey = getSignKey();

        return Jwts.builder()
                .setSubject(authentication.getName())
                .setIssuedAt(new Date())
                .setExpiration(expiryDate)
                .signWith(signingKey, SignatureAlgorithm.HS512)
                .claim("role", roleType.name())
                .compact();
    }
    private Key getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // ... additional methods for JWT validation, etc.
}
