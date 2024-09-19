package com.teamx.retroStore.security.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamx.retroStore.security.dto.AuthDTO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

@Component
public class JwtAuthenticationService {

    private static final Logger LOG = LoggerFactory.getLogger(JwtAuthenticationService.class);

    private static final String SECRET = "4F7831384C6274333736327065796C436D723339485033596A4C4B4B64685679";

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private static long ACCESS_TOKEN_EXPIRATION_TIME;
    private static long REFRESH_TOKEN_EXPIRATION_TIME;

    private static String JWT_TOKEN_ISSUER;

    private static ObjectMapper objectMapper = new ObjectMapper();

    public JwtAuthenticationService(@Value("${jwt.token.access.expiration.duration.in.minutes}") String tokenExpirationDuration,
                                    @Value("${jwt.token.refresh.expiration.duration.in.minutes}") String refreshTokenExpirationDuration,
                                    @Value("${jwt.token.issuer}") String tokenIssuer) {
        try {
            ACCESS_TOKEN_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(Long.parseLong(tokenExpirationDuration));
            REFRESH_TOKEN_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(Long.parseLong(refreshTokenExpirationDuration));
            JWT_TOKEN_ISSUER = tokenIssuer;
        } catch (NumberFormatException e) {
            ACCESS_TOKEN_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(15);
            REFRESH_TOKEN_EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(120);
        }
    }

    public static String authenticateAndGetUsername(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) return null;
        return extractUsername(token);
    }

    public static boolean isValidToken(HttpServletRequest request) {
        String token = extractToken(request);
        if (token == null) return false;
        return !isTokenExpired(token);
    }

    public static void addAuthentication(HttpServletResponse res, AuthDTO userDTO) throws IOException {

        String accessToken = generateAccessToken(userDTO);
        String refreshToken = generateRefreshToken(userDTO);

        userDTO.setPassword(null);
        userDTO.setAccessToken(accessToken);
        userDTO.setRefreshToken(refreshToken);

        res.setContentType(MediaType.APPLICATION_JSON.toString());
        res.getWriter().write(objectMapper.writeValueAsString(userDTO));
    }

    private static String extractToken(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);
        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            LOG.warn("WARN: Token not received in the request originated from: {}", request.getRemoteHost());
            return null;
        }
        token = token.replace(TOKEN_PREFIX, "");
        return token;
    }

    private static String generateAccessToken(AuthDTO userDTO) {
        return generateToken(userDTO, ACCESS_TOKEN_EXPIRATION_TIME);
    }

    private static String generateRefreshToken(AuthDTO userDTO) {
        return generateToken(userDTO, REFRESH_TOKEN_EXPIRATION_TIME);
    }

    private static String generateToken(AuthDTO userDetails, Long expirationTime) {
        LOG.debug("DEBUG: Token expiration set to: {}", new Date(System.currentTimeMillis() + expirationTime));
        return Jwts
                .builder()
                .subject(userDetails.getUsername())
                .issuer(JWT_TOKEN_ISSUER)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getSigningKey())
                .compact();
    }

    private static boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private static Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private static String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private static <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private static Claims extractAllClaims(String token) {
        return Jwts
                .parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private static SecretKey getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
