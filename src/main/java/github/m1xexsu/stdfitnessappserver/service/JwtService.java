package github.m1xexsu.stdfitnessappserver.service;

import github.m1xexsu.stdfitnessappserver.entity.UserEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

/**
 * Сервис для выпуска JWT bearer token.
 */
@Service
public class JwtService {
    private static final String TOKEN_TYPE = "Bearer";

    private final JwtEncoder jwtEncoder;
    private final long expirationSeconds;

    public JwtService(JwtEncoder jwtEncoder,
                      @Value("${app.jwt.expiration-seconds:3600}") long expirationSeconds) {
        this.jwtEncoder = jwtEncoder;
        this.expirationSeconds = expirationSeconds;
    }

    /**
     * Генерирует JWT для пользователя.
     *
     * @param user пользователь
     * @return JWT token string
     */
    public String generateToken(UserEntity user) {
        Instant now = Instant.now();

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .subject(user.getUsername())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expirationSeconds))
                .claim("uid", user.getUser_id())
                .claim("username", user.getUsername())
                .build();

        JwsHeader header = JwsHeader.with(MacAlgorithm.HS256).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    /**
     * Возвращает тип токена для ответов API.
     *
     * @return Bearer
     */
    public String getTokenType() {
        return TOKEN_TYPE;
    }

    /**
     * Возвращает срок жизни токена в секундах.
     *
     * @return expiration seconds
     */
    public long getExpirationSeconds() {
        return expirationSeconds;
    }
}
