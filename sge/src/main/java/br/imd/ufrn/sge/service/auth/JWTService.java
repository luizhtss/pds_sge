package br.imd.ufrn.sge.service.auth;

import br.imd.ufrn.sge.models.auth.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;

@Component
public class JWTService {

    private SecretKey secretKey;

    private static final long EXPIRATION_TIME = 900_000; // 15 minutos

    private  JWTService() {
        String secretString = "RElNMDEzOCAtIFBST0pFVE8gREVUQUxIQURPIERFIFNPRlRXQVJF";
        byte[] keyBytes = Base64.getDecoder().decode(secretString.getBytes(StandardCharsets.UTF_8));

        this.secretKey = new SecretKeySpec(keyBytes,"HmacSHA256");
    }

    public String generateToken(Usuario user) {
        return Jwts.builder()
                .setSubject(user.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String refreshToken(HashMap<String, Object> claims, Usuario user) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(user.getLogin())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey)
                .compact();
    }

    public String getLoginFromToken(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        return claimsResolver.apply(Jwts.parser().verifyWith(this.secretKey).build().parseSignedClaims(token).getPayload());
    }

    public boolean isTokenValid(String token, Usuario user) {
        final String login = getLoginFromToken(token);
        return (login.equals(user.getLogin()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

}
